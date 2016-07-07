package br.com.calendall.dto.in;

import javax.validation.constraints.NotNull;

public class LoginIN {

	@NotNull(message = "Email deve ser informado")
	private String email;
	
	@NotNull(message = "Senha deve ser informada")
	private String senha;

	public String getEmail() {
		return email;
	}
	
	public String getSenha() {
		return senha;
	}

	public LoginIN() {
		super();
	}

	public LoginIN(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}
}
