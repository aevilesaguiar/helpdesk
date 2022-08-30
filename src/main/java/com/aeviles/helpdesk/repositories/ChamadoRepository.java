package com.aeviles.helpdesk.repositories;

import com.aeviles.helpdesk.domain.Chamado;
import com.aeviles.helpdesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}
