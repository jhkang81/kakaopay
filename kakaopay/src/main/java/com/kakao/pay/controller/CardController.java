package com.kakao.pay.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakao.pay.mapper.CardMapper;
import com.kakao.pay.mapper.TestMapper;
import com.kakao.pay.service.CardService;
import com.kakao.pay.service.TestService;
import com.kakao.pay.util.AES256Cipher;
import com.kakao.pay.util.PayUtil;
import com.kakao.pay.vo.CardVo;
import com.kakao.pay.vo.TestVo;



@Controller
@RequestMapping(value = "/card")
public class CardController {

	static Logger logger = LoggerFactory.getLogger(CardController.class);

	@Autowired
	TestService testService;

	
	@Autowired
	CardService cardService;
	
	/** 
	 * 카드결제 요청 
	 * @version 2.0 
	 * @param string 이름 
	 * @param int 연령 
	*/
	@RequestMapping(value = "/setPaying", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> setPaying(CardVo cardVo, Model model){
		logger.debug("setPaying");
		Map<String, Object> jsonObject =new HashMap<String, Object>();

		//List<CardVo> testList = cardService.selectTest();
		cardVo.setUid(PayUtil.getUid());
		cardVo.setGubun("PAYMENT");
		jsonObject.put("uid", cardVo.getUid());
		String msg = "";
		String code = "";
		if(PayUtil.isStringEmpty(cardVo.getCardNo()) 
				|| PayUtil.isStringEmpty(cardVo.getPayment())
				|| PayUtil.isStringEmpty(cardVo.getMmyy())
				|| PayUtil.isStringEmpty(cardVo.getCvc())
				|| PayUtil.isStringEmpty(cardVo.getInstallment())) {
			code = "0001";
			msg = "필수값 오류";
	        jsonObject.put("code", code);
	        jsonObject.put("msg", msg);
		}
		if(PayUtil.isStringEmpty(cardVo.getVat())) {
			cardVo.setVat(Integer.toString(PayUtil.getVat(Integer.parseInt(cardVo.getPayment()))));
		}		
		if(code.equals("")) {
			try {
				jsonObject = cardService.regCardPaymentHst(cardVo);
			} catch (Exception e) {
		        jsonObject.put("code", "0002");
		        jsonObject.put("msg", "결과 등록 실패");
		        e.printStackTrace();
			}
		}
		
		return jsonObject;
	}
	
	/** 
	 * 카드결제 취소 
	 * @version 1.0 
	 * @param CardVo  
	*/
	@RequestMapping(value = "/setCancel", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> setCancel(CardVo cardVo, Model model) throws Exception {
		logger.debug("getDataList");
		Map<String, Object> jsonObject = new HashMap<String, Object>();

		String msg = "";
		String code = "";
		cardVo.setInstallment("00");
		cardVo.setGubun("CANCEL");
		if(PayUtil.isStringEmpty(cardVo.getUid()) 
				|| PayUtil.isStringEmpty(cardVo.getPayment())) {
			code = "0001";
			msg = "필수값 오류";
	        jsonObject.put("code", code);
	        jsonObject.put("msg", msg);
		}
        
		cardVo.setpUid(cardVo.getUid());
		cardVo.setUid(PayUtil.getUid());
		
		if(PayUtil.isStringEmpty(cardVo.getVat())) {
			cardVo.setVat(Integer.toString(PayUtil.getVat(Integer.parseInt(cardVo.getPayment()))));
		}		
		
		if(code.equals("")) {
			try {
				jsonObject = cardService.cancelCardPayment(cardVo);
			} catch (Exception e) {
				e.printStackTrace();
		        jsonObject.put("code", "0002");
		        jsonObject.put("msg", "취소 결과 등록 실패");
			}
		}

		return jsonObject;
	}
	
	/** 
	 * 결재정보조회 
	 * @version 1.0 
	 * @param CardVo  
	*/
	@RequestMapping(value = "/getPayment", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> getPayment(CardVo cardVo, Model model) throws Exception {
		logger.debug("getDataList");
		Map<String, Object> jsonObject = new HashMap<String, Object>();
		String msg = "";
		String code = "";
		if(PayUtil.isStringEmpty(cardVo.getUid())) {
			code = "0001";
			msg = "필수값 오류";
	        jsonObject.put("code", code);
	        jsonObject.put("msg", msg);
		}
		if(code.equals("")) {
			try {
				jsonObject = cardService.getPayment(cardVo);
			} catch (Exception e) {
				e.printStackTrace();
		        jsonObject.put("code", "0002");
		        jsonObject.put("msg", "조회 실패");
			}
		}

		return jsonObject;
	}
}	