package com.platform.util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

public class QRCodeUtils {

	public static String getToken() {
		try {

			Map<String, String> map = new LinkedHashMap<String, String>();
			map.put("grant_type", "client_credential");
			map.put("appid", "wx12607975e1d6f460");// 改成自己的appid
			map.put("secret", "48a3c1fac8c6cdee529b4134846f5515");

			String rt = UrlUtilA.sendPost("https://api.weixin.qq.com/cgi-bin/token", map);

			System.out.println("what is:" + rt);
			JSONObject json = JSONObject.parseObject(rt);

			if (json.getString("access_token") != null || json.getString("access_token") != "") {
				return json.getString("access_token");
			} else {
				return null;
			}
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}

	}

	/*
	 * 获取 二维码图片
	 *
	 */
	public static String getminiqrQr(String accessToken, HttpServletRequest request, String userId) {
		String imagePath = request.getContextPath();
		System.out.println(imagePath);
		// String p="C:\\Users\\zhou_\\Pictures";
		String urlPath = System.getProperty("catalina.home");
		System.out.println(System.getProperty("user.dir"));
		System.out.println(System.getProperty("catalina.home"));
		String twoCodePath = "/" + userId + ".png";
		String codeUrl = urlPath + "/webapps/ROOT/statics/qrdir" + twoCodePath;
		urlPath = urlPath + "/webapps/ROOT/statics/qrdir";
		File file = new File(urlPath);
		dirExists(file);
		try {
			URL url = new URL("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("POST");// 提交模式
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
			// 发送请求参数
			JSONObject paramJson = new JSONObject();
			paramJson.put("scene", "1234567890");
			paramJson.put("path", "pages/index?userId=" + userId);
			paramJson.put("width", 430);
			paramJson.put("is_hyaline", true);
			paramJson.put("auto_color", true);
			/**
			 * line_color生效 paramJson.put("auto_color", false); JSONObject lineColor = new
			 * JSONObject(); lineColor.put("r", 0); lineColor.put("g", 0);
			 * lineColor.put("b", 0); paramJson.put("line_color", lineColor);
			 */

			printWriter.write(paramJson.toString());
			// flush输出流的缓冲
			printWriter.flush();
			// 开始获取数据
			BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
			OutputStream os = new FileOutputStream(new File(codeUrl));
			int len;
			byte[] arr = new byte[1024];
			while ((len = bis.read(arr)) != -1) {
				os.write(arr, 0, len);
				os.flush();
			}
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "qrdir/" + twoCodePath;
	}
	
	
	/*
	 * 获取小程序商品分享二维码
	 *
	 */
	public static BufferedInputStream getGoodQrCode(String accessToken, Long userId, Integer goodId) {
		BufferedInputStream bis = null;
		try {
			URL url = new URL("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("POST");// 提交模式
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
			// 发送请求参数
			JSONObject paramJson = new JSONObject();
			paramJson.put("scene", "id=" + goodId + "&userId=" + userId);
			paramJson.put("page", "pages/goods/goods");
			paramJson.put("width", 280);//最小280
			paramJson.put("is_hyaline", false);
			paramJson.put("auto_color", true);
			/**
			 * line_color生效 paramJson.put("auto_color", false); JSONObject lineColor = new
			 * JSONObject(); lineColor.put("r", 0); lineColor.put("g", 0);
			 * lineColor.put("b", 0); paramJson.put("line_color", lineColor);
			 */

			printWriter.write(paramJson.toString());
			// flush输出流的缓冲
			printWriter.flush();
			// 开始获取数据
			bis = new BufferedInputStream(httpURLConnection.getInputStream());
//			OutputStream os = new FileOutputStream(new File(codeUrl));
//			int len;
//			byte[] arr = new byte[1024];
//			while ((len = bis.read(arr)) != -1) {
//				os.write(arr, 0, len);
//				os.flush();
//			}
//			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bis;
	}

	// 判断文件夹是否存在
	public static void dirExists(File file) {

		if (file.exists()) {
			if (file.isDirectory()) {
				System.out.println("dir exists");
			} else {
				System.out.println("the same name file exists, can not create dir");
			}
		} else {
			System.out.println("dir not exists, create it ...");
			file.mkdirs();  
		}

	}
	
	// 判断文件夹是否存在
	public static Boolean fileExists(File file) {

		if (file.exists()) {
			return true;
		} else {
			return false; 
		}

	}

}