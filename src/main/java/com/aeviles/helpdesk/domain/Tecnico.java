package com.aeviles.helpdesk.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Tecnico extends Pessoa{

    private static final long serialVersionUID = -1813588164058069631L;

    @OneToMany(mappedBy = "tecnico")//um técnico para muitos chamados
    private List<Chamado> chamados= new ArrayList<>();

    public Tecnico() {

        super();
        addPerfil(Perfil.CLIENTE);

    }

    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);

    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
