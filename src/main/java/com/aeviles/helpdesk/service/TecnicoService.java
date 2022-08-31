package com.aeviles.helpdesk.service;

import com.aeviles.helpdesk.domain.Tecnico;
import com.aeviles.helpdesk.repository.TecnicoRepository;
import com.aeviles.helpdesk.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> tecnico=tecnicoRepository.findById(id);
        return tecnico.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id+ "+id));
    }

    public List<Tecnico> findAll() {

        return tecnicoRepository.findAll();
    }
}
