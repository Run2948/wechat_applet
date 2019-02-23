
package com.platform.api;


import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.platform.annotation.IgnoreAuth;
import com.platform.entity.MlsUserVo;
import com.platform.service.MlsUserSer;
import com.platform.util.ApiBaseAction;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags="登录与注销接口")
@Controller
@RequestMapping("/api")
public class LogintCtr extends ApiBaseAction{
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private MlsUserSer mlsUserSer;
	
	@ApiOperation(value="用户登录与注册" ,httpMethod="POST")
	@PostMapping("/userLogin")
	@ResponseBody
	@IgnoreAuth
	public Object userLogin(
			@ApiParam(name = "mobile", value = "手机号") @RequestParam String mobile,
			@ApiParam(name = "captcha", value = "手机号") @RequestParam String captcha,HttpServletRequest request){
		Subject subject = SecurityUtils.getSubject();
		if(StringUtils.isEmpty(mobile)) {
			toResponsFail("用户手机号不可为空");
		}
		if (!mlsUserSer.verificationCode(mobile,captcha)) {
			toResponsFail("验证码证错误");
		};
		MlsUserVo mlsUser=new MlsUserVo ();
		mlsUser.setDeviceId("weglapp_"+ UUID.randomUUID());
		mlsUser.setUserTel(mobile);
		int tol=mlsUserSer.getEntityMapper().update(mlsUser);
		if(tol==0) {
			mlsUserSer.insUser(mlsUser);
		}
		mlsUserSer.getEntityMapper().findByUserTel(mobile);
		return toResponsSuccess(mlsUser);
	}

	
	@ApiOperation(value="发送注册验证码" ,httpMethod="POST")
	@RequestMapping("/sendRegisterCode")
	@ResponseBody
	@IgnoreAuth
	public Object register(@ApiParam(name = "mobile", value = "手机号") @RequestParam String mobile) {
		mlsUserSer.sendCode(mobile);
		return toResponsSuccess("发送成功");
	}
	
}

