package com.br.dti.resources;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.br.dti.model.Cliente;
import com.br.dti.repository.ClienteRepository;
import com.br.dti.service.ClienteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClienteResourceTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ClienteRepository clienteRepository;
	
	@MockBean
	private ClienteService clienteService;
	
	private static final String URL_BASE = "/api/clientes/";
	private static final Long ID_CLIENTE = 1L;
	private static final String CPF = "34441690002";
	
	@Test
	public void testCadastrarLancamento() throws JsonProcessingException, Exception {
		
		Cliente cliente = obterCliente();
		BDDMockito.given(this.clienteRepository.findByCpf(Mockito.anyString())).willReturn(Optional.of(new Cliente())); 
		BDDMockito.given(this.clienteService.persistir(Mockito.any(Cliente.class))).willReturn(cliente);
		
		mvc.perform(MockMvcRequestBuilders.post(URL_BASE)
				.content(this.obterJsonRequisicaoPost())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id").value(ID_CLIENTE))
				.andExpect(jsonPath("$.data.celular").value(cliente.getCelular()))
				.andExpect(jsonPath("$.data.cpf").value(CPF))
				.andExpect(jsonPath("$.data.email").value(cliente.getEmail()))
				.andExpect(jsonPath("$.data.endereco").value(cliente.getEndereco()))
				.andExpect(jsonPath("$.data.nome").value(cliente.getNome()))
				.andExpect(jsonPath("$.errors").isEmpty());		
	}
	
	private String obterJsonRequisicaoPost() throws JsonProcessingException {
		Cliente cli = obterCliente();
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(cli);		
	}
	
	private Cliente obterCliente() {
		Cliente cliente = new Cliente();
		cliente.setId(ID_CLIENTE);
		cliente.setCelular("(31) 99999-9999");
		cliente.setCpf(CPF);
		cliente.setEmail("emailCliente@gmail.com");
		cliente.setEndereco("Rua do cliente, 44 - Bairro do Cliente - Cidade do Cliente | MG");
		cliente.setNome("Nome do cliente de teste");
		
		return cliente;
	}

}
