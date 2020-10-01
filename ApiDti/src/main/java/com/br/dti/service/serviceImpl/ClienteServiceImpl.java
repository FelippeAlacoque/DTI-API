package com.br.dti.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.br.dti.dto.ClienteDTO;
import com.br.dti.model.Cliente;
import com.br.dti.repository.ClienteRepository;
import com.br.dti.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public Cliente persistir(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public void validarClienteExistente(String cpf, BindingResult result) {
		clienteRepository.findByCpf(cpf).ifPresent(cli -> result.addError(new ObjectError("cliente", "Já existe um cliente cadastrado com esse CPF")));
	}

	@Override
	public void atualizarCliente(Cliente cliente, ClienteDTO clienteDto, BindingResult result) {
		cliente.setCelular(clienteDto.getCelular());
		cliente.setEmail(clienteDto.getEmail());
		cliente.setEndereco(clienteDto.getEndereco());
		cliente.setNome(clienteDto.getNome());
		
		if(!cliente.getCpf().equals(clienteDto.getCpf())) {
			clienteRepository.findByCpf(clienteDto.getCpf())
				.ifPresent(cli -> result.addError(new ObjectError("cliente", "Já existe cliente cadastrado com o CPF " + cliente.getCpf())));
			cliente.setCpf(clienteDto.getCpf());
		}
		
	}



}
