package com.br.dti.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.dti.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	Optional<Cliente> findByCpf (String cpf);

}
