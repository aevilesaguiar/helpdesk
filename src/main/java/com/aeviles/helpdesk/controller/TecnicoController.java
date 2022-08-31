package com.aeviles.helpdesk.controller;

import com.aeviles.helpdesk.domain.Tecnico;
import com.aeviles.helpdesk.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService tecnicoService;


    //ResponseEntity significa que vamos representar toda resposta http, usamos responseEntity para trabalghar com APIS
    //localhost:8080/tecnicos/1
    @GetMapping(value = "/{id}")
    public ResponseEntity<Tecnico> findById(@PathVariable Integer id){//como estamos recebendo uma variavel de path temos que colocar um @PathVariable

        Tecnico objTecnico= this.tecnicoService.findById(id);
        return ResponseEntity.ok().body(objTecnico);

    }

}
