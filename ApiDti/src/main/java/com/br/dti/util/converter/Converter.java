package com.br.dti.util.converter;

import org.springframework.context.annotation.Configuration;

import com.br.dti.dto.ClienteDTO;
import com.br.dti.model.Cliente;

@Configuration
public class Converter {
	
	public Cliente converterClienteDTOparaCliente(ClienteDTO clienteDto) {
		Cliente cliente = new Cliente();
		cliente.setCelular(clienteDto.getCelular());
		cliente.setCpf(clienteDto.getCpf());
		cliente.setEmail(clienteDto.getEmail());
		cliente.setEndereco(clienteDto.getEndereco());
		cliente.setNome(clienteDto.getNome());
		
		return cliente;
	}

}
