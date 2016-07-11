package br.com.calendall.dto.in;

import javax.validation.constraints.NotNull;

public class CadastroFormaPagamentoIN {

	@NotNull(message = "informe o id")
	private Long id;

	@NotNull(message = "informe a tipo pagamento")
	private String tipoPagamento;

	public Long getId() {
		return id;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

}
