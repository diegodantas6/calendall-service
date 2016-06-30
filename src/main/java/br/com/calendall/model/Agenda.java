package br.com.calendall.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "agenda")
public class Agenda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message="Data e obrigatorio")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_agenda")
	private Date data;

	@NotNull(message="Situacao e obrigatorio")
	@Column(name = "situacao")
	private String situacao;

	@Column(name = "cordenadas_cliente")
	private String cordenadasCliente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id_pro")
	private Usuario profissional;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id_cli")
	private Usuario cliente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "atividade_id")
	private Atividade atividade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getCordenadasCliente() {
		return cordenadasCliente;
	}

	public void setCordenadasCliente(String cordenadasCliente) {
		this.cordenadasCliente = cordenadasCliente;
	}

	public Usuario getProfissional() {
		return profissional;
	}

	public void setProfissional(Usuario profissional) {
		this.profissional = profissional;
	}

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

}
