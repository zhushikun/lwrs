package com.lwrs.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by zsk on 16/9/15.
 */
public class Logger {

	public static void info(Object obj, String msg){
		Log log = LogFactory.getLog(obj.getClass());
		log.info(msg);
	}

	public static void warn(Object obj, String msg){
		Log log = LogFactory.getLog(obj.getClass());
		log.warn(msg);
	}


	public static void warn(Object obj, String msg, Throwable e){
		Log log = LogFactory.getLog(obj.getClass());
		log.warn(msg, e);
	}

	public static void error(Object obj, String msg){
		Log log = LogFactory.getLog(obj.getClass());
		log.error(msg);
	}


	public static void error(Object obj, String msg, Throwable e){
		Log log = LogFactory.getLog(obj.getClass());
		log.error(msg, e);
	}


}
