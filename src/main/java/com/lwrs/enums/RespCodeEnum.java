package com.lwrs.enums;

/**
 * Created by zsk on 16/9/15.
 */
public enum RespCodeEnum {
	OK("0", "ok"),
	PARAM_INVALID("0001", "invalid param");

	private String code;
	private String msg;

	RespCodeEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
