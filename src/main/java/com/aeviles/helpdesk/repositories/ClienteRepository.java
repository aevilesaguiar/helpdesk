package com.aeviles.helpdesk.repositories;

import com.aeviles.helpdesk.domain.Cliente;
import com.aeviles.helpdesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
