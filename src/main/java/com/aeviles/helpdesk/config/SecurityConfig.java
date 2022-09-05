package com.aeviles.helpdesk.config;

import com.aeviles.helpdesk.security.JWTAAuthorizationFilter;
import com.aeviles.helpdesk.security.JWTAutenticationFilter;
import com.aeviles.helpdesk.security.JWTUtil;
import com.aeviles.helpdesk.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

//Classe de Configuração
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//podemos usar o prepostEnable nos nossos endpoints
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    //se fosse com o banco de dados h2 incluíriamos ocódigo abaixo
    //private static final String[] PUBLIC_MATCHERS = { "/h2-console/**" }; tudo que vier após o h2console eu quero que seja liberado

    //esse enviaronment representa o ambiente o qual o nosso aplicativo atual está sendo executado, ele pode ser usado para obter os perfis e ambientes do aplicativo
    //@Autowired
    //	private Environment env;


    //esse método sobrescrito configure: qualquer endpoint que requerer uma defesa contra qualquer vulnerabilidade
    //pode ser especificado aqui dentro, inclusive os endpoints publicos e algumas configurações de filtro.
    @Override
    protected void configure(HttpSecurity http) throws Exception {



        //fazer uma validação para ele ativar o h2
        //if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
         //   http.headers().frameOptions().disable();
        //}

    //temos uma configuração de cors e ele sobe o métodocorsConfigurationSource()
      http.cors().and().csrf().disable(); //desabilitar a proteção contra o ataque csrf ,Cross-site Request Forgery (CSRF) é um tipo de
                                            // ataque de websites maliciosos. Um ataque CSRF às vezes é chamado de ataque de um clique
                                            //ou transporte de sessão. Esse tipo de ataque envia solicitações desautorizadas de um usuário no qual o website confia.
                                            // a aplicação não vai armazenar a sessão de usuario nós vamos desabilitar
    //registrar o filtro de autenticação em SecurityConfigure
    http.addFilter(new JWTAutenticationFilter(authenticationManager(),jwtUtil));

    http.addFilter(new JWTAAuthorizationFilter(authenticationManager(),jwtUtil, userDetailsService));

    //eu asseguro que a sessão de usuário não será criada
      http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

      //configuração para liberar o h2
       //http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();
    }

//como será utiliazda a autenticação do framework esse método tem uma sobrecarga e ele precisa ser reescrito
        @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder()).and();

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        //configuração do cors, ele aplica uma liberação de valores padrão
        CorsConfiguration configuration= new CorsConfiguration().applyPermitDefaultValues();
        //métodos permitidos
        configuration.setAllowedMethods(Arrays.asList("POST","GET","PUT","DELETE","OPTIONS"));

        final UrlBasedCorsConfigurationSource source= new UrlBasedCorsConfigurationSource();
        //registrando a configuração de cors
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }

    //adicionar um Bean de BCryptPasswordEncoder, qualquer pessoa que tiver acesso ao BD vai ver a senha , por isso temos que encodar a senha
    //Agora temos um BCryptPasswordEncoder em forma de @Bean que posso injetar em qualquer parte do meu programa
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }


}
