package com.aeviles.helpdesk.controller;

import com.aeviles.helpdesk.domain.Chamado;
import com.aeviles.helpdesk.domain.dtos.ChamadoDTO;
import com.aeviles.helpdesk.domain.dtos.TecnicoDTO;
import com.aeviles.helpdesk.service.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll(){
        List<Chamado> chamadoList=chamadoService.findAll();
        List<ChamadoDTO> dtoList=chamadoList.stream().map(obj -> new ChamadoDTO(obj)).collect(Collectors.toList());//temos uma lista convertida para DTO
        return ResponseEntity.ok().body(dtoList);
    }



    @PostMapping
    public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO objChamadoDTO){
        Chamado objChamado = chamadoService.create(objChamadoDTO);
        URI uri= ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(objChamado.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> update(@PathVariable Integer id,@Valid @RequestBody ChamadoDTO objDTO){

        Chamado newObj = chamadoService.update(id, objDTO);
        return ResponseEntity.ok().body(new ChamadoDTO(newObj));
    }


}
