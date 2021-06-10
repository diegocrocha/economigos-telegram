package com.economigos.economigostelegram.service;

import com.economigos.economigostelegram.utils.ServerConfig;
import org.springframework.web.client.RestTemplate;

public class EconomigosService {

    public static Long requestUsuarioIdByEmail(String email){
        RestTemplate restTemplate = new RestTemplate();
        Long idUsuario = restTemplate.getForObject( ServerConfig.getBaseUrl()+"economigos/usuarios/email?email="+email.trim(), Long.class);

        return idUsuario;
    }

    public static ContaDto requestContaByNome(String contaNome, Long idUsuario){
        RestTemplate restTemplate = new RestTemplate();
        ContaDto contaDto = restTemplate.getForObject( ServerConfig.getBaseUrl()+"economigos/contas/conta?apelido="+contaNome.trim()+"&idUsuario="+idUsuario.toString().trim(), ContaDto.class);

        return contaDto;
    }

    public static CategoriaDto requestCategoriaByNome(String categoriaNome){
        RestTemplate restTemplate = new RestTemplate();
        CategoriaDto categoriaDto = restTemplate.getForObject( ServerConfig.getBaseUrl()+"economigos/categorias/categoria?categoriaNome="+categoriaNome.trim(), CategoriaDto.class);

        return categoriaDto;
    }

}
