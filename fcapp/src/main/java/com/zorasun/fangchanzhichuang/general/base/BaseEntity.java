package com.zorasun.fangchanzhichuang.general.base;

import java.io.Serializable;

/**
 * 实体基础类
 * 
 * @author Iverson_573
 * 
 * @create-time 2015年8月7日 上午9:30:37
 * 
 * 
 */
public class BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 返回码
	 */
	private int code;
	/**
	 * 返回信息描述
	 */
	private String msg;
	
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

}
