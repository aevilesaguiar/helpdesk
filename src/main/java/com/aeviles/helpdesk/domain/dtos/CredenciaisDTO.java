package com.aeviles.helpdesk.domain.dtos;

//Não precisa do Serializable porque ele não trafega em rede
//serve para fazer a conversão do Usuario e senha que vai vir na requisição de login
public class CredenciaisDTO {

    private String email;
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
