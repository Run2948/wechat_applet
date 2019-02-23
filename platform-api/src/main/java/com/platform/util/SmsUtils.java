package com.platform.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 短信发送工具类
 */
@Service
public class SmsUtils {
	
	 private Logger logger = LoggerFactory.getLogger(getClass());	
	//产品名称:云通信短信API产品,开发者无需替换
      String product = "Dysmsapi";
    //产品域名,开发者无需替换
      String domain = "dysmsapi.aliyuncs.com";
      
      
	@Value("${happyMall.sms.accessKeyId}")
	private String accessKeyId;
	@Value("${happyMall.sms.accessKeySecret}")
	private String accessKeySecret;
	@Value("${happyMall.sms.sign}")
	private String sign;
	
	

    /**
     * 发送短信
     * @param receiver		接受者手机号
     * @param templateCode	模板ID
     * @param paramMap		模板参数1,模板参数2,.....
     */
	public  void sendSMS(String receiver, String templateCode,  Map<String, String> paramMap){
//		long start = System.currentTimeMillis();
//		logger.info("【阿里云短信】 短信开始发送----begin----");
		if(StringUtils.isBlank(receiver)){
			logger.info("【阿里云短信】上送的手机号为空");
			return ;
		}else if(StringUtils.isBlank(templateCode)){
			logger.info("【阿里云短信】上送的短信模板ID为空");
			return ;
		}
		 
        ObjectMapper objectMapper = new ObjectMapper();
		IAcsClient acsClient = null;
        try {
        	//可自助调整超时时间
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");

            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
			acsClient = new DefaultAcsClient(profile);
		} catch (ClientException e1) {
			logger.error("【阿里云短信】初始化阿里云短信的acsClient错误");
			throw new RuntimeException(e1);
		}
       
       
		String dataResult = "";
        //填充参数
        if(null != paramMap){
            try{
				dataResult = objectMapper.writeValueAsString(paramMap);
			} catch (JsonProcessingException e) {
				logger.error("【阿里云短信】对象转换JSON格式出错");
				throw new RuntimeException(e);
			}
        }
        
//        logger.info("【阿里云短信】 短信签名：" + sign);
//		logger.info("【阿里云短信】 短信模板ID：" + templateCode);
//		logger.info("【阿里云短信】 短信模板-接受人：" + receiver);
//		logger.info("【阿里云短信】 短信模板JSON数据：" + dataResult);
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(receiver);	 							//必填:待发送手机号
        request.setSignName(sign);					//必填:短信签名-可在短信控制台中找到
        request.setTemplateCode(templateCode);							//必填:短信模板-可在短信控制台中找到
        if(StringUtils.isNotBlank(dataResult)){
        	//可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        	request.setTemplateParam(dataResult);
        }
        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        //request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = null;
        try {
        	sendSmsResponse = acsClient.getAcsResponse(request);
			//发短信
//			logger.info("【阿里云短信】------短信接口返回的数据--------");
//			logger.info("【阿里云短信】 Code=" + sendSmsResponse.getCode());
//			logger.info("【阿里云短信】 Message=" + sendSmsResponse.getMessage());
//			logger.info("【阿里云短信】 RequestId=" + sendSmsResponse.getRequestId());
//			logger.info("【阿里云短信】 BizId=" + sendSmsResponse.getBizId());
//			long end = System.currentTimeMillis()-start;
//			logger.info("【阿里云短信】 短信发送结束----end----耗时：" + end +" 毫秒");
			if(!"OK".equals(sendSmsResponse.getCode())){//发送失败
				throw new RuntimeException(sendSmsResponse.getMessage());
			}
		} catch (Exception e) {
			logger.error("【阿里云短信】短信发送失败！",e);
			throw new RuntimeException(e);
		}
	}
}
