package com.weixin.service;

import java.util.Properties;

import org.apache.log4j.Logger;


public class WXUtil {
	private static Logger logger=Logger.getLogger(WXUtil.class);
//	private static SendMsgService sendMsg;
	private final static String weixinPath="weixin/weixin.properties";
	private static Properties properties;
	static{
		properties=new Properties();
		try {
			properties.load(WXUtil.class.getClassLoader().getResourceAsStream(weixinPath));
		}catch(Exception e){
			logger.error("--------------文件"+weixinPath+"加载失败-------", e);
		}	
	}
		
	public static String get(String key){
		return (String) properties.get(key);
	}	
}
