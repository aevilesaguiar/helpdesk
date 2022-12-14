package com.aeviles.helpdesk.domain;

import com.aeviles.helpdesk.domain.dtos.TecnicoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
public class Tecnico extends Pessoa{

    private static final long serialVersionUID = -1813588164058069631L;

    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")//um técnico para muitos chamados
    private List<Chamado> chamados= new ArrayList<>();

    public Tecnico() {

        super();
        addPerfil(Perfil.CLIENTE);

    }

    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
    }

    public Tecnico(TecnicoDTO tecnicoObj) {
        super();
        this.id = tecnicoObj.getId();
        this.nome = tecnicoObj.getNome();
        this.cpf = tecnicoObj.getCpf();
        this.email = tecnicoObj.getEmail();
        this.senha = tecnicoObj.getSenha();
        this.perfis = tecnicoObj.getPerfis().stream().map(x->x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao =tecnicoObj.getDataCriacao();
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
