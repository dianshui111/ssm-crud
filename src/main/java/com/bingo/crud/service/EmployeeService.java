package com.bingo.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bingo.crud.bean.Employee;
import com.bingo.crud.bean.EmployeeExample;
import com.bingo.crud.bean.EmployeeExample.Criteria;
import com.bingo.crud.dao.EmployeeMapper;

@Service
public class EmployeeService {

	@Autowired
	EmployeeMapper employeeMapper;
	/**
	 * 查询所有员工
	 * @return List<Employee>
	 */
	public List<Employee> getAll() {
		return employeeMapper.selectByExampleWithDept(null);
	}
	
	/**
	 * 保存员工信息
	 * @param employee
	 */
	public void saveEmp(Employee employee) {
		employeeMapper.insertSelective(employee);
	}

	/**
	 * 检查用户名在数据库中是否可用
	 * @param empName
	 * @return true:该用户名不存在，可用；false：用户名已存在，不可用
	 */
	public boolean checkUser(String empName) {
		EmployeeExample example=new EmployeeExample();
		//创建查询条件
		Criteria criteria=example.createCriteria();
		//拼装查询条件
		criteria.andEmpNameEqualTo(empName);
		long count=employeeMapper.countByExample(example);
		return count==0;
	}

	public Employee getEmp(Integer empId) {
		Employee employee=employeeMapper.selectByPrimaryKey(empId);
		return employee;
	}

	public void updateEmp(Employee employee) {
		employeeMapper.updateByPrimaryKeySelective(employee);		
		
	}

	public void deleteById(Integer id) {
		employeeMapper.deleteByPrimaryKey(id);		
	}

	public void deleteBatchEmp(List<Integer> ids) {
		EmployeeExample example=new EmployeeExample();
		Criteria criteria=example.createCriteria();
		criteria.andEmpIdIn(ids);
		employeeMapper.deleteByExample(example);
	}

}
