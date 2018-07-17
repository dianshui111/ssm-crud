package com.bingo.crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.mybatis.generator.codegen.ibatis2.dao.templates.GenericCIDAOTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bingo.crud.bean.Employee;
import com.bingo.crud.bean.Msg;
import com.bingo.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 处理员工增删改查请求
 * @author cbq
 *
 */
@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;

	
	/**
	 * 检查用户名在数据库中是否可用
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkuser")
	public Msg checkUser(@RequestParam("empName") String empName) {
		//1.检查输入格式是否正确
		String regName="(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,10}$)";
		//相当于 if (!regName.matches(empName)) 
		if (!Pattern.matches(regName, empName)) {
			return Msg.fail().add("err_msg", "输入用户名不符合规范！");
		}
		//2.进行数据库查询
		boolean isValid=employeeService.checkUser(empName);
		if(isValid)
			return Msg.success();
		else
			return Msg.fail().add("err_msg", "用户名已存在！");
	}
	

	/**
	 * 保存员工信息
	 * 1、支持JSR303校验
	 * 2、需要导入Hibernate-validator包
	 * 3、在bean的属性中注解匹配条件
	 * 4、@Valid校验参数,BindingResult result绑定校验结果
	 * @return
	 */
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody//导入Jackson包才能正常工作
	public Msg saveEmp(@Valid Employee employee, BindingResult result) {
		if(result.hasErrors()) {
			Map<String, Object> map=new HashMap<>();
			List<FieldError> errors=result.getFieldErrors();
			for(FieldError fieldError:errors) {
				//getField()为错误字段，getDefaultMessage()为错误信息
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}
		employeeService.saveEmp(employee);
		return Msg.success();
	}
	
	
	/**
	 * 根据id获取员工信息
	 * URL："${APP_PATH}/emp/"+id
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id")Integer id) {
		Employee employee=employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}
	
	
	/**
	 * 如果直接发送ajax=PUT形式的请求，封装的数据全是null
	 * 问题：
	 * 请求体中有数据，但是Employee对象封装不上；
	 * 原因：
	 * Tomcat：
	 * 	1.请求将数据封装成map
	 * 	2.requst.getParameter("empName")就会从这个map中取值
	 * 	3.SpringMVC在封装POJO对象时，会调用requst的这个方法。
	 * 
	 * ajax的PUT请求引发的血案：
	 * Tomcat不会封装PUT请求体中的数据为map，只有POST形式的请求体，才能对请求体的数据封装为map
	 * 
	 * 更新员工信息
	 * @param employee
	 * @return 更新成功或失败
	 */
	@RequestMapping(value="/emp/{empId}", method=RequestMethod.PUT)
	@ResponseBody
	public Msg updateEmp(Employee employee) {
		employeeService.updateEmp(employee);
		return Msg.success();
	}
	
	/**
	 * 删除员工
	 * 单个
	 * 批量1-2-3
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value="/emp/{delIds}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteById(@PathVariable("delIds")String delIds) {
		List<Integer> list=new ArrayList<>();
		if(delIds.contains("-")) {
			String[] ids=delIds.split("-");
			for(String s:ids) {
				list.add(Integer.parseInt(s));
			}
			employeeService.deleteBatchEmp(list);
			
		}else {
			employeeService.deleteById(Integer.parseInt(delIds));
		}
		return Msg.success();
	}
	/**
	 * 这是一个用json返回查询结果的方法
	 * @param pn
	 * @param model
	 * @return PageInfo
	 */
	@RequestMapping("/emps")
	@ResponseBody//导入Jackson包才能正常工作
	public Msg getEmpsWithJson(@RequestParam(value="pn", defaultValue="1")Integer pn) {
		PageHelper.startPage(pn,5);//每页5条记录		
		List<Employee> emps=employeeService.getAll();//startPage后面紧跟的查询就是一个分页查询
		PageInfo page = new PageInfo(emps,5);//连续显示5页
		
		return Msg.success().add("pageInfo",page);
	}
	/**
	 * 查询员工数据（分页查询）
	 * 这是将查询结果放到model中，跳转到jsp页码处理的方法
	 * @return
	 */
	//@RequestMapping可以用来注释一个控制器类，在这种情况下，所有方法都将映射为相对于类级别的请求，
	//表示该控制器处理的所有请求都被映射到value属性所指示的路径下。
	//@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pn", defaultValue="1")Integer pn,Model model) {
		//这不是一个分页查询
		//引入PageHelper分页插件
		//在查询之前只需要调用pageHelper，传入页码，和每页的记录数
		PageHelper.startPage(pn,5);//每页5条记录
		//startPage后面紧跟的查询就是一个分页查询
		List<Employee> emps=employeeService.getAll();
		//用PageInfo对结果进行包装,只需要将pageInfo交给页面就行了
		//封装了详细的分页信息，包括查询出来的数据
		PageInfo page = new PageInfo(emps,5);//连续显示5页
		model.addAttribute("pageInfo", page);//结果放到请求域model中
		
		return "list";
	}
	


}
