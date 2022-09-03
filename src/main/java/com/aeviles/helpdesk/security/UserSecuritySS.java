package com.aeviles.helpdesk.security;

import com.aeviles.helpdesk.domain.Perfil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

//esssas regras são implementadas de acordo com a regra do cliente
public class UserSecuritySS implements UserDetails {

    //precisamos incluir o serialVersion porque UserDetails contem o implements Serializable
    private static final long serialVersionUID = -1813588164058069631L;

    //adicionar atributos id, email, senha e lista de authorites
    private Integer id;
    private String email;
    private String senha;

    //criar um construtor


    public UserSecuritySS(Integer id, String email, String senha, Set<Perfil> perfis) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.authorities = perfis.stream().map(x-> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toSet());
    }

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public Integer getId() {
        return id;
    }

    //a conta não está expirada?
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
//a conta não está bloqueada? true or false
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
//as senhas não estão expiradas?
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
//está habilitado?
    @Override
    public boolean isEnabled() {
        return true;
    }
}
