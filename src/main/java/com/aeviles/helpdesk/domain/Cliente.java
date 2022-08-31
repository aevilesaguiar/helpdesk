package com.aeviles.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente extends Pessoa{

    private static final long serialVersionUID = -1813588164058069631L;

    @JsonIgnore //protege contra a serialização
    @OneToMany(mappedBy = "cliente")
    private List<Chamado> chamados= new ArrayList<>();//foiiniciliado para evitar nullPointer Exception

    public Cliente( ) {
        super();

        //sempre que um cliente for criado eu adiciono um perfil
        addPerfil(Perfil.CLIENTE);
    }

    //a lista de chamados não foi adicionado ao construtor por que não iremos passar uma lista de chamados quando
    //instanciar um tipo cliente
    public Cliente(Integer id, String nome, String cpf, String email, String senha ) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
