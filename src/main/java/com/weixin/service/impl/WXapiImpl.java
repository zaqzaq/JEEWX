package com.weixin.service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.jeecgframework.core.util.ApplicationContextUtil;

import net.sf.json.JSONObject;
import weixin.guanjia.message.model.TextItem;
import weixin.guanjia.message.model.TextMessageKf;
import weixin.guanjia.message.service.CustomerMessageService;

import com.weixin.service.WXUtil;
import com.weixin.service.WXapi;

public class WXapiImpl extends UnicastRemoteObject  implements WXapi{

	private static final long serialVersionUID = 1L;

	public WXapiImpl() throws RemoteException {
		super();
	}

	@Override
	public boolean pushTextMSG(String wxOpenId, String content) throws RemoteException {
		TextMessageKf customMessage = new TextMessageKf();
		customMessage.setMsgtype("text");
		TextItem textItem = new TextItem();
		textItem.setContent(content);
		customMessage.setText(textItem);
		customMessage.setTouser(wxOpenId);
		
		JSONObject jsonObj = JSONObject.fromObject(customMessage);
		
		((CustomerMessageService)ApplicationContextUtil.getContext().getBean("customerMessageService")).
			sendMessage(jsonObj.toString(),WXUtil.get("ljtWX.account"));
		
		return false;
	}

	@Override
	public boolean pushNewMSG(String wxOpenId, Long newId) throws RemoteException {
		return false;
	}

}
