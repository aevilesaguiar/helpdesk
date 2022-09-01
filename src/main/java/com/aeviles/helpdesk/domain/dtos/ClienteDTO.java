package com.aeviles.helpdesk.domain.dtos;

import com.aeviles.helpdesk.domain.Cliente;
import com.aeviles.helpdesk.domain.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClienteDTO implements Serializable {

    private static final long serialVersionUID = -1813588164058069631L;

    protected Integer id;
    @NotNull(message = " O campo nome é requerido")
    protected String nome;
    @NotNull(message = " O campo cpf é requerido")
    protected String cpf;
    @NotNull(message = " O campo email é requerido")
    protected String email;
    @NotNull(message = " O campo senha é requerido")
    protected String senha;
    protected Set<Integer> perfis= new HashSet<>();//evita a exceção de nullPointerException, o set não permite que existe dois perfis igual pois é um conjunto, eu quero armazenar apenas o código do perfil
    @JsonFormat(pattern = "dd/MM/yyyy")//formato/padrão pois apenas com LocalDate vem sem a forma padrao que eu quero
    protected LocalDate dataCriacao=LocalDate.now();//esse método pega a instancia atual em que esse método foi criado

    public ClienteDTO() {
        super();
        addPerfis(Perfil.CLIENTE);
    }

    public ClienteDTO(Cliente clienteDTO) {
        super();
        this.id = clienteDTO.getId();
        this.nome = clienteDTO.getNome();
        this.cpf = clienteDTO.getCpf();
        this.email = clienteDTO.getEmail();
        this.senha = clienteDTO.getSenha();
        this.perfis = clienteDTO.getPerfis().stream().map(x->x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao =clienteDTO.getDataCriacao();
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
