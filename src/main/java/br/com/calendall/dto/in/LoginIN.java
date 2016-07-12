package br.com.calendall.dto.in;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginIN {

	@NotNull(message = "Email deve ser informado")
	private String email;

	@Size(min = 6, max = 20, message = "A senha deve conter entre {min} e {max} caracteres")
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
