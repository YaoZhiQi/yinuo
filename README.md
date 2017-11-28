# yinuo


接口设计

环境ip：47.100.24.40 端口：80

wechat登陆
	request/get: /wechatLogin?code=${res.code}

	response
		{
			"id": "e4911dba-e7f7-4e08-a59c-4d243355098d",
			"loginUser": {
				"birthday": "1970-01-01 08:00:00",
				"createTime": "2017-11-08 20:48:18",
				"id": 1,
				"sex": 0,
				"wechatNickname": "",
				"wechatOpenid": "onIX60AX6xG8RsqnPr1b"
			}
		}
	
		以后每次请求，在header中带上，key:ticket, value:${id}

更新用户信息
	request/put: /users

		{
			"id": 1,
			"birthday": "1970-01-01 08:00:00",
			"sex": 1,	//1男，2女
			"wechatNickname": "微信昵称",
		}

	response:
		{
			"id": 1
		}