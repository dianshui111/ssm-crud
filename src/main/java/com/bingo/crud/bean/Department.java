package com.bingo.crud.bean;

public class Department {



	private Integer deptId;

    private String deptName;
    
//生成有参构造器的同时，一定要生成无参构造器！
  	public Department(Integer deptId, String deptName) {
  		super();
  		this.deptId = deptId;
  		this.deptName = deptName;
  	}
      public Department() {
  		super();
  	}
      
//getter及setter函数
    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }
}