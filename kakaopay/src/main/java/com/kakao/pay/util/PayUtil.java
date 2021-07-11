package com.kakao.pay.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.kakao.pay.vo.CardVo;

public class PayUtil {
	public static String curDate() {
		SimpleDateFormat format = new SimpleDateFormat ( "yyyyMMddHHmmss");
		Date time = new Date();
		String rtn = format.format(time); 
		return rtn;
	}
	
	public static String getUid() {
		SimpleDateFormat format = new SimpleDateFormat ( "yyyyMMddHHmmss");
		Date time = new Date();
		Random rnd = new Random();
		String randomStr = String.valueOf(rnd.nextInt(1000000));

		String rtn = format.format(time)+StringUtils.leftPad(randomStr, 6, "0");
		
		return rtn;
	}
	
	public static boolean isStringEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}

	public static int getVat(int payment) {
		return (int) Math.round((double)payment/11);
	}

	public static String getStringData(CardVo cardVo) {
		String rtnStr;
		String chkStr = StringUtils.leftPad(cardVo.getGubun(), 10)
				+StringUtils.rightPad(cardVo.getUid(), 10)
				+StringUtils.rightPad(cardVo.getCardNo(), 20)
				+StringUtils.leftPad(StringUtils.stripToEmpty(cardVo.getInstallment()), 2, "0")
				+StringUtils.leftPad(cardVo.getMmyy(), 4, "0")
				+cardVo.getCvc()
				+StringUtils.leftPad(cardVo.getPayment(), 10)
				+StringUtils.leftPad(cardVo.getVat(), 10, "0")
				+StringUtils.leftPad(StringUtils.stripToEmpty(cardVo.getpUid()), 20)
				+StringUtils.rightPad(cardVo.getCardEnc(), 300)
				+StringUtils.rightPad("", 47);
		rtnStr = StringUtils.leftPad(Integer.toString(chkStr.length()), 4)+chkStr;
		System.out.println(rtnStr);
		return rtnStr;
	}
}
