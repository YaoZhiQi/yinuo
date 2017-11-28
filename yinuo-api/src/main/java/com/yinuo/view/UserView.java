package com.yinuo.view;

import java.util.HashMap;

import com.yinuo.bean.User;

public class UserView extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	public UserView(User user) {
		put("id", user.getId());
		
		
		//put("createTime", DateTool.standardSdf.format(user.getCreateTime()));
	}
	
}
