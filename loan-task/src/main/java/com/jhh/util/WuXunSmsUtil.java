package com.jhh.util;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


public class WuXunSmsUtil {
	
	private static final Logger logger = Logger.getLogger(WuXunSmsUtil.class);
	
	private static final String BASE_URL = "http://sms.newsms.com.cn/WebAPI/SmsAPI.asmx/SendSms";
	
	private static final String USER_NAME = "jinhuhang";
	private static final String PASS_WORD = "888888";

	public static boolean send(String message,String phone,int type){
		String result = "";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", USER_NAME);
		map.put("pwd", PASS_WORD);
		map.put("mobiles", phone);
		map.put("contents", message);
		try {
			result = HttpUtils.sendPost(BASE_URL, HttpUtils.toParam(map));
			logger.error("短信发送-phone:"+phone+",结果:" + result);
			String code = result.substring(result.indexOf("<Code>") + 6, result.indexOf("</Code>"));
			return Integer.valueOf(code) > -1  ? true : false;
		} catch (Exception e) {
			logger.error("短信发送异常 phone:" + phone + "------------:" + e.getMessage(), e);
			return false;
		}
	}
	
	public static String sendResult(String message,String phone,int type){
		String result = "";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", USER_NAME);
		map.put("pwd", PASS_WORD);
		map.put("mobiles", phone);
		map.put("contents", message);
		try {
			result = HttpUtils.sendPost(BASE_URL, HttpUtils.toParam(map));
			logger.error("短信发送-phone:"+phone+",结果:" + result);
		} catch (Exception e) {
			logger.error("短信发送异常 phone:" + phone + "------------:" + e.getMessage(), e);
		}
		return result;
	}
	
	public static void main(String[] args) {
		String phone = "15618973047";
		String[] phones = phone.split(",");
		System.out.println();
		String result = "【悠米闪借】尊敬的用户，您本次账单人民币1002.0。到期还 款日2017-07-30 。"
				+ "http://uat.youmishanjie.com/static/register/youmiRepayment.html";
		for(String p : phones){
			System.out.println(WuXunSmsUtil.sendResult(result,  p, 1));
		}
	}
}
