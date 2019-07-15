package com.hy.security.core.validate.code.validate.impl;

import com.hy.security.core.validate.code.validate.SmsCodeSender;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
/**
 * 短信发送实现类
 * @author hy
 *
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

	// 短信发送接口的http地址，请咨询客服
	private String url = "http://api01.monyun.cn:7901/sms/v2/std/single_send";
	//private String url = "http://api02.monyun.cn:7901/sms/v2/std/";
	//http://api01.monyun.cn:7901/sms/v2/std/single_send
	/**
	 * 编码格式。发送编码格式统一用UTF-8
	 */
	//private static final String ENCOKING = "UTF-8";

	//账号
	private String userid = "E107RE";

	//密码
	private String pwd = "zE3FyQ";
	
	private String APIKey = "a85dfcbfd03270999a06f6235db65636";
	
	//String msg = "【汉宇科技】您的验证码是："+code;
	/**
	     * 发送短信
	     *
	     * @param account
	     *            account
	     * @param pswd
	     *            pswd
	     * @param mobile
	     *            手机号码
	     * @param content
	     *            短信发送内容
	     */
	@Override
	public void send(String mobile, String code) {
		System.out.println("向手机"+mobile+"发送验证码"+code);
		String content = "您的验证码是"+code+"请在2分钟内验证";
		NameValuePair[] data = { new NameValuePair("userid",userid),
				new NameValuePair("pwd", pwd),
				new NameValuePair("mobile",mobile),
				new NameValuePair("content",content),
				new NameValuePair("APIKey",APIKey)
		};
		doPost(url,data);
	}

	/**
	*基于HttpClient的post函数
	* PH
	* @param url
	* 提交的URL
	* @param data
	* 提交NameValuePair参数
	* @return 提交响应
	*/
	private void doPost(String url, NameValuePair[] data) {
		HttpClient client =new HttpClient();
		PostMethod method=new PostMethod(url);
		// method.setRequestHeader("ContentType",
		// "application/x-www-form-urlencoded;charset=UTF-8");
		method.setRequestBody(data);
		// client.getParams().setContentCharset("UTF-8");
		client.getParams().setConnectionManagerTimeout(10000);
		try{
			client.executeMethod(method);
			String str = method.getResponseBodyAsString();
			System.err.println(str);
		}catch(Exception e){
			e.printStackTrace();
		}
	}




}
