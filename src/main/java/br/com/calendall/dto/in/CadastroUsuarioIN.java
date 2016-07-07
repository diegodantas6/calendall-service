package br.com.calendall.dto.in;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CadastroUsuarioIN {

	@NotNull(message="Nome deve ser informado")
	private String nome;

	@NotNull(message="Email deve ser informado")
	private String email;

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

	public String getEmail() {
		return email;
	}
	
	public String getSenha() {
		return senha;
	}

	public String getTipo() {
		return tipo;
	}

}
