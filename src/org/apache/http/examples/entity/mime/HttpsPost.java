package org.apache.http.examples.entity.mime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpsPost {
	 public static void post(String httpsUrl, String xmlStr) {  
	        HttpsURLConnection urlCon = null;  
	        try {  
	        	URL u = new URL(httpsUrl);
	        	urlCon = (HttpsURLConnection)u.openConnection();
//	            urlCon = (HttpsURLConnection) (new URL(httpsUrl)).openConnection();  
	            urlCon.setDoInput(true);  
	            urlCon.setDoOutput(true);  
	            urlCon.setRequestMethod("POST");  
	            urlCon.setRequestProperty("Content-Length",  
	                    String.valueOf(xmlStr.getBytes().length));  
	            urlCon.setUseCaches(false);  
	            urlCon.getOutputStream().write(xmlStr.getBytes("utf-8"));  
	            urlCon.getOutputStream().flush();  
	            urlCon.getOutputStream().close();  
	            BufferedReader in = new BufferedReader(new InputStreamReader(  
	                    urlCon.getInputStream()));  
	            String line;  
	            while ((line = in.readLine()) != null) {  
	                System.out.println(line);  
	            }  
	        } catch (MalformedURLException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
	 public static void main(String[] args) throws Exception {  
	        // 本地起的https服务  
	        String httpsUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";  
	        // 传输文本  
	        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><fruitShop><fruits><fruit><kind>萝卜</kind></fruit><fruit><kind>菠萝</kind></fruit></fruits></fruitShop>";  
	        HttpsPost.post(httpsUrl, xmlStr);  
	    }  
}
