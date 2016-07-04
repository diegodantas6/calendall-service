package br.com.calendall.dto.in;

import javax.validation.constraints.NotNull;

public class RecuperarSenhaIN {

	@NotNull(message="informe o login")
	private String login;
	
	public String getLogin() {
		return login;
	}
	
}
