package com.duolanjian.java.market.view;

import java.util.HashMap;

import com.duolanjian.java.market.bean.User;
import com.duolanjian.java.market.util.DateTool;

public class UserView extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	public UserView(User user) {
		put("id", user.getId());
		put("wechat", user.getWechat());
		
		//put("createTime", DateTool.standardSdf.format(user.getCreateTime()));
	}
	
}
