package com.duolanjian.java.market.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.duolanjian.java.market.bean.User;

@Component
public interface UserMapper extends MapperI<User>{
	
	public User selectByMobile(@Param("mobile")String mobile);
	
	public void deleteByMobile(@Param("mobile")String mobile);

}
 