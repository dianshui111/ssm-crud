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
 * ��ȡ���в�����Ϣ
 */
	public List getAllDepts() {
		//ѡ������Ϊnull���൱��ȫѡ
		return departmentMapper.selectByExample(null);
	}

}
