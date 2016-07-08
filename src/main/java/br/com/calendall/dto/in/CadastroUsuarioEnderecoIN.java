package br.com.calendall.dto.in;

import javax.validation.constraints.NotNull;

public class CadastroUsuarioEnderecoIN {

	@NotNull(message="Id deve ser informado")
	private Long id;

	@NotNull(message="Cpf deve ser informado")
	private String cpf;

	@NotNull(message="Celular deve ser informada")
	private String celular;
	
	@NotNull(message="CEP deve ser informado")
	private String cep;

	@NotNull(message="Numero deve ser informado")
	private Integer numero;

	private String complemento;

	@NotNull(message="Tipo atendimento deve ser informado")
	private String tipoAtendimento;

	public Long getId() {
		return id;
	}

	public String getCpf() {
		return cpf;
	}

	public String getCelular() {
		return celular;
	}

	public String getCep() {
		return cep;
	}

	public Integer getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getTipoAtendimento() {
		return tipoAtendimento;
	}
	
}
