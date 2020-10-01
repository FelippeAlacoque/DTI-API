package com.br.dti.util.converter;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;

import com.br.dti.dto.ClienteDTO;
import com.br.dti.model.Cliente;

@Configuration
public class Converter {
	
	public Cliente converterClienteDTOparaCliente(ClienteDTO clienteDto) {
		Cliente cliente = new Cliente();
		cliente.setId(clienteDto.getId());
		cliente.setCelular(clienteDto.getCelular());
		cliente.setCpf(clienteDto.getCpf());
		cliente.setEmail(clienteDto.getEmail());
		cliente.setEndereco(clienteDto.getEndereco());
		cliente.setNome(clienteDto.getNome());
		
		return cliente;
	}
	
	public ClienteDTO converterClienteEmClienteDTO(Optional<Cliente> cliente) {		
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setId(cliente.get().getId());
		clienteDTO.setCelular(cliente.get().getCelular());
		clienteDTO.setCpf(cliente.get().getCpf());
		clienteDTO.setEmail(cliente.get().getEmail());
		clienteDTO.setEndereco(cliente.get().getEndereco());
		clienteDTO.setNome(cliente.get().getNome());
		
		return clienteDTO;
	}

}
