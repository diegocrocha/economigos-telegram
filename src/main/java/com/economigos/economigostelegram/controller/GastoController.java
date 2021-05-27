package com.economigos.economigostelegram.controller;

import com.economigos.economigostelegram.form.GastoForm;
import com.economigos.economigostelegram.utils.ServerConfig;
import org.springframework.web.client.RestTemplate;

public class GastoController {

    public static Boolean createProducts(GastoForm product) {
        try {
            new RestTemplate().postForObject(ServerConfig.getBaseUrl()+"economigos/gastos", product, GastoForm.class);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

}
