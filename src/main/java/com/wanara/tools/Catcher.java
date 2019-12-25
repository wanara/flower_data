package com.wanara.tools;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Catcher {
	private Map<String, String> requestMap;
	private String connectMethod;
	public byte[] Download(String connect) {
		byte[] result = null;
		try {
			URL url = new URL(connect);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("Accept", "*/*");
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36");
			if(connectMethod != null) con.setRequestMethod(connectMethod);
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
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  result;
	}
	public void setRequestMap(Map<String, String> requestMap){
		this.requestMap = requestMap;
	}
	public static Catcher build(){
		return new Catcher();
	}
}
