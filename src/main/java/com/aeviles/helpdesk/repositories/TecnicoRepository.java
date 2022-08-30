package com.aeviles.helpdesk.repositories;

import com.aeviles.helpdesk.domain.Cliente;
import com.aeviles.helpdesk.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
}
