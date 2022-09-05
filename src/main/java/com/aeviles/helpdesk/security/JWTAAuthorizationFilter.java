package com.aeviles.helpdesk.security;


import com.aeviles.helpdesk.service.UserDetailsServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Criar um filtro de Autorização JWT
public class JWTAAuthorizationFilter extends BasicAuthenticationFilter {

    private JWTUtil jwtUtil;
    private UserDetailsServiceImpl userDetailsService;


    public JWTAAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
        super(authenticationManager);

        this.jwtUtil=jwtUtil;
        this.userDetailsService= userDetailsService;
       }

       //métodos de BasicAuthenticationFilter

    //conseguimos pegar algumas requisições que vem no cabeçalho através do request
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //variavel do tipo String, ela vai receber o nosso request , onde podemos pegar um campo no nosso Header no caso o "Authorization"
        //ou seja a variavel header terá esse valor : Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzdGFsbG1hbkBtYWlsLmNvbSIsImV4cCI6MTY2Mjk4Nzk5Mn0.JfPfYQxjy-F7cwWqlC75x2_MNhZ97eCSc4-h1VzN6dq_gAlORsUOs81Z_O7qlU7FKqpgwb0la_NfWowP6T3eLQ
        //quando fizermos a requisição
            String header = request.getHeader("Authorization");
            if(header != null && header.startsWith("Bearer ")) {
                UsernamePasswordAuthenticationToken authToken = getAuthentication(header.substring(7));
                if(authToken != null) {
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            chain.doFilter(request, response);
        }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
    if (jwtUtil.tokenValido(token)){
        String username= jwtUtil.getUsername(token);
        UserDetails details= userDetailsService.loadUserByUsername(username);//loadUserByUsername carrega o usuario pelo o seu username
        return new UsernamePasswordAuthenticationToken(details.getUsername(),null,details.getAuthorities());// sennha é nulo, e eu peho a lista de authoritie
    }
    return null;
    }
}
