package com.aeviles.helpdesk.service;

import com.aeviles.helpdesk.domain.Tecnico;
import com.aeviles.helpdesk.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> tecnico=tecnicoRepository.findById(id);
        return tecnico.orElse(null);
    }

}
