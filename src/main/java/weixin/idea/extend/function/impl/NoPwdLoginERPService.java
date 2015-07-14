package weixin.idea.extend.function.impl;

import javax.servlet.http.HttpServletRequest;

import weixin.guanjia.core.entity.message.resp.TextMessageResp;
import weixin.guanjia.core.util.MessageUtil;
import weixin.idea.extend.function.KeyServiceI;

import com.weixin.service.ERPapiClient;

/**
 * 扩展----之免密码登陆ERP
 * @author zaqzaq
 * 2015年7月10日
 *
 */
public class NoPwdLoginERPService  implements KeyServiceI {

	@Override
	public String getKey() {
		return "LOGIN,免密码登陆ERP,login";
	}

	@Override
	public String excute(String content, TextMessageResp textMessage, HttpServletRequest request) {
		
		String returnMessage = null;
		
		String openId=textMessage.getToUserName();
		try {
			returnMessage=ERPapiClient.getApi().getNoPWDuriToLogin(openId);
		} catch (Exception e) {
			e.printStackTrace();
			returnMessage="系统异常，请联系： 朗捷通-信息中心---6046";
		}
		
		textMessage.setContent(returnMessage);
		return MessageUtil.textMessageToXml(textMessage);
	}

}
