package com.yinuo.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yinuo.bean.User;
import com.yinuo.service.UserService;
import com.yinuo.util.HttpUtil;
import com.yinuo.util.JedisUtil;
import com.yinuo.util.Constant.RedisNameSpace;
import com.yinuo.validation.Validation;

@RestController
public class UserController {
	
	private static final String appid= "wx254e077517572b8a";
	
	private static final String appsecret = "d21ea703e91c9cae25dff51d2aeb177b";

	@Autowired
	private Validation validation;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JedisUtil jedisUtil;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value="/wechatLogin", method=RequestMethod.GET)
    public Object post(@RequestParam(name="code") String code){
		Map<String,Object> result = new HashMap<String, Object>();
		
		String wechatUrl = "";
		try {
			wechatUrl = String.format("https://api.weixin.qq.com/sns/jscode2session"
					+ "?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code", URLEncoder.encode(appid, "utf-8"), 
					URLEncoder.encode(appsecret, "utf-8"), URLEncoder.encode(code, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		logger.info("wechatUrl: " + wechatUrl);
		String response = HttpUtil.sendGet(wechatUrl);
		logger.info("response: " + response);
		
		String ticket = UUID.randomUUID().toString();
		JSONObject jsonObject = JSON.parseObject(response);
		String openid = jsonObject.getString("openid");
		
		User loginUser = userService.selectByOpenid(openid);
		if(loginUser == null) {
			loginUser = new User();
			loginUser.setWechatOpenid(openid);
			loginUser.setCreateTime(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(new Date()));
			userService.insert(loginUser);
			loginUser = userService.selectByOpenid(openid);
		}
		
		jedisUtil.set(RedisNameSpace.LOGIN + ticket, JSON.toJSONString(loginUser), RedisNameSpace.LOGIN_TIME);
		
		//清除之前的缓存
		String oldUuid = jedisUtil.get(openid);
		jedisUtil.del(RedisNameSpace.LOGIN + oldUuid);

		//设置新的缓存
		jedisUtil.set(openid, ticket, RedisNameSpace.LOGIN_TIME);

		result.put("id", ticket);
		result.put("loginUser", loginUser);
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
