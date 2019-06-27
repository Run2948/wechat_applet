
package com.platform.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.platform.annotation.APPLoginUser;
import com.platform.annotation.IgnoreAuth;
import com.platform.entity.MlsUserEntity2;
import com.platform.entity.UserRecord;
import com.platform.service.UserRecordSer;
import com.platform.util.ApiBaseAction;
import com.platform.util.ApiPageUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 分销用户<br>
 */
@Api(tags="分销用户")
@Controller
@RequestMapping("/api/userRecord")
public class UserRecordCtr extends ApiBaseAction{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserRecordSer userRecordSer;
	
	 /**
     * 分页获取品牌
     */
    @ApiOperation(value = "分页获取品牌")
    @IgnoreAuth
    @RequestMapping("list")
    @ResponseBody
    public Object list(UserRecord userRecord,@APPLoginUser MlsUserEntity2 mlsuser) {
    	if(userRecord.getPage()==null) {
    		userRecord.setPage(1);
    		userRecord.setSize(10);
    	}
    	userRecord.setOffset((userRecord.getPage()-1)*userRecord.getSize());
    	
    	userRecord.setMlsUserId(mlsuser.getMlsUserId());
    	List<UserRecord> rliset=userRecordSer.getEntityMapper().findByEntity(userRecord);
        Long total = userRecordSer.getEntityMapper().findByEntity_count(userRecord);
        ApiPageUtils pageUtil = new ApiPageUtils(rliset, total.intValue(), userRecord.getSize(), userRecord.getPage());
        return toResponsSuccess(pageUtil);
    }
}

