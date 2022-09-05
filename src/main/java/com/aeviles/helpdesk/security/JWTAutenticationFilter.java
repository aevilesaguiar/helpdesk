package com.aeviles.helpdesk.security;

import com.aeviles.helpdesk.domain.dtos.CredenciaisDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

//Criar um filtro de Autenticação JWT
public class JWTAutenticationFilter extends UsernamePasswordAuthenticationFilter {
//quando eu extendo a classe UsernamePasswordAuthenticationFilter automaticamente o spring vai entender que esse filtro vai e=interceptar
// a requisição POST lá do endpoint/login que inclusive é um endpoint reservado do SpringSecurity


    //AuthenticationManger é a principal interface para estratégia de autenticação, se o principal(usuario e senha) da autenticação de entrada for válido
    //o método que ele possui chamado autenticat retorna uma instancia de autenticat com um sinalizador de autenticado
    //definido como verdadeiro, do contrário se o principal(é o usuario e senha) não for válido esse sinalizador retorna um
    //valor nulo, ou seja ele não vai poder decidir se está autenticado ou não;
    private AuthenticationManager authenticationManager;

    //precisamos porque tem o metodo generatedToken, ou seja caso consiga autenticar agente chama o metodo generatedToken
    private JWTUtil jwtUtil;


    public JWTAutenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    //tentaiva de autentication
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            CredenciaisDTO credenciaisDTO=new ObjectMapper().readValue(request.getInputStream(), CredenciaisDTO.class);
            UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(credenciaisDTO.getEmail(),credenciaisDTO.getSenha(), new ArrayList<>());
            Authentication authentication=authenticationManager.authenticate(authenticationToken);
            return authentication;

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    //caso a autenticação ocorra com sucesso ele entra nesse caso
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String username=((UserSS) authResult.getPrincipal()).getUsername();
        String token= jwtUtil.generateToken(username);

        //repostas no header
        response.setHeader("access-control-expose-headers", "Authorization");
        response.setHeader("Authorization", "Bearer " + token);


    }

    //caso a autenticação não tenha sucesso

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        response.setStatus(401);
        response.setContentType("aplication/json");
        response.getWriter().append(json());
    }

    private CharSequence json() {
        long date=new Date().getTime();
        return "{"
        +"\"timestamp\":"+date+","
        +"\"status\":401, "
        +"\"error\":\"Não Autorizado\","
        +"\"message\":\"Email ou senha inválidos\","
        +"\"path\":\"/login\"}";

    }


}
