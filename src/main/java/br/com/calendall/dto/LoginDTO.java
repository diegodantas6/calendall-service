package br.com.calendall.dto;

import javax.validation.constraints.NotNull;

public class LoginDTO {

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

	public LoginDTO() {
		super();
	}

	public LoginDTO(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}
}
