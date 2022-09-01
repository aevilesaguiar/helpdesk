package com.aeviles.helpdesk.service;

import com.aeviles.helpdesk.domain.Cliente;
import com.aeviles.helpdesk.domain.Pessoa;
import com.aeviles.helpdesk.domain.dtos.ClienteDTO;

import com.aeviles.helpdesk.repository.ClienteRepository;
import com.aeviles.helpdesk.repository.PessoaRepository;
import com.aeviles.helpdesk.service.exceptions.DataIntegrityViolationException;
import com.aeviles.helpdesk.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id){
        Optional<Cliente> cliente= clienteRepository.findById(id);
        return cliente.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id+ "+id));
    }

    public List<Cliente> findAll() {

        return clienteRepository.findAll();
    }

    public Cliente create(ClienteDTO clienteDTO) {
        clienteDTO.setId(null);//para aassegurar que o id vai vir nulo
        validaPorCpfEEmail(clienteDTO);
        Cliente newClienteObj=new Cliente(clienteDTO);
        return clienteRepository.save(newClienteObj);
    }

    public Cliente update(Integer id, ClienteDTO clienteDTO) {
        //eu seto o id que veio do TecnicoDTO com o Id que veio no parâmetro, pq ele pode passar na url um id e no corpo da requisição que veio nesse objeto passar outro id.
        clienteDTO.setId(id);
        // se esse id não existe ele lança um objeto não encontrado
        Cliente oldObjetoCliente = findById(id);
        validaPorCpfEEmail(clienteDTO);//verifica se existe e-mail ou cpf
        oldObjetoCliente=new Cliente(clienteDTO);
        return clienteRepository.save(oldObjetoCliente);

    }

    public void delete(Integer id) {
        Cliente clienteObj=findById(id);
        //se o técnico tiver alguma ordem de chamada ele lança a exceção
        if(clienteObj.getChamados().size()>0) {
            throw new DataIntegrityViolationException("Técnico possui ordens de Serviços e não pode ser deletados!");
        }
        clienteRepository.deleteById(id);

    }
    private void validaPorCpfEEmail(ClienteDTO clienteDTO) {
        Optional< Pessoa> optionalCliente=pessoaRepository.findByCpf(clienteDTO.getCpf());
        //verifica se o cpf já existe
        if(optionalCliente.isPresent()&&optionalCliente.get().getId()!=clienteDTO.getId()){
                throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }
        optionalCliente=pessoaRepository.findByEmail(clienteDTO.getEmail());
        //verifica se o email já existe
        if(optionalCliente.isPresent()&&optionalCliente.get().getId()!=clienteDTO.getId()){
            throw new DataIntegrityViolationException("Email já cadastrado no sistema");
        }

    }



}
