package com.aeviles.helpdesk;

import com.aeviles.helpdesk.domain.*;
import com.aeviles.helpdesk.repositories.ChamadoRepository;
import com.aeviles.helpdesk.repositories.ClienteRepository;
import com.aeviles.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	//Não é uma boa prática fazer dessa forma a inclusão dos dados
	//injentando dependencias em deteminado trecho de código
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

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
