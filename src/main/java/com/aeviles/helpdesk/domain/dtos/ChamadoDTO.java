package com.aeviles.helpdesk.domain.dtos;

import com.aeviles.helpdesk.domain.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

public class ChamadoDTO implements Serializable {

    private static final long serialVersionUID = -3260603479817898134L;

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura= LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;
    //aqui eu também não preciso de todas as informações de prioridade , apenas do id
    private Integer prioridade;
    //aqui eu também não preciso de todas as informações de status , apenas do id
    private Integer status;
    private String titulo;
    private String observacoes;
    private String Observacoes;
    //aqui eu não quero retornar todas as informações do meu técnico
    //quando eu buscar um chamado pelo o seu id, eu preciso apenas do id
    private Integer tecnico;
    //mesmo caso do cliente eu só preciso do id e não de todas as informações do cliente
    private Integer cliente;
    private String nomeTecnico;
    private String nomeCliente;

    //no nosso front nós vamos implementar uma tabela que nós vamos listar todos os chamados e nessa tabela vai ter uma coluna
    //que vai se o nome do tecnico e cliente que atua no chamado. Se eu fizer um findById por tecnico, ele fará isso para cada técnico
    // por isso foi criado um atributo nomeTecnico/nomeCliente assim já retornmaos o nome do tecnico e só fazemos uma requisição


    public ChamadoDTO() {
        super();
    }

    public ChamadoDTO(Chamado obj) {
        this.id = obj.getId();
        this.dataAbertura = obj.getDataAbertura();
        this.dataFechamento = obj.getDataFechamento();
        this.prioridade = obj.getPrioridade().getCodigo();
        this.status = obj.getStatus().getCodigo();
        this.titulo = obj.getTitulo();
        this.observacoes = obj.getTitulo();
        this.tecnico = obj.getTecnico().getId();
        this.cliente = obj.getCliente().getId();
        this.nomeTecnico = obj.getTecnico().getNome();
        this.nomeCliente = obj.getCliente().getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Integer getTecnico() {
        return tecnico;
    }

    public void setTecnico(Integer tecnico) {
        this.tecnico = tecnico;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public String getNomeTecnico() {
        return nomeTecnico;
    }

    public void setNomeTecnico(String nomeTecnico) {
        this.nomeTecnico = nomeTecnico;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}
