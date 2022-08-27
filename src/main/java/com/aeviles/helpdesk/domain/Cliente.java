package com.aeviles.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa{

    private List<Chamado> chamados= new ArrayList<>();//foiiniciliado para evitar nullPointer Exception

    public Cliente( ) {
        super();
    }

    //a lista de chamados não foi adicionado ao construtor por que não iremos passar uma lista de chamados quando
    //instanciar um tipo cliente
    public Cliente(Integer id, String nome, String cpf, String email, String senha ) {
        super(id, nome, cpf, email, senha);
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
