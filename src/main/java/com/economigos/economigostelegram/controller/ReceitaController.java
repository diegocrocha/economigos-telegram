package com.economigos.economigostelegram.controller;

import com.economigos.economigostelegram.form.ReceitaForm;
import org.springframework.web.client.RestTemplate;

public class ReceitaController {
    public static Boolean createProducts(ReceitaForm product) {
        try {
            new RestTemplate().postForObject("http://ip172-18-0-3-c2k4nqnqf8u000d72cqg-8080.direct.labs.play-with-docker.com/economigos/rendas", product, ReceitaForm.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
