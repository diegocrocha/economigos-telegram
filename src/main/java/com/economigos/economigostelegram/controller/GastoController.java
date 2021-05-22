package com.economigos.economigostelegram.controller;

import com.economigos.economigostelegram.form.GastoForm;
import org.springframework.web.client.RestTemplate;

public class GastoController {

    public static Boolean createProducts(GastoForm product) {
        try {
            new RestTemplate().postForObject("http://ip172-18-0-3-c2k4nqnqf8u000d72cqg-8080.direct.labs.play-with-docker.com/economigos/gastos", product, GastoForm.class);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

}
