/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import com.google.gson.Gson;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import static services.HostpinnacleService.PASSWORD;
import static services.HostpinnacleService.USER_ID;
/**
 *
 * @author Codepal
 */
public class ApiUtil {
     
    public static String encodeMap(Map<String, String> map) {
        StringBuilder encodedString = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String encodedKey = URLEncoder.encode(entry.getKey(), "UTF-8");
                String encodedValue = URLEncoder.encode(entry.getValue(), "UTF-8");
                if (encodedString.length() > 0) {
                    encodedString.append("&");
                }
                encodedString.append(encodedKey).append("=").append(encodedValue);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // Handle exception
        }
        return encodedString.toString();
    }
    
    public static void makePostRequest(String endpoint,HashMap<String, String> requestData) {
        try {
            // Create URL object
            URL url = new URL(endpoint);
            
            // Create connection object
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            // Set request method
            connection.setRequestMethod("POST");
            
            // Set basic authorization header
            String auth = USER_ID + ":" + PASSWORD;
            String encodedAuth = java.util.Base64.getEncoder().encodeToString(auth.getBytes());
            connection.setRequestProperty("Authorization", "Basic " + encodedAuth);
            
            // Set content type
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("cache-control", "no-cache");
          
            
            // Enable input and output streams
            connection.setDoOutput(true);
            
            // Create HashMap to store data
       
            
            // Convert HashMap to JSON
//            Gson gson = new Gson();
//            String jsonRequest = gson.toJson(requestData);
//            
//            // Write JSON data to the output stream
//            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
//                outputStream.writeBytes(jsonRequest);
//                outputStream.flush();
//            }

           
            String data = encodeMap(requestData);
            
            // Write JSON data to the output stream
            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.writeBytes(data);
                outputStream.flush();
            }
            
            // Get response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            System.out.println("Response Code: " + connection.getResponseMessage());
            // Handle response here if needed
            
            // Close connection
            connection.disconnect();
            
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception
        }
    }
}
