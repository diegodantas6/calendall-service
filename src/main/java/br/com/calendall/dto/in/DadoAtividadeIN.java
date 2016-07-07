package br.com.calendall.dto.in;

import javax.validation.constraints.NotNull;

public class DadoAtividadeIN {

	@NotNull(message = "id deve ser informado")
	private Long id;
	
	@NotNull(message = "tempo deve ser informado")
	private Integer tempo;
	
	@NotNull(message = "preco deve ser informado")
	private Double preco;

	public Long getId() {
		return id;
	}

	public Integer getTempo() {
		return tempo;
	}

	public Double getPreco() {
		return preco;
	}

}
