package br.com.calendall.dto;

import javax.validation.constraints.NotNull;

public class RecuperarSenhaDTO {

	@NotNull(message="informe o login")
	private String login;
	
	public String getLogin() {
		return login;
	}
	
}
