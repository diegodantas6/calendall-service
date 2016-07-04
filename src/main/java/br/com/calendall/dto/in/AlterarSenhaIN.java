package br.com.calendall.dto.in;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AlterarSenhaIN {

	@NotNull(message="informe o id")
	private Long id;
	
	@Size(
	    min = 6,
	    max = 20,
	    message = "A senha deve conter entre {min} e {max} caracteres"
	)
	@NotNull(message="informe a senha")
	private String senha;

	public Long getId() {
		return id;
	}
	
	public String getSenha() {
		return senha;
	}
}
