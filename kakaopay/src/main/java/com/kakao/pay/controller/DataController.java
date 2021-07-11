package com.kakao.pay.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakao.pay.mapper.TestMapper;
import com.kakao.pay.service.TestService;
import com.kakao.pay.vo.TestVo;



@Controller
@RequestMapping(value = "/data")
public class DataController {

	static Logger logger = LoggerFactory.getLogger(DataController.class);

	@Autowired
	TestService testService;

	@Autowired
	public TestMapper mapper;
	
	@RequestMapping(value = "/getDataList", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> getBoardList(Model model) throws Exception {
		logger.debug("getDataList");
		Map<String, Object> jsonObject =new HashMap<String, Object>();

		List<TestVo> testList = mapper.selectTest();

        jsonObject.put("id", testList.get(0).getId());
        jsonObject.put("name", testList.get(0).getName());
        jsonObject.put("resultmsg", "test");

		return jsonObject;
	}
	
	@RequestMapping(value = "/setCardPaying", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> setCardPaying(Model model) throws Exception {
		logger.debug("getDataList");
		Map<String, Object> jsonObject =new HashMap<String, Object>();

		List<TestVo> testList = mapper.selectTest();

        jsonObject.put("id", testList.get(0).getId());
        jsonObject.put("name", testList.get(0).getName());
        jsonObject.put("resultmsg", "test");

		return jsonObject;
	}

}	