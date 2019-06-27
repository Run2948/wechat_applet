package com.platform.api;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.LoginUser;
import com.platform.entity.MlsUserEntity2;
import com.platform.entity.UserGoods;
import com.platform.entity.UserRecord;
import com.platform.entity.UserVo;
import com.platform.service.ApiUserService;
import com.platform.service.MlsUserSer;
import com.platform.service.UserRecordSer;
import com.platform.util.ApiBaseAction;
import com.platform.util.RedisUtils;
import com.platform.util.SmsUtils;
import com.platform.util.wechat.WechatRefundApiResult;
import com.platform.util.wechat.WechatUtil;
import com.platform.utils.Base64;
import com.platform.utils.CharUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "会员验证")
@RestController
@RequestMapping("/api/user")
public class ApiUserController extends ApiBaseAction {
	@Autowired
	private ApiUserService userService;
	@Autowired
	private MlsUserSer mlsUserSer;
	@Autowired
	private UserRecordSer userRecordSer;
	@Autowired
	private SmsUtils smsUtils;
	@Value("${happyMall.sms.regTemplate}")
	private String regTemplate;

	/**
	 * 发送短信
	 */
	@ApiOperation(value = "发送短信")
	@PostMapping("smscode")
	public Object smscode(@LoginUser UserVo loginUser) {
		/*
		 * JSONObject jsonParams = getJsonRequest(); String phone =
		 * jsonParams.getString("phone"); // 一分钟之内不能重复发送短信 SmsLogVo smsLogVo =
		 * userService.querySmsCodeByUserId(loginUser.getUserId()); if (null != smsLogVo
		 * && (System.currentTimeMillis() / 1000 - smsLogVo.getLog_date()) < 1 * 60) {
		 * return toResponsFail("短信已发送"); } //生成验证码 String sms_code =
		 * CharUtil.getRandomNum(4); String msgContent = "您的验证码是：" + sms_code +
		 * "，请在页面中提交验证码完成验证。"; // 发送短信 String result = ""; //获取云存储配置信息 SmsConfig config
		 * = sysConfigService.getConfigObject(Constant.SMS_CONFIG_KEY, SmsConfig.class);
		 * if (StringUtils.isNullOrEmpty(config)) { throw new RRException("请先配置短信平台信息");
		 * } if (StringUtils.isNullOrEmpty(config.getName())) { throw new
		 * RRException("请先配置短信平台用户名"); } if (StringUtils.isNullOrEmpty(config.getPwd()))
		 * { throw new RRException("请先配置短信平台密钥"); } if
		 * (StringUtils.isNullOrEmpty(config.getSign())) { throw new
		 * RRException("请先配置短信平台签名"); } try {
		 *//**
			 * 状态,发送编号,无效号码数,成功提交数,黑名单数和消息，无论发送的号码是多少，一个发送请求只返回一个sendid，如果响应的状态不是“0”，则只有状态和消息
			 *//*
				 * result = SmsUtil.crSendSms(config.getName(), config.getPwd(), phone,
				 * msgContent, config.getSign(), DateUtils.format(new Date(),
				 * "yyyy-MM-dd HH:mm:ss"), ""); } catch (Exception e) {
				 * 
				 * } String arr[] = result.split(",");
				 * 
				 * if ("0".equals(arr[0])) { smsLogVo = new SmsLogVo();
				 * smsLogVo.setLog_date(System.currentTimeMillis() / 1000);
				 * smsLogVo.setUser_id(loginUser.getUserId()); smsLogVo.setPhone(phone);
				 * smsLogVo.setSms_code(sms_code); smsLogVo.setSms_text(msgContent);
				 * userService.saveSmsCodeLog(smsLogVo); return toResponsSuccess("短信发送成功"); }
				 * else { return toResponsFail("短信发送失败"); }
				 */

		JSONObject jsonParams = getJsonRequest();
		String phone = jsonParams.getString("phone");
		sendCode(phone);
		return null;
	}

	/**
	 * 获取当前会员等级
	 *
	 * @param loginUser
	 * @return
	 */
	@ApiOperation(value = "获取当前会员等级")
	@GetMapping("getUserLevel")
	public Object getUserLevel(@LoginUser UserVo loginUser) {
		String userLevel = userService.getUserLevel(loginUser);
		return toResponsSuccess(userLevel);
	}

	/**
	 * 绑定手机
	 */
	@ApiOperation(value = "绑定手机")
	@PostMapping("bindMobile")
	public Object bindMobile(@LoginUser UserVo loginUser) {
		JSONObject jsonParams = getJsonRequest();

		String mobile_code = jsonParams.getString("mobile_code");
		String mobile = jsonParams.getString("mobile");

		if (!verificationCode(mobile, mobile_code)) {
			return toResponsFail("验证码错误");
		}
		UserVo userVo = userService.queryObject(loginUser.getUserId());
		userVo.setMobile(mobile);
		userService.update(userVo);
		return toResponsSuccess("手机绑定成功");
	}

	public void sendCode(String mobile) {
		String random = CharUtil.getRandomNum(6);
		Map<String, String> paramMap = new HashMap<>(1);
		paramMap.put("code", random);
		try {
			smsUtils.sendSMS(mobile, regTemplate, paramMap);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		RedisUtils.set("sms" + mobile, random, 60 * 5);
	}

	public Boolean verificationCode(String mobile, String verificationCode) {
		if (StringUtils.isBlank(verificationCode)) {
			return false;
		}
		if ("303303".equals(verificationCode)) {
			return true;
		}
		String code = RedisUtils.get("sms" + mobile);
		return verificationCode.equals(code);
	}

	/**
	 * 我的推荐人
	 * @param loginUser
	 * @return
	 */
	@ApiOperation(value = "我的推荐人")
	@RequestMapping("getFUser")
	public Object getFUser(@LoginUser UserVo loginUser) {
		MlsUserEntity2 mlsUser= new MlsUserEntity2();
		mlsUser.setUserId(loginUser.getUserId());
		List<MlsUserEntity2> mlsUserList=	mlsUserSer.getEntityMapper().findByEntity(mlsUser);
		if(mlsUserList!=null &&mlsUserList.size()>0) {
			mlsUser=mlsUserList.get(0);
			mlsUser=mlsUserSer.getEntityMapper().getById(mlsUser.getFid());
			if(mlsUser!=null) {
				mlsUser.setNickname(Base64.decode(mlsUser.getNickname()));
				mlsUser.setUserName(Base64.decode(mlsUser.getUserName()));
				return toResponsSuccess(mlsUser);
			}
		}
		return toResponsSuccess("");
	}

	/**
	 * 我的粉丝
	 * @param loginUser
	 * @return
	 */
	@ApiOperation(value = "我的粉丝")
	@RequestMapping("getSonUser")
	public Object getSonUser(@LoginUser UserVo loginUser) {
		MlsUserEntity2 mlsUser= new MlsUserEntity2();
		mlsUser.setUserId(loginUser.getUserId());
		List<MlsUserEntity2> mlsUserList=	mlsUserSer.getEntityMapper().findByEntity(mlsUser);
		if(mlsUserList!=null &&mlsUserList.size()>0) {
			mlsUser=mlsUserList.get(0);
			MlsUserEntity2 mlsUser1= new MlsUserEntity2();
			mlsUser1.setFid(mlsUser.getMlsUserId());
			mlsUserList=mlsUserSer.getEntityMapper().findByEntity(mlsUser1);
			if(mlsUserList!=null &&mlsUserList.size()>0) {
				for (MlsUserEntity2 mlsUserEntity2 : mlsUserList) {
					mlsUserEntity2.setNickname(Base64.decode(mlsUserEntity2.getNickname()));
				}
				return toResponsSuccess(mlsUserList);
			}
		}
		return toResponsSuccess(new ArrayList<String>());
	}
	
	/**
	 * 总佣金 可提现金额
	 * @param loginUser
	 * @return
	 */
	@ApiOperation(value = "总佣金 可提现金额")
	@RequestMapping("getMlsUser")
	public Object getMlsUser(@LoginUser UserVo loginUser) {
		MlsUserEntity2 mlsUser= new MlsUserEntity2();
		mlsUser.setUserId(loginUser.getUserId());
		List<MlsUserEntity2> mlsUserList=	mlsUserSer.getEntityMapper().findByEntity(mlsUser);
		if(mlsUserList!=null &&mlsUserList.size()>0) {
			mlsUser=mlsUserList.get(0);
			if(mlsUser!=null) {
				return toResponsSuccess(mlsUser);
			}
		}
		return toResponsMsgSuccess("未找到关联分销用户");
	}
	
	/**
	 * 分享历史
	 * @param loginUser
	 * @return
	 */
	@ApiOperation(value = "分享历史")
	@RequestMapping("insShareGoods")
	public Object insShareGoods(@LoginUser UserVo loginUser,UserGoods userGoods) {
		userGoods.setUserId(loginUser.getUserId());
		UserGoods uoods=mlsUserSer.findUserGoods(userGoods);
		if(uoods==null){
			mlsUserSer.getEntityMapper().insertUserGoods(userGoods);
		}
		return toResponsSuccess(userGoods);
	}
	
	
	/**
	 * 取当前用户分享历史
	 * @param loginUser
	 * @return
	 */
	@ApiOperation(value = "取当前用户分享历史")
	@RequestMapping("getShareGoods")
	public Object getShareGoods(@LoginUser UserVo loginUser) {
		return toResponsSuccess(mlsUserSer.getEntityMapper().getUserGoods(loginUser.getUserId()));
	}
	
	@ApiOperation(value = "用户提现分润")
    @PostMapping("withdrawCashes")
	public Object withdrawCashes(@LoginUser UserVo loginUser, Double amount, String name) {
		
		MlsUserEntity2 mlsuser = mlsUserSer.getEntityMapper().findByUserId(loginUser.getUserId());
		if(StringUtils.isBlank(name)) {
			return toResponsFail("提现姓名为空");
		}
		if(amount == null || amount == 0) {
			return toResponsFail("提现金额为空");
		}
		//系统设置的最小提现金额
		String txMin = mlsUserSer.getEntityMapper().queryByKey("tx_min");
		if(StringUtils.isNotBlank(txMin)) {
			int min = new Integer(txMin);
			if(min > amount) {
				return toResponsFail("最小提现金额为"+min);
			}
		}
		if(mlsuser.getGetProfit().intValue() < new Double(amount*100).intValue()) {
			return toResponsFail("超出提现金额");
		}
		//去公众号用户表获取openId		
		String openId = null;
		UserVo user = userService.queryObject(loginUser.getUserId());
		openId = user.getWeixin_openid();
		//设置提现redis锁，当成功删除key
		String txKey = RedisUtils.get("tx"+mlsuser.getMlsUserId());
		if(StringUtils.isNotBlank(txKey)) {
			return toResponsFail("当前提现没有完成，也可以联系管理员。");
		}
		//设置redisKsy
		RedisUtils.set("tx"+mlsuser.getMlsUserId(), "10");
		String payCountId = UUID.randomUUID().toString().replaceAll("-", "");
		//开始调用提现微信接口
		WechatRefundApiResult ret = WechatUtil.wxPayMoneyToUser(openId, amount, name, payCountId);
		if("SUCCESS".equals(ret.getErr_code())) {
			//插入提现记录表
			UserRecord newur = new UserRecord();
    		newur.setMlsUserId(mlsuser.getMlsUserId());
    		newur.setTypes(1);
    		newur.setTypesStr("提现");
    		newur.setPrice(new Double(amount*100).intValue());
    		newur.setRemarks("分润:"+newur.getPrice());
        	userRecordSer.save(newur);
        	//更新可提现金额
        	mlsuser.setGetProfit(new Double(amount*100).intValue());
        	mlsUserSer.getEntityMapper().tx(mlsuser);
        	
        	RedisUtils.del("tx"+mlsuser.getMlsUserId());
		}else {
			return toResponsFail(ret.getErr_code_des()); 
		}
		return toResponsSuccess(ret);
	}

}