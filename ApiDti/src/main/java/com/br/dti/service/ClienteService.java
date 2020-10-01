package com.br.dti.service;

import org.springframework.validation.BindingResult;

import com.br.dti.model.Cliente;

public interface ClienteService {
	
	Cliente persistir (Cliente cliente);
	void validarClienteExistente (String cpf, BindingResult result);
}
