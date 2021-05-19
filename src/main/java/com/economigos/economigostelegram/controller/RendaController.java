package com.economigos.economigostelegram.controller;

import com.economigos.economigostelegram.form.RendaForm;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/economigos/rendas")
public class RendaController {

    @PostMapping()
    public ResponseEntity<?> createProducts(@RequestBody RendaForm product) {
        try {
            new RestTemplate().postForObject("http://localhost:8080/economigos/rendas", product, RendaForm.class);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
