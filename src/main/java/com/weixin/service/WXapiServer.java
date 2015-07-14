package com.weixin.service;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import org.apache.log4j.Logger;

import com.weixin.service.impl.WXapiImpl;
/**
 * 微信API的RMI服务
 * @author zaqzaq
 * 2015年7月14日
 *
 */
public class WXapiServer {
		private static Logger logger=Logger.getLogger(WXapiServer.class);
		
		public static void init(){
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {  
						logger.info("----------------微信 提供给ERP的RMI服务启动ing.......----------");
						 WXapi api = new  WXapiImpl();  
						 LocateRegistry.createRegistry(Integer.valueOf(WXUtil.get("wxRMI.port")));
				         Naming.rebind("rmi://127.0.0.1:"+WXUtil.get("wxRMI.port")+"/wxAPI", api);  
				         logger.info("----------------微信 提供给ERP的RMI服务启动成功！---------------");
				     } catch (Exception e) {  
				         e.printStackTrace();  
				         logger.error("----------------微信 提供给ERP的RMI服务启动失败！---------------", e);
				     }  				
				}
			}).start();;
			 
		}
}
