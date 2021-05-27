package com.economigos.economigostelegram.controller;

import com.economigos.economigostelegram.form.ReceitaForm;
import com.economigos.economigostelegram.utils.ServerConfig;
import org.springframework.web.client.RestTemplate;

public class ReceitaController {
    public static Boolean createProducts(ReceitaForm product) {
        try {
            new RestTemplate().postForObject(ServerConfig.getBaseUrl()+"economigos/rendas", product, ReceitaForm.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
