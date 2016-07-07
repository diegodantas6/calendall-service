package br.com.calendall.dto.in;

import javax.validation.constraints.NotNull;

public class RecuperarSenhaIN {

	@NotNull(message="informe o email")
	private String email;

	public String getEmail() {
		return email;
	}
	
}
