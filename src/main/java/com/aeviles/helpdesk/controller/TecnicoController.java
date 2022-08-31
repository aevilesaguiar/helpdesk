package com.aeviles.helpdesk.controller;

import com.aeviles.helpdesk.domain.Tecnico;
import com.aeviles.helpdesk.domain.dtos.TecnicoDTO;
import com.aeviles.helpdesk.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService tecnicoService;


    //ResponseEntity significa que vamos representar toda resposta http, usamos responseEntity para trabalghar com APIS
    //localhost:8080/tecnicos/1
    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){//como estamos recebendo uma variavel de path temos que colocar um @PathVariable

        Tecnico objTecnico= this.tecnicoService.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(objTecnico));

    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){

        List<Tecnico> tecnicoList=tecnicoService.findAll();
        List<TecnicoDTO> tecnicoDTOS= tecnicoList.stream().map(tecnicoObj -> new TecnicoDTO(tecnicoObj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(tecnicoDTOS);
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@RequestBody TecnicoDTO tecnicoDTO){
        Tecnico newObjTecnico=tecnicoService.create(tecnicoDTO);
        URI uri= ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(tecnicoDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
