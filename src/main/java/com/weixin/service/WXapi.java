package com.weixin.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * jeewx微信平台提供的RMI接口
 * @author zaqzaq
 * 2015年7月10日
 *
 */
public interface WXapi  extends Remote {
	/**
	 * 向朗捷通微信用户发送文本消息
	 * @param wxOpenId  接收者微信openId
	 * @param content   文本内容 
	 * @return			 成功与否	
	 * @throws RemoteException
	 */
	public boolean pushTextMSG(String wxOpenId,String content)throws RemoteException;
	/**
	 * 向朗捷通微信用户发送图文消息
	 * @param wxOpenId 接收者微信openId
	 * @param newId    jeewx平台发布的消息ID
	 * @return			成功与否
	 * @throws RemoteException
	 */
	public boolean pushNewMSG(String wxOpenId,Long newId)throws RemoteException;
}
