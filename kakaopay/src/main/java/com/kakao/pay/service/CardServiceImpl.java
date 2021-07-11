package com.kakao.pay.service;

import com.kakao.pay.mapper.CardMapper;
import com.kakao.pay.util.AES256Cipher;
import com.kakao.pay.util.PayUtil;
import com.kakao.pay.vo.CardVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CardServiceImpl implements CardService {
	
	@Autowired
	public CardMapper cardMapper;
	
	@Override
	public List<CardVo> selectTest() throws Exception {
		System.out.println(this.getClass()+"test111");
		return cardMapper.selectTest();
	}
	
	@Override
	@Transactional
	public Map<String, Object> regCardPaymentHst(CardVo cardVo) throws Exception {

		Map<String, Object> jsonObject =  new HashMap<String, Object>();
		AES256Cipher a256 = AES256Cipher.getInstance();
		String id =cardVo.getCardNo()+"|"+cardVo.getMmyy()+"|"+cardVo.getCvc();
        
		String enId = a256.AES_Encode(id);
		System.out.println(enId);
		cardVo.setCardEnc(enId);
		cardVo.setStringData(PayUtil.getStringData(cardVo));
		cardVo.setpUid(cardVo.getUid());
		cardMapper.regCardPaymentHst(cardVo);
		cardMapper.regCardPayment(cardVo);

		jsonObject.put("uid", cardVo.getUid());
		jsonObject.put("data", cardVo.getStringData());
		jsonObject.put("code", "0000");
		jsonObject.put("msg", "결재 성공");
		return jsonObject;
	}
	
	@Override
	@Transactional
	public Map<String, Object> cancelCardPayment(CardVo cardVo) throws Exception {
		Map<String, Object> jsonObject =  new HashMap<String, Object>();
		CardVo rtnCardVo = cardMapper.getPaymentHis(cardVo);
		int payment = Integer.parseInt(rtnCardVo.getPayment()) - Integer.parseInt(cardVo.getPayment());
		int vat = Integer.parseInt(rtnCardVo.getVat()) - Integer.parseInt(cardVo.getVat());
		if(rtnCardVo==null) {
			jsonObject.put("code", "0004");
			jsonObject.put("msg", "결제정보 조회 실패");
			jsonObject.put("uid", cardVo.getpUid());
		}else if(payment<0 || vat<0) {
			System.out.println("결제금액 오류");
			jsonObject.put("code", "0003");
			jsonObject.put("msg", "결제금액 오류");
			jsonObject.put("payment", rtnCardVo.getPayment());
			jsonObject.put("vat", rtnCardVo.getVat());
			jsonObject.put("parmPayment", cardVo.getPayment());
			jsonObject.put("parmVat", cardVo.getVat());
		}else {
			rtnCardVo.setUid(cardVo.getUid());
			rtnCardVo.setpUid(cardVo.getpUid());
			rtnCardVo.setPayment(cardVo.getPayment());
			rtnCardVo.setVat(cardVo.getVat());
			rtnCardVo.setGubun(cardVo.getGubun());
			rtnCardVo.setStringData(PayUtil.getStringData(rtnCardVo));
			cardMapper.regCardPaymentHst(rtnCardVo);
			
			rtnCardVo.setPayment(Integer.toString(payment));
			rtnCardVo.setVat(Integer.toString(vat));
			
			cardMapper.updatCardPayment(rtnCardVo);

			jsonObject.put("uid", cardVo.getUid());
			jsonObject.put("puid", cardVo.getpUid());
			jsonObject.put("payment", payment);
			jsonObject.put("vat", vat);
			jsonObject.put("data", rtnCardVo.getStringData());
			jsonObject.put("code", "0000");
			jsonObject.put("msg", "취소 성공");
			
		}
		return jsonObject;
	}
	
	@Override
	public Map<String, Object> getPayment(CardVo cardVo) throws Exception {
		Map<String, Object> jsonObject =  new HashMap<String, Object>();
		CardVo rtnCardVo = cardMapper.getPaymentHisOne(cardVo);
		jsonObject.put("uid", cardVo.getUid());

		AES256Cipher a256 = AES256Cipher.getInstance();
		
		String[] deId = a256.AES_Decode(rtnCardVo.getCardEnc()).split("[|]");
		String cardno = deId[0];
		String masking = "";
		for(int i=0; i<(cardno.length()-9); i++ ) {
			masking += "*";
		}
		cardno = cardno.substring(0,6) + masking + cardno.substring(cardno.length()-3, cardno.length());

		jsonObject.put("cardNo", deId[0]);
		jsonObject.put("mmyy", deId[1]);
		jsonObject.put("cvc", deId[2]);
		jsonObject.put("gubun", rtnCardVo.getGubun());
		jsonObject.put("payment", rtnCardVo.getPayment());
		jsonObject.put("vat", rtnCardVo.getVat());
		jsonObject.put("data", rtnCardVo.getStringData());
		jsonObject.put("code", "0000");
		jsonObject.put("msg", "조회 성공");
		return jsonObject;
	}
	
	
}