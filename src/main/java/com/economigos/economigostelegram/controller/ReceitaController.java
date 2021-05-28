package com.economigos.economigostelegram.controller;

import com.economigos.economigostelegram.form.ReceitaForm;
import org.springframework.web.client.RestTemplate;

public class ReceitaController {
    public static Boolean createProducts(ReceitaForm product) {
        try {
            new RestTemplate().postForObject("http://ip172-18-0-33-c2ol9pfnjsv000e5o7p0-8080.direct.labs.play-with-docker.com/" + "economigos/rendas", product, ReceitaForm.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
