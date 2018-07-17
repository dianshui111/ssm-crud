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
	 * ��ѯ����Ա��
	 * @return List<Employee>
	 */
	public List<Employee> getAll() {
		return employeeMapper.selectByExampleWithDept(null);
	}
	
	/**
	 * ����Ա����Ϣ
	 * @param employee
	 */
	public void saveEmp(Employee employee) {
		employeeMapper.insertSelective(employee);
	}

	/**
	 * ����û��������ݿ����Ƿ����
	 * @param empName
	 * @return true:���û��������ڣ����ã�false���û����Ѵ��ڣ�������
	 */
	public boolean checkUser(String empName) {
		EmployeeExample example=new EmployeeExample();
		//������ѯ����
		Criteria criteria=example.createCriteria();
		//ƴװ��ѯ����
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
