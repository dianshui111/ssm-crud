package com.bingo.crud.controller;
/**
 * ����������
 * @author cbq
 *
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.bingo.crud.bean.Department;
import com.bingo.crud.bean.Msg;
import com.bingo.crud.service.DepartmentService;

@Controller
public class DepartmentController {
	@Autowired
	DepartmentService departmentService;
	/**
	 * ������в�����Ϣ
	 */
	@RequestMapping("/depts")
	@ResponseBody
	public Msg getDepts(){
		List<Department> depts=departmentService.getAllDepts();
		return Msg.success().add("depts", depts);
	}

}
