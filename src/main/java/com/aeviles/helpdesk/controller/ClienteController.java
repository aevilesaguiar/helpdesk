package com.aeviles.helpdesk.controller;

import com.aeviles.helpdesk.domain.Cliente;
import com.aeviles.helpdesk.domain.Tecnico;
import com.aeviles.helpdesk.domain.dtos.ClienteDTO;
import com.aeviles.helpdesk.domain.dtos.TecnicoDTO;
import com.aeviles.helpdesk.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    //ResponseEntity significa que vamos representar toda resposta http, usamos responseEntity para trabalghar com APIS
    //localhost:8080/clientes/1
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){//como estamos recebendo uma variavel de path temos que colocar um @PathVariable

        Cliente objCliente= this.clienteService.findById(id);
        return ResponseEntity.ok().body(new ClienteDTO(objCliente));

    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){

        List<Cliente> clienteList= clienteService.findAll();
        List<ClienteDTO> clienteDTOS= clienteList.stream().map(clienteObj -> new ClienteDTO(clienteObj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(clienteDTOS);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO clienteDTO){
        Cliente newObjCliente= clienteService.create(clienteDTO);
        URI uri= ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(clienteDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id,@Valid @RequestBody ClienteDTO clienteDTO){
        Cliente objCliente = clienteService.update(id, clienteDTO);
        return ResponseEntity.ok().body(new ClienteDTO(objCliente));
    }

    //método delete é no conten ele não retorna nada, posso colocar void ou clienteDTO
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO>  delete(@PathVariable Integer id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
