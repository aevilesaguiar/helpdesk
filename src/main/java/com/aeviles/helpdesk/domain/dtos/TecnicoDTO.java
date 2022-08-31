package com.aeviles.helpdesk.domain.dtos;

import com.aeviles.helpdesk.domain.Perfil;
import com.aeviles.helpdesk.domain.Tecnico;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TecnicoDTO implements Serializable {

    private static final long serialVersionUID = -1813588164058069631L;

    protected Integer id;
    protected String nome;
    protected String cpf;
    protected String email;
    protected String senha;
    protected Set<Integer> perfis= new HashSet<>();//evita a exceção de nullPointerException, o set não permite que existe dois perfis igual pois é um conjunto, eu quero armazenar apenas o código do perfil
    @JsonFormat(pattern = "dd/MM/yyyy")//formato/padrão pois apenas com LocalDate vem sem a forma padrao que eu quero
    protected LocalDate dataCriacao=LocalDate.now();//esse método pega a instancia atual em que esse método foi criado

    public TecnicoDTO() {
        super();
        addPerfis(Perfil.CLIENTE);
    }

    public TecnicoDTO(Tecnico tecnicoObj) {
        super();
        this.id = tecnicoObj.getId();
        this.nome = tecnicoObj.getNome();
        this.cpf = tecnicoObj.getCpf();
        this.email = tecnicoObj.getEmail();
        this.senha = tecnicoObj.getSenha();
        this.perfis = tecnicoObj.getPerfis().stream().map(x->x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao =tecnicoObj.getDataCriacao();
    }

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

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x->Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfis(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());//estou adicionando a minha listinha o codigo
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
