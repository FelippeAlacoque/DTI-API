package com.br.dti.resource;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.dti.dto.ClienteDTO;
import com.br.dti.model.Cliente;
import com.br.dti.repository.ClienteRepository;
import com.br.dti.response.Response;
import com.br.dti.service.ClienteService;
import com.br.dti.util.converter.Converter;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	ClienteRepository clienteRepository;
	
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
	
	@GetMapping
	public java.util.List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response<ClienteDTO>> buscarClientePorId(@PathVariable Long id){
		
		Response<ClienteDTO> response = new Response<ClienteDTO>();
		Optional<Cliente> cliente = clienteRepository.findById(id);
		
		if(!cliente.isPresent()) {
			response.getErrors().add("Não foi localizado cliente com o código " + id);
			return ResponseEntity.badRequest().body(response);
		}		
		response.setData(converter.converterClienteEmClienteDTO(cliente));
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response<ClienteDTO>> atualizar(@PathVariable Long id,
														  @Valid @RequestBody ClienteDTO clienteDto, 
														  BindingResult result) {
		
		Response<ClienteDTO> response = new Response<ClienteDTO>();		
		Optional<Cliente> cliente = clienteRepository.findById(id);
		
		clienteService.atualizarCliente(cliente.get(), clienteDto, result);
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}		
		clienteService.persistir(cliente.get());
		response.setData(converter.converterClienteEmClienteDTO(cliente));
		
		return ResponseEntity.ok(response);		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<String>> excluir(@PathVariable Long id) {
		Response<String> response = new Response<String>();
		
		Optional<Cliente> cliente = clienteRepository.findById(id);
		
		if(!cliente.isPresent()) {
			response.getErrors().add("Erro ao excluir cliente. Cliente com código " + id + " não localizado.");
			return ResponseEntity.badRequest().body(response);
		}
		clienteRepository.delete(cliente.get());
		response.setData(cliente.get().toString());
		
		return ResponseEntity.ok(new Response<String>());
	}

}
