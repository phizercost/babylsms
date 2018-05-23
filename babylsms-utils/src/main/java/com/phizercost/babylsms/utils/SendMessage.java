package com.phizercost.babylsms.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.log4j.Logger;
import org.json.JSONObject;




public class SendMessage {
	
	//final static Logger logger = Logger.getLogger(SendMessage.class);
	static String restUrl = "http://*******************************";
	final int sendingDelay = 1000;

	private static HttpPost createConnectivity(String restUrl) {
		
		HttpPost post = new HttpPost(restUrl);
		post.setHeader("AUTHORIZATION", "WvtqENKui885g5sIOu");
		post.setHeader("Content-Type", "application/json");
		post.setHeader("Accept", "application/json");
		return post;
	}
	
	private static String executeReq(String jsonData, HttpPost httpPost) {
		try {
			return executeHttpRequest(jsonData, httpPost);
		} catch (UnsupportedEncodingException e) {
			return ("error while encoding api url : " + e);
		} catch (IOException e) {
			return ("ioException occured while sending http request : " + e);
		} catch (Exception e) {
			return ("exception occured while sending http request : " + e);
		} finally {
			httpPost.releaseConnection();
		}
	}
	
	private static String executeHttpRequest(String jsonData, HttpPost httpPost) throws UnsupportedEncodingException, IOException {
		HttpResponse response = null;
		String line = "";
		StringBuffer result = new StringBuffer();
		httpPost.setEntity(new StringEntity(jsonData));
		HttpClient client = HttpClientBuilder.create().build();
		response = client.execute(httpPost);
		BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		while ((line = reader.readLine()) != null) {
			result.append(line);
		}
		return ""+response.getStatusLine().getStatusCode();
	}
	
	public static String sendMessage(String sender, String receiver, String message){
		String result;
		JSONObject sms = new JSONObject();
		JSONObject details = new JSONObject();
		ArrayList<JSONObject> list = new ArrayList<JSONObject>();
		sms.put("from_number", sender);
		details.put("text", message);
		details.put("number", receiver);
		list.add(details);
		sms.put("messages", list);
		String jsonData = sms.toString();
		HttpPost httpPost = createConnectivity(restUrl);
		result = executeReq(jsonData, httpPost);
		//logger.info("SENDER:"+sender+"|RECEIVER:"+receiver+"|MSG:"+message+"|RESULT:"+result);
		return result;
	}

}
