package com.economigos.economigostelegram.controller;

import com.economigos.economigostelegram.form.RendaForm;
import org.springframework.web.client.RestTemplate;

public class RendaController {
    public static Boolean createProducts(RendaForm product) {
        try {
            new RestTemplate().postForObject("http://localhost:8080/economigos/rendas", product, RendaForm.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
