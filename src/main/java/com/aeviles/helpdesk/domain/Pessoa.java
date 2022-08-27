package com.aeviles.helpdesk.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Pessoa {


    protected Integer id;
    protected String nome;
    protected String cpf;
    protected String email;
    protected String senha;
    protected Set<Integer> perfis= new HashSet<>();//evita a exceção de nullPointerException, o set não permite que existe dois perfis igual pois é um conjunto, eu quero armazenar apenas o código do perfil
    protected LocalDate dataCriacao=LocalDate.now();//esse método pega a instancia atual em que esse método foi criado

    public Pessoa() {
        super();

        //TODO usuario criado no sistema deverá ter no minimo um perfil de cliente
        addPerfil(Perfil.CLIENTE);


    }

    public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.addPerfil(Perfil.CLIENTE);
    }

    //metodos acessores e modificadores


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

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

    //foi alterado o metodo set será mapeado, coleto os dados e faço a conversão para uma listinnha tipo set
    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x->Perfil.toEnum(x)).collect(Collectors.toSet());
    }
//foi alterado o nome do método, eu não quero recebr uma lista e sim apenas um perfil
    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id) && Objects.equals(cpf, pessoa.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }
}
