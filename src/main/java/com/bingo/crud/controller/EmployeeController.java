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
 * ����Ա����ɾ�Ĳ�����
 * @author cbq
 *
 */
@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;

	
	/**
	 * ����û��������ݿ����Ƿ����
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkuser")
	public Msg checkUser(@RequestParam("empName") String empName) {
		//1.��������ʽ�Ƿ���ȷ
		String regName="(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,10}$)";
		//�൱�� if (!regName.matches(empName)) 
		if (!Pattern.matches(regName, empName)) {
			return Msg.fail().add("err_msg", "�����û��������Ϲ淶��");
		}
		//2.�������ݿ��ѯ
		boolean isValid=employeeService.checkUser(empName);
		if(isValid)
			return Msg.success();
		else
			return Msg.fail().add("err_msg", "�û����Ѵ��ڣ�");
	}
	

	/**
	 * ����Ա����Ϣ
	 * 1��֧��JSR303У��
	 * 2����Ҫ����Hibernate-validator��
	 * 3����bean��������ע��ƥ������
	 * 4��@ValidУ�����,BindingResult result��У����
	 * @return
	 */
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody//����Jackson��������������
	public Msg saveEmp(@Valid Employee employee, BindingResult result) {
		if(result.hasErrors()) {
			Map<String, Object> map=new HashMap<>();
			List<FieldError> errors=result.getFieldErrors();
			for(FieldError fieldError:errors) {
				//getField()Ϊ�����ֶΣ�getDefaultMessage()Ϊ������Ϣ
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}
		employeeService.saveEmp(employee);
		return Msg.success();
	}
	
	
	/**
	 * ����id��ȡԱ����Ϣ
	 * URL��"${APP_PATH}/emp/"+id
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
	 * ���ֱ�ӷ���ajax=PUT��ʽ�����󣬷�װ������ȫ��null
	 * ���⣺
	 * �������������ݣ�����Employee�����װ���ϣ�
	 * ԭ��
	 * Tomcat��
	 * 	1.�������ݷ�װ��map
	 * 	2.requst.getParameter("empName")�ͻ�����map��ȡֵ
	 * 	3.SpringMVC�ڷ�װPOJO����ʱ�������requst�����������
	 * 
	 * ajax��PUT����������Ѫ����
	 * Tomcat�����װPUT�������е�����Ϊmap��ֻ��POST��ʽ�������壬���ܶ�����������ݷ�װΪmap
	 * 
	 * ����Ա����Ϣ
	 * @param employee
	 * @return ���³ɹ���ʧ��
	 */
	@RequestMapping(value="/emp/{empId}", method=RequestMethod.PUT)
	@ResponseBody
	public Msg updateEmp(Employee employee) {
		employeeService.updateEmp(employee);
		return Msg.success();
	}
	
	/**
	 * ɾ��Ա��
	 * ����
	 * ����1-2-3
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
	 * ����һ����json���ز�ѯ����ķ���
	 * @param pn
	 * @param model
	 * @return PageInfo
	 */
	@RequestMapping("/emps")
	@ResponseBody//����Jackson��������������
	public Msg getEmpsWithJson(@RequestParam(value="pn", defaultValue="1")Integer pn) {
		PageHelper.startPage(pn,5);//ÿҳ5����¼		
		List<Employee> emps=employeeService.getAll();//startPage��������Ĳ�ѯ����һ����ҳ��ѯ
		PageInfo page = new PageInfo(emps,5);//������ʾ5ҳ
		
		return Msg.success().add("pageInfo",page);
	}
	/**
	 * ��ѯԱ�����ݣ���ҳ��ѯ��
	 * ���ǽ���ѯ����ŵ�model�У���ת��jspҳ�봦��ķ���
	 * @return
	 */
	//@RequestMapping��������ע��һ���������࣬����������£����з�������ӳ��Ϊ������༶�������
	//��ʾ�ÿ�����������������󶼱�ӳ�䵽value������ָʾ��·���¡�
	//@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pn", defaultValue="1")Integer pn,Model model) {
		//�ⲻ��һ����ҳ��ѯ
		//����PageHelper��ҳ���
		//�ڲ�ѯ֮ǰֻ��Ҫ����pageHelper������ҳ�룬��ÿҳ�ļ�¼��
		PageHelper.startPage(pn,5);//ÿҳ5����¼
		//startPage��������Ĳ�ѯ����һ����ҳ��ѯ
		List<Employee> emps=employeeService.getAll();
		//��PageInfo�Խ�����а�װ,ֻ��Ҫ��pageInfo����ҳ�������
		//��װ����ϸ�ķ�ҳ��Ϣ��������ѯ����������
		PageInfo page = new PageInfo(emps,5);//������ʾ5ҳ
		model.addAttribute("pageInfo", page);//����ŵ�������model��
		
		return "list";
	}
	


}
