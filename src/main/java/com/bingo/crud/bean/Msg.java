package com.bingo.crud.bean;
/**
 * json����ͨ�õķ�����
 * @author cbq
 *
 */

import java.util.HashMap;
import java.util.Map;

public class Msg {
	//�Զ���״̬�� 100-�ɹ� 200-ʧ��
	private int code;
	//��ʾ��Ϣ
	private String msg;
	//�û�Ҫ���ظ������������
	private Map<String, Object> extend= new HashMap<>();
	
	//�ɹ���Ϣ
	public static Msg success() {
		Msg result= new Msg();
		result.setCode(100);
		result.setMsg("����ɹ���");	
		return result;
	}
	
	//ʧ����Ϣ
	public static Msg fail() {
		Msg result= new Msg();
		result.setCode(200);
		result.setMsg("����ʧ�ܣ�");
		return result;
	}
	
	//��ʽ����add
	public Msg add(String key,Object value) {
		this.getExtend().put(key, value);
		return this;
		
	}
	
	
	
	//getter��setter

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}
	
	
	
	

}
