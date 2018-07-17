package com.bingo.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bingo.crud.bean.Department;
import com.bingo.crud.dao.DepartmentMapper;


@Service
public class DepartmentService {
	@Autowired
	DepartmentMapper departmentMapper;
/**
 * 获取所有部门信息
 */
	public List getAllDepts() {
		//选择条件为null，相当于全选
		return departmentMapper.selectByExample(null);
	}

}
