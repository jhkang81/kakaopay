package com.kakao.pay.service;

import com.kakao.pay.vo.CardVo;
import java.util.List;
import java.util.Map;

public interface  CardService {
	
	List<CardVo> selectTest() throws Exception;
	
	Map<String, Object> regCardPaymentHst(CardVo cardVo) throws Exception;
	
	Map<String, Object> cancelCardPayment(CardVo cardVo) throws Exception;
	
	Map<String, Object> getPayment(CardVo cardVo) throws Exception;
	
}