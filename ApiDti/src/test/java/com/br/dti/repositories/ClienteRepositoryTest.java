package com.br.dti.repositories;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.br.dti.model.Cliente;
import com.br.dti.repository.ClienteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteRepositoryTest {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	private static final String CPF = "34441690002";
	
	@Before
	public void setUp() throws Exception {
		Cliente cliente = new Cliente();
		cliente.setCelular("(31) 99999-9999");
		cliente.setCpf(CPF);
		cliente.setEmail("emailCliente@gmail.com");
		cliente.setEndereco("Rua do cliente, 44 - Bairro do Cliente - Cidade do Cliente | MG");
		cliente.setNome("Nome do cliente de teste");
		
		this.clienteRepository.save(cliente);
	}
	
	@After
    public final void tearDown() { 
		this.clienteRepository.deleteAll();
	}

	@Test
	public void testBuscarPorCpf() {
		Optional<Cliente> cliente = this.clienteRepository.findByCpf(CPF);
		
		assertEquals(CPF, cliente.get().getCpf());
	}
}
