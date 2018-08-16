package com.gzzy.foodManProgram.logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogManager {
	
	
	static Logger logger = LoggerFactory.getLogger("com.hp");

	public static void info(String msg){
		logger.info(msg);
	}
	
	public static void err(String msg){
		logger.error(msg); 
	}
	public static void err(String msg , Throwable th){
		logger.error(msg , th); 
	}
	
}
