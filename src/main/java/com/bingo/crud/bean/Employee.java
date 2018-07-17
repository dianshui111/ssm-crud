package com.bingo.crud.bean;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

public class Employee {


	private Integer empId;
	
    //@pattern �Զ���empName��У�����
	@Pattern(regexp="(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,10}$)",message="���ֲ����������������")
    private String empName;

    private String gender;
    
    //������jsr303�Դ���email��ʵ�֣�Ҳ�����Զ���ʵ��
   // @Pattern(regexp="^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$",message="���䲻���������������")
    @Email(message="���䲻���������������")
    private String email;

    private Integer dId;
    
    //���Ӳ�����Ϣ���ڲ�ѯԱ����ͬʱ���������������
    private Department department;
    
   //�вκ��޲ι��캯�� 
    public Employee() {
		super();
	}

	public Employee(Integer empId, String empName, String gender, String email, Integer dId) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.gender = gender;
		this.email = email;
		this.dId = dId;
	}
	
	
	//getter��setter����
    public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }
}