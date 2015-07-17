package com.weixin.service;

import java.rmi.Naming;

import org.apache.log4j.Logger;

import com.sun.star.uno.RuntimeException;
/**
 * ERP接口客户端
 * @author zaqzaq
 * 2015年7月10日
 *
 */
public class ERPapiClient {

	private static ERPapi erPapi=null;
	private static Logger logger=Logger.getLogger(ERPapiClient.class);
	private static boolean runFlag=false;//运行状态
	
	private ERPapiClient(){}
	
	public static ERPapi getApi() throws Exception{
		if(erPapi==null){
			throw new RuntimeException("与ERP系统对接中，请稍候再试。。。");
		}
		return erPapi;
	}
	
	public static void init(){
		//调用远程对象，注意RMI路径与接口必须与服务器配置一致
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(!runFlag){
					connectERPapiServer();	
					try {
						Thread.sleep(5000l);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();;
		
	}
	
	/**
	 * 连接ERPapi服务RMI中心
	 */
	public synchronized static boolean connectERPapiServer(){
		runFlag=false;
		try {
			erPapi=(ERPapi)Naming.lookup("rmi://"+WXUtil.get("erpRMI.host")+":"+WXUtil.get("erpRMI.port")+"/erpAPI");
			runFlag=true;
			logger.info("ERP RMI服务连接成功");
		} catch (Exception e) {
			logger.info("ERP RMI服务连接失败："+e.getMessage()+"----5秒后重连");
		}	
		return runFlag;
	}

}
