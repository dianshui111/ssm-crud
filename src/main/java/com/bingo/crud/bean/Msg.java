package com.bingo.crud.bean;
/**
 * json数据通用的返回类
 * @author cbq
 *
 */

import java.util.HashMap;
import java.util.Map;

public class Msg {
	//自定义状态码 100-成功 200-失败
	private int code;
	//提示信息
	private String msg;
	//用户要返回给浏览器的数据
	private Map<String, Object> extend= new HashMap<>();
	
	//成功消息
	public static Msg success() {
		Msg result= new Msg();
		result.setCode(100);
		result.setMsg("处理成功！");	
		return result;
	}
	
	//失败消息
	public static Msg fail() {
		Msg result= new Msg();
		result.setCode(200);
		result.setMsg("处理失败！");
		return result;
	}
	
	//链式操作add
	public Msg add(String key,Object value) {
		this.getExtend().put(key, value);
		return this;
		
	}
	
	
	
	//getter和setter

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
