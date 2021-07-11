package com.kakao.pay.mapper;

import java.util.List;
import com.kakao.pay.vo.CardVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CardMapper {
	List<CardVo> selectTest(); 
	int regCardPaymentHst(CardVo cardVo);
	int regCardPayment(CardVo cardVo);
	int updatCardPayment(CardVo cardVo);
	CardVo getPaymentHis(CardVo cardVo);
	CardVo getPaymentHisOne(CardVo cardVo);
	
}
