package br.com.calendall.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CadastroUsuarioDTO {

	@NotNull(message="Nome deve ser informado")
	private String nome;

	@Size(
	    min = 6,
	    max = 20,
	    message = "A senha deve conter entre {min} e {max} caracteres"
	)
	@NotNull(message="Login deve ser informado")
	private String login;

	@Size(
	    min = 6,
	    max = 20,
	    message = "A senha deve conter entre {min} e {max} caracteres"
	)
	@NotNull(message="Senha deve ser informada")
	private String senha;
	
	@NotNull(message="Tipo deve ser informado")
	private String tipo;

	public String getNome() {
		return nome;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public String getTipo() {
		return tipo;
	}

}
