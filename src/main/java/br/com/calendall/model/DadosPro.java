package br.com.calendall.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dados_pro")
public class DadosPro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message="Tipo de pagamento e obrigatorio")
	@Column(name = "tipo_pagamento")
	private String tipoPagamento;

	@NotNull(message="Tipo de atendimento e obrigatorio")
	@Column(name = "tipo_atendimento")
	private String tipoAtendimento;
	
	@NotNull(message="Tempo entre agendas e obrigatorio")
	@Column(name = "tempo_entre_agendas")
	private Integer tempoEntreAgendas;

	@NotNull(message="CEP e obrigatorio")
	@Column(name = "cep")
	private String cep;

	@NotNull(message="Numero e obrigatorio")
	@Column(name = "numero")
	private Integer numero;

	@NotNull(message="Complemento e obrigatorio")
	@Column(name = "complemento")
	private String complemento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public String getTipoAtendimento() {
		return tipoAtendimento;
	}

	public void setTipoAtendimento(String tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}

	public Integer getTempoEntreAgendas() {
		return tempoEntreAgendas;
	}

	public void setTempoEntreAgendas(Integer tempoEntreAgendas) {
		this.tempoEntreAgendas = tempoEntreAgendas;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

}
