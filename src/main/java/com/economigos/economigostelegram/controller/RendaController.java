package com.economigos.economigostelegram.controller;

import com.economigos.economigostelegram.form.RendaForm;
import org.springframework.web.client.RestTemplate;

public class RendaController {
    public static Boolean createProducts(RendaForm product) {
        try {
            new RestTemplate().postForObject("http://ip172-18-0-32-c2jhshtmrepg00esej4g-8080.direct.labs.play-with-docker.com/economigos/rendas", product, RendaForm.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
