package com.aeviles.helpdesk.service;

import com.aeviles.helpdesk.domain.Pessoa;
import com.aeviles.helpdesk.repository.PessoaRepository;
import com.aeviles.helpdesk.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PessoaRepository pessoaRepository;


    //UserDetailsService tem esse método, e essa implementação loga por email e senha
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Pessoa>  user=pessoaRepository.findByEmail(email);
        if(user.isPresent()){
            return new UserSS(user.get().getId(), user.get().getEmail(),user.get().getSenha(),user.get().getPerfis());
        }
        //caso não seja encontrado no bnco
        throw new UsernameNotFoundException(email);
    }
}
