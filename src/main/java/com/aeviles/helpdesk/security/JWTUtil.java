package com.aeviles.helpdesk.security;

import io.jsonwebtoken.Claims;
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
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())//secret pega um array de bytes
                .compact();//esse metodo compact compacta o corpo do jwt deixando a nossa API mais perfomrmática.
    }

    //Header
    //O Header é um objeto JSON que define informações sobre o tipo do token (typ), nesse caso JWT, e o algorítmo de criptografia
    // usado em sua assinatura (alg), normalmente HMAC SHA256 ou RSA.

    //Payload
    //O Payload é um objeto JSON com as Claims (informações) da entidade tratada, normalmente o usuário autenticado.

    //Essas claims podem ser de 3 tipos:
//
//Reserved claims: atributos não obrigatórios (mas recomendados) que são usados na validação do token pelos protocolos de segurança das APIs.
    //Geralmente os atributos mais utilizados são: sub, iss e exp.
    //
    //Public claims: atributos que usamos em nossas aplicações. Normalmente armazenamos as informações do usuário autenticado na aplicação.
//Private claims: atributos definidos especialmente para compartilhar informações entre aplicações.
    //Por segurança recomenda-se não armazenar informações confidenciais ou sensíveis no token.

    //Signature
    //A assinatura é a concatenação dos hashes gerados a partir do Header e Payload usando base64UrlEncode, com uma chave secreta ou certificado RSA.
//Resultado final
//O resultado final é um token com três seções (header, payload, signature) separadas por “.” — ponto.

    public boolean tokenValido(String token) {
    //o claim é um tipo jwt que armazena as reinvindicações do token, assim conseguimos pegar algumas informações dele
        Claims claims=getClaims(token);
        //se claims for diferente de nulo quer dizer que ele pegou um valor
        if (claims!=null){
            String username = claims.getSubject();//conseguimos pegar o getsUBJECT() no nosso payload , nesse caso srá o e-mail
            Date expirationDate= claims.getExpiration();//pega o tempo de expiração do token
            Date now = new Date(System.currentTimeMillis());//significa o momento atual em milisegundos

            if(username!=null && expirationDate!= null && now.before(expirationDate)){
                return true;
            }
        }
        return false;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJwt(token).getBody();
        }catch (Exception e){
            return  null;

        }
    }

    public String getUsername(String token) {
        Claims claims=getClaims(token);
        if(claims!=null){
            return claims.getSubject();
        }
        return null;

    }
}
