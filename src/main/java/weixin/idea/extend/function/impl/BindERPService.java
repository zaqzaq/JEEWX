package weixin.idea.extend.function.impl;

import javax.servlet.http.HttpServletRequest;

import weixin.guanjia.core.entity.message.resp.TextMessageResp;
import weixin.guanjia.core.util.MessageUtil;
import weixin.idea.extend.function.KeyServiceI;

import com.weixin.service.ERPapiClient;

/**
 * 扩展----之绑定ERP帐户
 * @author zaqzaq
 * 2015年7月10日
 *
 */
public class BindERPService  implements KeyServiceI {

	@Override
	public String getKey() {
		return "ERP,erp";
	}

	@Override
	public String excute(String content, TextMessageResp textMessage, HttpServletRequest request) {
		
		String returnMessage = null;
		String[] keys=content.split("\\+");
		
		if(keys.length!=3){
			returnMessage="您输入绑定ERP的格式不正确：eg: ERP+test+123456";
		}else{
			String openId=textMessage.getToUserName();
			
			String userName=keys[1];
			String authCode=keys[2];
			
			try {
				returnMessage=ERPapiClient.getApi().updateUserWXopenId(userName, openId, authCode);
			} catch (Exception e) {
				e.printStackTrace();
				returnMessage="系统异常，请联系： 朗捷通-信息中心---6046";
			}
		}
		
		textMessage.setContent(returnMessage);
		return MessageUtil.textMessageToXml(textMessage);
	}

}
