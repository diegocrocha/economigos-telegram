package com.economigos.economigostelegram.service;

import org.springframework.web.client.RestTemplate;

public class EconomigosService {

    public static ContaDto requestContaByNome(String contaNome){
        RestTemplate restTemplate = new RestTemplate();
        ContaDto contaDto = restTemplate.getForObject( "http://ip172-18-0-3-c2k4nqnqf8u000d72cqg-8080.direct.labs.play-with-docker.com" + "/economigos/contas/conta?apelido="+contaNome.trim(), ContaDto.class);

        return contaDto;
    }

    public static CategoriaDto requestCategoriaByNome(String categoriaNome){
        RestTemplate restTemplate = new RestTemplate();
        CategoriaDto categoriaDto = restTemplate.getForObject( "http://ip172-18-0-3-c2k4nqnqf8u000d72cqg-8080.direct.labs.play-with-docker.com" + "/economigos/categorias/categoria?categoriaNome="+categoriaNome.trim(), CategoriaDto.class);

        return categoriaDto;
    }

}
