package br.com.calendall.dto.in;

import javax.validation.constraints.NotNull;

public class LoginIN {

	@NotNull(message = "Login deve ser informado")
	private String login;
	
	@NotNull(message = "Senha deve ser informada")
	private String senha;

	public String getLogin() {
		return login;
	}
	
	public String getSenha() {
		return senha;
	}

	public LoginIN() {
		super();
	}

	public LoginIN(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}
}
