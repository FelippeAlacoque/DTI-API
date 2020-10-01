package com.br.dti.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public class ClienteDTO {
	
	private long id;
	private String nome;
	private String endereco;
	private String celular;
	private String email;
	private String cpf;
	
	public ClienteDTO() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@NotEmpty(message = "Nome deve ser preenchido.")
	@Length(min = 5, max = 200,	message = "O nome deve possuir no mínimo 5 caracteres e no máximo 200.")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotEmpty(message = "Celular deve ser preenchido.")
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@NotEmpty(message = "Email deve ser preenchido.")
	@Email(message = "Email inválido.")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotEmpty(message = "CPF deve ser preenchido.")
	@CPF(message = "CPF inválido.")
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@NotEmpty(message = "Endereço deve ser preenchido.")
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
