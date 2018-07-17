package com.bingo.crud.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bingo.crud.bean.Department;
import com.bingo.crud.bean.Employee;
import com.bingo.crud.dao.DepartmentMapper;
/**
 * 
 * @author cbq
 * �Ƽ�Spring����Ŀ�Ϳ���ʹ��Spring�ĵ�Ԫ���ԣ������Զ�ע��������Ҫ�����
 *1������SpringTest����ģ��
 *2��ContextConfiguration ָ��Spring�����ļ���λ��
 *3��ֱ��autowiredҪʹ�õ��������
 */
import com.bingo.crud.dao.EmployeeMapper;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class MapperTest {

	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	SqlSession sqlSession;
	/**
	 * ����departmentMapper
	*/
	@Test
	public void testCRUD(){
//			//1������Spring��ioc����
//			ApplicationContext ioc=new ClassPathXmlApplicationContext("DepartmentMapper.xml");
//			//2���������л�ȡmapper
//			DepartmentMapper bean=ioc.getBean(DepartmentMapper.class);
		
		System.out.println(employeeMapper);
			
			
		//1.���뼸������
//		departmentMapper.insertSelective(new Department(null,"������"));
//		departmentMapper.insertSelective(new Department(null,"���Բ�"));
		
		//2������Ա������
		//employeeMapper.insertSelective(new Employee(null,"Jerry","M","Jerry@qq.com",1));
		
		//3������������Ա����ʹ�ÿ���ִ������������sqlSession,����ʹ��UUID����
//		for() {
//			employeeMapper.insertSelective(new Employee(null, ,"M","Jerry@qq.com",1));
//		}
		EmployeeMapper mapper=sqlSession.getMapper(EmployeeMapper.class);
		for(int i=0;i<1000;i++) {
			String uid=UUID.randomUUID().toString().substring(0, 5)+i;//����
			mapper.insert(new Employee(null,uid ,"M",uid+"@qq.com",1));
		}
		System.out.println("����ִ����ɣ�");
	}

}
