package com.kakao.pay.mapper;

import java.util.List;
import com.kakao.pay.vo.TestVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TestMapper {
	List<TestVo> selectTest(); 
}
