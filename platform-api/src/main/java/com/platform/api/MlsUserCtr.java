
package com.platform.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.platform.annotation.APPLoginUser;
import com.platform.entity.MlsUserVo;
import com.platform.service.MlsUserSer;
import com.platform.util.ApiBaseAction;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 分销用户<br>
 */
@Api(tags="分销用户")
@Controller
@RequestMapping("/api/mlsuser")
public class MlsUserCtr extends ApiBaseAction{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MlsUserSer mlsUserSer;
	@RequestMapping("/getCurrentUser")
	@ResponseBody
	@ApiOperation(value="取当前登录用户信息" ,httpMethod="POST",tags="个人中心")
	public Object getCurrentUser(@APPLoginUser MlsUserVo mlsuser){
		mlsuser=mlsUserSer.getEntityMapper().getById(mlsuser.getMlsUserId());
		return toResponsSuccess(mlsuser);
	}
}

