package com.br.dti.services;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.br.dti.model.Cliente;
import com.br.dti.repository.ClienteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteServiceTest {
	
	@MockBean
	private ClienteRepository clienteRepository;
	
	@Before
	public void setUp() throws Exception {
		Cliente cliente = new Cliente();
		cliente.setCelular("(31) 99999-9999");
		cliente.setCpf("34441690002");
		cliente.setEmail("emailCliente@gmail.com");
		cliente.setEndereco("Rua do cliente, 44 - Bairro do Cliente - Cidade do Cliente | MG");
		cliente.setNome("Nome do cliente de teste");
		
		this.clienteRepository.save(cliente);
	}
	
	@After
    public final void tearDown() { 
		this.clienteRepository.deleteAll();
	}
	
	//TODO: métodos do ClienteServiceImpl

}
