package br.com.calendall.dto.in;

import javax.validation.constraints.NotNull;

public class CadastroCartaoIN {

	@NotNull(message = "informe o id")
	private Long id;

	@NotNull(message = "informe o numero")
	private String numero;

	@NotNull(message = "informe o nome")
	private String nome;

	@NotNull(message = "informe o cvv")
	private String cvv;

	@NotNull(message = "informe o mes")
	private Integer mes;

	@NotNull(message = "informe o ano")
	private Integer ano;

	public Long getId() {
		return id;
	}

	public String getNumero() {
		return numero;
	}

	public String getNome() {
		return nome;
	}

	public String getCvv() {
		return cvv;
	}

	public Integer getMes() {
		return mes;
	}

	public Integer getAno() {
		return ano;
	}
}
