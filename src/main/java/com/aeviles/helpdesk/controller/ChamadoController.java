package com.aeviles.helpdesk.controller;

import com.aeviles.helpdesk.domain.Chamado;
import com.aeviles.helpdesk.domain.dtos.ChamadoDTO;
import com.aeviles.helpdesk.service.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoController {

    @Autowired
    private ChamadoService chamadoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
        Chamado objChamado= chamadoService.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(objChamado));

    }



}
