package com.br.dti.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
