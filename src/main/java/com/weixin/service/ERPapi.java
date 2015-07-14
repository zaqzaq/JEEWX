package com.weixin.service;
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * ERP系统提供的RMI接口
 * @author zaqzaq
 * 2015年7月10日
 *
 */
public interface ERPapi extends Remote {
	
	/**
	 * 给用户userName绑定微信openId
	 * @param userName OA登陆名
	 * @param wxOpenId 朗捷通关注用户的openId
	 * @param code 邀请码
	 * @return 提示信息
	 */
	public String updateUserWXopenId(String userName,String wxOpenId,String code) throws RemoteException;
	
	/**
	 * 获取免密码登陆的URI链接
	 * @param wxOpenId 朗捷通关注用户的openId
	 * @return
	 * @throws RemoteException
	 */
	public String getNoPWDuriToLogin(String wxOpenId) throws RemoteException;
}

