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
		
		returnMessage="系统异常,请稍后再试，或联系： 朗捷通-信息中心---6046";
		
		try {
			returnMessage=ERPapiClient.getApi().getNoPWDuriToLogin(openId);
		}catch(java.rmi.ConnectException e){
			if(ERPapiClient.connectERPapiServer()){//重连RMI服务
				try {
					returnMessage=ERPapiClient.getApi().getNoPWDuriToLogin(openId);
				} catch (Exception e1) {
					e.printStackTrace();
				}
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		textMessage.setContent(returnMessage);
		return MessageUtil.textMessageToXml(textMessage);
	}

}
