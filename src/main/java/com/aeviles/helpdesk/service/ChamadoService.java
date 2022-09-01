package com.aeviles.helpdesk.service;

import com.aeviles.helpdesk.domain.Chamado;
import com.aeviles.helpdesk.repository.ChamadoRepository;
import com.aeviles.helpdesk.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;



    public Chamado findById(Integer id){
        Optional<Chamado> objChamado= chamadoRepository.findById(id);
        return objChamado.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado "+id));
    }


    public List<Chamado> findAll() {

        return chamadoRepository.findAll();
    }
}
