package com.aeviles.helpdesk.controller;

import com.aeviles.helpdesk.domain.Tecnico;
import com.aeviles.helpdesk.domain.dtos.TecnicoDTO;
import com.aeviles.helpdesk.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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

    @PreAuthorize("hasAnyRole('ADMIN')")//SÓ QUEM CRIA É O PERFIL ADMIN
    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO tecnicoDTO){
        Tecnico newObjTecnico=tecnicoService.create(tecnicoDTO);
        URI uri= ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(tecnicoDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @PreAuthorize("hasAnyRole('ADMIN')")//SÓ QUEM ATUALIZA É O PERFIL ADMIN
    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id,@Valid @RequestBody TecnicoDTO tecnicoDTO){
        Tecnico objTecnico = tecnicoService.update(id, tecnicoDTO);
        return ResponseEntity.ok().body(new TecnicoDTO(objTecnico));
    }

    //método delete é no conten ele não retorna nada, posso colocar void ou tecnicoDTO
    @PreAuthorize("hasAnyRole('ADMIN')")//SÓ QUEM DELETA É O PERFIL ADMIN
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO>  delete(@PathVariable Integer id){
        tecnicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
