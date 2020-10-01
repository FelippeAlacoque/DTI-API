package com.br.dti.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.dti.dto.ClienteDTO;
import com.br.dti.model.Cliente;
import com.br.dti.response.Response;
import com.br.dti.service.ClienteService;
import com.br.dti.util.converter.Converter;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	Converter converter;
	
	@PostMapping
	public ResponseEntity<Response<ClienteDTO>> cadastrar(@Valid @RequestBody ClienteDTO clienteDTO, BindingResult result){
		Response<ClienteDTO> response = new Response<ClienteDTO>();		
		
		clienteService.validarClienteExistente(clienteDTO.getCpf(), result);
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}		
		
		Cliente cliente = converter.converterClienteDTOparaCliente(clienteDTO);		
		
		this.clienteService.persistir(cliente);		
		response.setData(clienteDTO);
		
		return ResponseEntity.ok(response);			
	}

}
