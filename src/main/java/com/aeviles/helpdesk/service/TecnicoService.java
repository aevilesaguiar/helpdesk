package com.aeviles.helpdesk.service;

import com.aeviles.helpdesk.domain.Pessoa;
import com.aeviles.helpdesk.domain.Tecnico;
import com.aeviles.helpdesk.domain.dtos.TecnicoDTO;
import com.aeviles.helpdesk.repository.PessoaRepository;
import com.aeviles.helpdesk.repository.TecnicoRepository;
import com.aeviles.helpdesk.service.exceptions.DataIntegrityViolationException;
import com.aeviles.helpdesk.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> tecnico=tecnicoRepository.findById(id);
        return tecnico.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id+ "+id));
    }

    public List<Tecnico> findAll() {

        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(null);//para aassegurar que o id vai vir nulo
        validaPorCpfEEmail(tecnicoDTO);
        Tecnico newTecnicoObj=new Tecnico(tecnicoDTO);
        return tecnicoRepository.save(newTecnicoObj);
    }

    public Tecnico update(Integer id, TecnicoDTO tecnicoDTO) {
        //eu seto o id que veio do TecnicoDTO com o Id que veio no parâmetro, pq ele pode passar na url um id e no corpo da requisição que veio nesse objeto passar outro id.
        tecnicoDTO.setId(id);
        // se esse id não existe ele lança um objeto não encontrado
        Tecnico oldObjetoTecnico = findById(id);
        validaPorCpfEEmail(tecnicoDTO);//verifica se existe e-mail ou cpf
        oldObjetoTecnico=new Tecnico(tecnicoDTO);
        return tecnicoRepository.save(oldObjetoTecnico);

    }
    private void validaPorCpfEEmail(TecnicoDTO tecnicoDTO) {
        Optional< Pessoa> optionalTecnico=pessoaRepository.findByCpf(tecnicoDTO.getCpf());
        //verifica se o cpf já existe
        if(optionalTecnico.isPresent()&&optionalTecnico.get().getId()!=tecnicoDTO.getId()){
                throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }
        optionalTecnico=pessoaRepository.findByEmail(tecnicoDTO.getEmail());
        //verifica se o email já existe
        if(optionalTecnico.isPresent()&&optionalTecnico.get().getId()!=tecnicoDTO.getId()){
            throw new DataIntegrityViolationException("Email já cadastrado no sistema");
        }

    }


}
