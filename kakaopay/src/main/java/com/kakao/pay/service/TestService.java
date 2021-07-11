package com.kakao.pay.service;

import com.kakao.pay.mapper.TestMapper;
import com.kakao.pay.vo.TestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TestService {
	
	public TestMapper mapper;
	
	public List<TestVo> selectTest() {
		return mapper.selectTest();
	}
}