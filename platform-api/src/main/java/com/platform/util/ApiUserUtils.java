package com.platform.util;

import com.platform.utils.ResourceUtil;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:58<br>
 * 描述: ApiUserUtils <br>
 */
public class ApiUserUtils {

    //替换字符串
    public static String getCode(String APPID, String REDIRECT_URI, String SCOPE) {
        return String.format(ResourceUtil.getConfigByName("wx.getCode"), APPID, REDIRECT_URI, SCOPE);
    }

    //替换字符串
    public static String getWebAccess(String CODE) {
        return String.format(ResourceUtil.getConfigByName("wx.webAccessTokenhttps"),
                ResourceUtil.getConfigByName("wx.appId"),
                ResourceUtil.getConfigByName("wx.secret"),
                CODE);
    }

    //替换字符串
    public static String getUserMessage(String access_token, String openid) {
        return String.format(ResourceUtil.getConfigByName("wx.userMessage"), access_token, openid);
    }
    
    
    //替换字符串
    public static String getAccessToken() {
        return String.format(ResourceUtil.getConfigByName("wx.getAccessToken"),
                ResourceUtil.getConfigByName("wx.appId"),
                ResourceUtil.getConfigByName("wx.secret"));
    }
    
    public static String getWXACodeUnlimit(String accessToken, String scene) {
        return String.format(ResourceUtil.getConfigByName("wx.getWXACodeUnlimit"), accessToken, scene);
    }
    
}