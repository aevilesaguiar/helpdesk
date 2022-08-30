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
public class HelpdeskApplication {
	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

}
