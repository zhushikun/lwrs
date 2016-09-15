package com.lwrs.utils;


/**
 * Created by zsk on 16/9/15.
 */
public class Logger {

	public static void info(Object obj, String msg){
		org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(obj.getClass());
		logger.info(msg);
	}

	public static void warn(Object obj, String msg){
		org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(obj.getClass());
		logger.info(msg);
	}


	public static void warn(Object obj, String msg, Throwable e){
		org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(obj.getClass());
		logger.info(msg, e);
	}

	public static void error(Object obj, String msg){
		org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(obj.getClass());
		logger.info(msg);
	}


	public static void error(Object obj, String msg, Throwable e){
		org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(obj.getClass());
		logger.info(msg, e);
	}


}
