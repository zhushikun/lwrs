package com.lwrs.bean;

import com.google.gson.Gson;

/**
 * Created by zsk on 16/9/15.
 */
public class BaseBean {

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
