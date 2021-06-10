package com.economigos.economigostelegram.utils;

public class ServerConfig {
    public static String getBaseUrl(){
        String baseUrl = "";

        if (System.getenv("SERVICE_URL") != null){
            baseUrl = System.getenv("SERVICE_URL");
        }else{
            //baseUrl = "http://ec2-34-236-53-23.compute-1.amazonaws.com:8080/";
            baseUrl = "http://ip172-18-0-34-c30kh57qf8u000bcbr6g-8080.direct.labs.play-with-docker.com/";
        }

        return baseUrl;
    }
}
