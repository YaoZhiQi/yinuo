package com.duolanjian.java.market.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duolanjian.java.market.bean.User;
import com.duolanjian.java.market.service.UserService;
import com.duolanjian.java.market.util.JedisUtil;
import com.duolanjian.java.market.util.MD5Util;
import com.duolanjian.java.market.validation.Validation;

@RestController
public class UserController {

	@Autowired
	private Validation validation;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MD5Util md5Util;
	
	@Autowired
	private JedisUtil jedisUtil;
	
	@RequestMapping(value="/loginByWeixin", method=RequestMethod.GET)
//    @ResponseBody
    public Object post(@RequestParam(name="code") String code){
		Map<String,Object> result = new HashMap<String, Object>();
		
		
		
		result.put("id", 1);
		return result;
    }
	/*
	@RequestMapping(value="/users", method=RequestMethod.POST)
//    @ResponseBody
    public Object post(@RequestBody String body){
		Map<String,Object> result = new HashMap<String, Object>();
		User user = validation.getObject(body, User.class, new String[]{"mobile","password"});
		user.setPassword(md5Util.string2MD5(user.getPassword()));
		long id = userService.insert(user);
		result.put("id", id);
		return result;
    }
	
	@NeedLogin
	@RequestMapping(value="/users", method=RequestMethod.PUT)
    public Object put(User loginUser, @RequestBody String body){
		
		loginUser.checkLevel(2);
		Map<String,Object> result = new HashMap<String, Object>();
		User user = validation.getObject(body, User.class, new String[]{"id"});
		User src = userService.selectOne(user.getId());
		if(src == null) {
			throw new InvalidArgumentException("不存在的ID。");
		}
		try{
			user = (User) MergerUtil.merger(src, user);
		} catch (Exception e) {
			throw new InvalidArgumentException(e.getMessage());
		}
		user.setPassword(md5Util.convertMD5(user.getPassword()));
		userService.update(user);
		return result;
    }

	@NeedLogin
	@RequestMapping(value="/users", method=RequestMethod.DELETE)
    public Object delete(User loginUser, @RequestParam Long id){
		
		loginUser.checkLevel(3);
        Map<String, Object> result=new HashMap<String, Object>();
        userService.delete(id);
        return result;
    }
	
	@NeedLogin
	@RequestMapping(value="/users", method=RequestMethod.GET)
    public Object get(User loginUser, @RequestParam(defaultValue="false") boolean self,
    		@RequestParam(defaultValue="1") int page,
    		@RequestParam(defaultValue="20") int pageSize){
		
		List<UserView> users = new ArrayList<UserView>();
		if(self) {
			users.add(new UserView(loginUser));
		}else {
			loginUser.checkLevel(3);
			List<User> userList = userService.selectListByPage(page, pageSize);
			for(User user : userList) {
				users.add(new UserView(user));
			}
		}
		
        Map<String, Object> result=new HashMap<String, Object>();
        result.put("data", users);
        
        return result;
    }
	
	@RequestMapping(value="/users/login", method=RequestMethod.POST)
    public Object login(@RequestBody String body){
		User user = validation.getObject(body, User.class, new String[]{"mobile", "password"});
		
		User loginInfo = userService.selectByMobile(user.getMobile());
		String ticket = UUID.randomUUID().toString();
		if(loginInfo != null && loginInfo.getPassword().equals(md5Util.string2MD5(user.getPassword()))) {
			System.out.println("key: " + RedisNameSpace.LOGIN + ticket);
			jedisUtil.set(RedisNameSpace.LOGIN + ticket, JSON.toJSONString(loginInfo), RedisNameSpace.LOGIN_TIME);
		}else {
			throw new NeedAuthorizationException("账号或者密码错误");
		}
		
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("loginInfo", new UserView(loginInfo));
		result.put("ticket", ticket);
        return result;
    }*/
}
