package com.aeviles.helpdesk.service;

import com.aeviles.helpdesk.domain.*;
import com.aeviles.helpdesk.domain.dtos.ChamadoDTO;
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
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;



    public Chamado findById(Integer id){
        Optional<Chamado> objChamado= chamadoRepository.findById(id);
        return objChamado.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado "+id));
    }


    public List<Chamado> findAll() {

        return chamadoRepository.findAll();
    }

    public Chamado create(ChamadoDTO objChamadoDTO) {

        return  chamadoRepository.save(newChamado(objChamadoDTO));
    }

    private Chamado newChamado(ChamadoDTO objDTO){
        //caso não existe tecnico/cliente que foi passada como parâmetro uma exceção vai ser lançada
        Tecnico tecnico=tecnicoService.findById(objDTO.getTecnico());
        Cliente cliente=clienteService.findById(objDTO.getCliente());

        //caso exista
        Chamado chamado=new Chamado();
        //se objDTO for diferente de nulo ele quer atualizar o chamado
        if(objDTO.getId() != null){
            chamado.setId(objDTO.getId());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(objDTO.getPrioridade()));
        chamado.setStatus(Status.toEnum(objDTO.getStatus()));
        chamado.setTitulo(objDTO.getTitulo());
        chamado.setObservacoes(objDTO.getObservacoes());
        return chamado;


    }
}
