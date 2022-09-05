package com.aeviles.helpdesk.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.secret}")
    private String secret;

    //metodo que gera o token
    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)//define o valor de reinvindicação do nosso token
                .setExpiration(new Date(System.currentTimeMillis()+expiration))//pega em milisegundos o momento atual + 3 seg
                .signWith(SignatureAlgorithm.HS512,secret.getBytes())//secret pega um array de bytes
                .compact();//esse metodo compact compacta o corpo do jwt deixando a nossa API mais perfomrmática.
    }




}
