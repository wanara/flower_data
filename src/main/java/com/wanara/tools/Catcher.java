package com.wanara.tools;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Catcher {
	private Map<String, String> requestMap;
	private String requestMethod;
	public byte[] download(String connect) {
		byte[] result = null;
		try {
			URL url = new URL(connect);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("Accept", "*/*");
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36");
			if(requestMethod != null) con.setRequestMethod(requestMethod);
			if(requestMap != null && requestMap.size() > 0){
				for (String key:
					 requestMap.keySet()) {
					con.setRequestProperty(key, requestMap.get(key));
				}
			}

			Map<String, List<String>> headers = con.getHeaderFields();
			for (String key : headers.keySet()) {
				if(key == null) {
					System.out.println(headers.get(null));
					continue;
				}
				List<String> header = headers.get(key);
				System.out.println(key + " ：" + header);
			}
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				result = CommonUtil.readInputStream(con.getInputStream());
			}
			con.disconnect();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return  result;
	}
	public Catcher setRequestMap(Map<String, String> requestMap){
		this.requestMap = requestMap;
		return this;
	}
	public Catcher setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
		return this;
	}
	public static Catcher newInstance(){
		return new Catcher();
	}
}
