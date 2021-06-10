package com.economigos.economigostelegram.utils;

public class ServerConfig {
    public static String getBaseUrl(){
        String baseUrl = "";

        if (System.getenv("SERVICE_URL") != null){
            baseUrl = System.getenv("SERVICE_URL");
        }else{
            baseUrl = "http://ec2-34-236-53-23.compute-1.amazonaws.com:8080/";
            //baseUrl = "http://localhost:8080/";
        }

        return baseUrl;
    }
}
