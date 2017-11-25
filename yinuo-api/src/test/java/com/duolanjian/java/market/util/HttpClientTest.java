package com.duolanjian.java.market.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.duolanjian.java.market.controller.UserController;

public class HttpClientTest {

	private static final String appid= "wx254e077517572b8a";
	
	private static final String appsecret = "d21ea703e91c9cae25dff51d2aeb177b";

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Test
    public void testGet() {
        //百度天气的api
        //String url1 = "http://api.map.baidu.com/telematics/v3/weather?location=%E5%8C%97%E4%BA%AC&output=json&ak=W69oaDTCfuGwzNwmtVvgWfGH";
        //String url1 = "http://localhost:8080/wechat/test/view2/你好世界";
        String url1 = "https://api.weixin.qq.com/sns/jscode2session?appid=wx254e077517572b8a&secret=d21ea703e91c9cae25dff51d2aeb177b&js_code=1&grant_type=authorization_code";
        String result1= "";
        try{
        	result1 = HttpUtil.sendGet(url1);
        }catch(Exception e) {
        	e.printStackTrace();
        }
    	System.out.println(result1);
        //输出{"param":"你好世界"}
    }
	
	@Test
    public void testGet2() {
		String wechatUrl = String.format("https://api.weixin.qq.com/sns/jscode2session"
				+ "?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code", URLEncoder.encode(appid), 
				URLEncoder.encode(appsecret), URLEncoder.encode("1"));
		logger.info("wechatUrl: " + wechatUrl);
		String result1= "";
        try{
        	result1 = HttpsUtil.get(wechatUrl, null);
        }catch(Exception e) {
        	e.printStackTrace();
        }
    	System.out.println(result1);
        //输出{"param":"你好世界"}
    }
	
    @Test
    public void testPost() throws UnsupportedEncodingException{
        String url = "http://localhost:8080/wechat/test/view";
        Map<String,String> map = new HashMap<String,String>();
        map.put("param1", "你好世界");
        map.put("param2", "哈哈");
        String result = HttpUtil.sendPost(url, map);
        System.out.println(result);
        //输出结果{"param1":"你好世界","param2":"哈哈"}

    }

    @Test
    public void testPost1() throws UnsupportedEncodingException{
        String url = "http://localhost:8080/wechat/test/view3";
        String result = HttpUtil.sendPost(url);
        System.out.println(result);
        //输出结果{"status":"success"}

    }

}