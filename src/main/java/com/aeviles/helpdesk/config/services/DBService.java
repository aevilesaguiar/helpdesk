package com.aeviles.helpdesk.config.services;

import com.aeviles.helpdesk.domain.*;
import com.aeviles.helpdesk.repositories.ChamadoRepository;
import com.aeviles.helpdesk.repositories.ClienteRepository;
import com.aeviles.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;
    public void instanciaDB(){

        Tecnico tec1= new Tecnico(null,"Aeviles Aguiar","111.123.222-89","aasemail@email.com","123");
        tec1.addPerfil(Perfil.ADMIN);


        Cliente cli1= new Cliente(null,"Mark Zukemberg","123.456.789-00","mark@email.com","123");
        cli1.addPerfil(Perfil.CLIENTE);

        Chamado c1= new Chamado(null, Prioridade.MEDIA,Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado",tec1,cli1);

        tecnicoRepository.saveAll(Arrays.asList(tec1));
        clienteRepository.saveAll(Arrays.asList(cli1));
        chamadoRepository.saveAll(Arrays.asList(c1));

    }
}
