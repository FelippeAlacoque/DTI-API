package com.br.dti.service;

import org.springframework.validation.BindingResult;

import com.br.dti.dto.ClienteDTO;
import com.br.dti.model.Cliente;

public interface ClienteService {
	
	Cliente persistir (Cliente cliente);
	void validarClienteExistente (String cpf, BindingResult result);
	void atualizarCliente(Cliente cliente, ClienteDTO clienteDto, BindingResult result);
}
