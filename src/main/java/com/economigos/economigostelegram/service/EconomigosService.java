package com.economigos.economigostelegram.service;

import com.economigos.economigostelegram.utils.ServerConfig;
import org.springframework.web.client.RestTemplate;

public class EconomigosService {

    public static ContaDto requestContaByNome(String contaNome){
        RestTemplate restTemplate = new RestTemplate();
        ContaDto contaDto = restTemplate.getForObject( ServerConfig.getBaseUrl()+"economigos/contas/conta?apelido="+contaNome.trim(), ContaDto.class);

        return contaDto;
    }

    public static CategoriaDto requestCategoriaByNome(String categoriaNome){
        RestTemplate restTemplate = new RestTemplate();
        CategoriaDto categoriaDto = restTemplate.getForObject( ServerConfig.getBaseUrl()+"economigos/categorias/categoria?categoriaNome="+categoriaNome.trim(), CategoriaDto.class);

        return categoriaDto;
    }

}
