/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Codepal
 */
public class HostpinnacleService {
    private static final String BASE_URL = "http://54.247.191.102/restapi/sms/1/text/";
    public static final String SEND_SINGLE_URL = BASE_URL+"/single";
    public static final String SEND_BULK_URL = BASE_URL+"/single";
    public static final String ADD_CREDIT_URL = BASE_URL+"/single";
    public static final String CHECK_CREDIT_URL = BASE_URL+"/single";
    public static final String REPORTS_URL = BASE_URL+"/reports"; //once
    public static final String LOGS_URL = BASE_URL+"/logs"; //multiple times
    
    public static final String USER_ID = "codejar";
    public static final String PASSWORD = "cP0Uasc9"; 
    
    public static final String MSG_TYPE = "text";
    public static final String SENDER_ID = "HPKSMS";
    public static final String API_KEY = "675fea5b95bb39de61022d3b564226abc2fae7e9";
    
    
    public static void sendSingleSMS(String to, String text){
        HashMap<String, String> requestData = new HashMap<>();
        requestData.put("apikey", API_KEY);
        requestData.put("userid", USER_ID);
        requestData.put("password", PASSWORD);
        requestData.put("mobile", to);
        requestData.put("msg", text);
        
        requestData.put("sendMethod", "quick");
        requestData.put("msgType", MSG_TYPE);
        requestData.put("senderid", SENDER_ID);
        requestData.put("duplicatecheck", "true");
        requestData.put("output", "true");
        
        ApiUtil.makePostRequest(SEND_SINGLE_URL, requestData);
    }
    
    public static void sendBulkSMS(String from, String to, String text){
           
            
    }
    
    
    public static void checkCreditBalance(){
        
    }
    
    public static void addSMSCredit(){
        
    }
    
    public static void main(String[] args){
        try{
            Date mydate = new Date(System.currentTimeMillis());
            String data = "";
            data += "sendMethod=quick";
            data += "&userid="+SENDER_ID;
            data += "&password=" + URLEncoder.encode(PASSWORD, "UTF-8");
            data += "&msg=" + URLEncoder.encode("Good morning Jared" , "UTF-8");
            data += "&mobile=" + URLEncoder.encode("254742433934", "UTF-8");
            data += "&msgType="+MSG_TYPE;
            data += "&senderid="+SENDER_ID;
//            data += "&dltEntityId=xxxxxxxxxxxx";
//            data += "&dltTemplateId=xxxxxxxxxxxx";
            data += "&output=json";
            URL url = new URL("https://smsportal.hostpinnacle.co.ke/SMSApi/send?" + data);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false); conn.connect();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder buffer = new StringBuilder();
            while((line = rd.readLine()) != null){
                buffer.append(line).append("\n");
            }
            System.out.println(buffer.toString());
            rd.close();
            conn.disconnect();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
//   public static void main(String[] args) {
//		// Your apikey key
//		
//
//		// Message type text/unicode/flash
//		
//
//		// Multiple mobiles numbers separated by comma
//		String mobile = "254742433934";
//		
//		// Your message to terminate, URLEncode the content
//		String msg = "This is a test message in Java";
//		// DLT PE ID
//		String dltEntityId = "xxxxxxxxxxxxx";
//		// DLT Template ID
//		String dltTemplateId = "xxxxxxxxxxxxx";
//		// response format
//		String output = "json";
//
//		// Prepare Url
//		URLConnection myURLConnection = null;
//		URL myURL = null;
//		BufferedReader reader = null;
//
//		// URL encode message
//		String urlencodedmsg = "";
//		try {
//			urlencodedmsg = URLEncoder.encode(msg, "UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			// TODO Auto-generated catch block
//			System.out.println("Exception while encoding msg");
//			e1.printStackTrace();
//		}
//
//		// API End Point
//		String mainUrl = "https://smsportal.hostpinnacle.co.ke/SMSApi?";
//
//		// API Paramters
//		StringBuilder sendSmsData = new StringBuilder(mainUrl);
//		sendSmsData.append("apikey=" + API_KEY);
//		sendSmsData.append("&userid=" + USER_ID);
//		sendSmsData.append("&password=" + PASSWORD);
//		sendSmsData.append("&type=" + msgType);
//		sendSmsData.append("&mobile=" + mobile);
//		sendSmsData.append("&senderid=" + senderId);
//		sendSmsData.append("&text=" + urlencodedmsg);
//
//		sendSmsData.append("&dltEntityId=" + dltEntityId);
//		sendSmsData.append("&dltTemplateId=" + dltTemplateId);
//		sendSmsData.append("&output=" + output);
//		// final string
//		mainUrl = sendSmsData.toString();
//		try {
//			// prepare connection
//			myURL = new URL(mainUrl);
//			myURLConnection = myURL.openConnection();
//			myURLConnection.connect();
//			reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
//			// reading response
//			String response;
//			while ((response = reader.readLine()) != null)
//				// print response
//				System.out.println(response);
//
//			// finally close connection
//			reader.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
