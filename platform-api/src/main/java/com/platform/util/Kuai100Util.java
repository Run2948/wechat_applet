package com.platform.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.platform.utils.DateUtils;
import com.platform.utils.JsonUtil;

public class Kuai100Util {

	public static void main(String[] args) throws Exception {
		Map<String, String> map = getExpressInfo("yuantong", "804554447238426545");
		String ischeck = map.get("ischeck");
		System.out.println(ischeck);
		if("1".equals(ischeck)) {
			System.out.println(map.get("checkTime"));
		}
		
		
		
	}
	
	/**
	 * 获取物流信息
	 * type		快递公司编码（顺风：shufeng）
	 * postid	快递号：1234567890
	 */ 
    public static Map<String, String> getExpressInfo(String type, String postid) {
    	String url = "https://m.kuaidi100.com/query";
    	String param = "type="+type+"&postid="+postid;
        String result = "";
        BufferedReader in = null;
        Map<String, String> resMap = new HashMap<String, String>();
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            System.out.println(result);
            Map map = JsonUtil.getObjet(result, Map.class);
    		String ischeck = map.get("ischeck").toString();
    		//返回是否签收标志
    		resMap.put("ischeck", ischeck);
    		resMap.put("result", result);
    		if("1".equals(ischeck)) {//如果签收查询签收时间
    			List<Map> list = JsonUtil.getList(map.get("data").toString(), Map.class);
    			String checkTime = "2010-01-01 00:00:00";
    			for(Map m : list) {
    				String ftime = m.get("ftime").toString();
    				checkTime = DateUtils.compare_date(checkTime, ftime);
    			}
    			resMap.put("checkTime", checkTime);
    		}
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return resMap;
    }
}
