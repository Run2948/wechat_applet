package com.platform.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class WXUtils {

    @Autowired
    private static RestTemplate restTemplate;
	
	public static String getAccessToken() {
		
		
		return null;
	}

	public static void main(String[] args) {
		getAccessToken();

	}

}
