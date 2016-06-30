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
@Table(name = "folga")
public class Folga implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message="Data e obrigatorio")
	@Temporal(TemporalType.DATE)
	@Column(name = "data_folga")
	private Date dataFolga;

	@Column(name = "descricao")
	private String descricao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id")
	private Usuario profissional;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataFolga() {
		return dataFolga;
	}

	public void setDataFolga(Date dataFolga) {
		this.dataFolga = dataFolga;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getProfissional() {
		return profissional;
	}

	public void setProfissional(Usuario profissional) {
		this.profissional = profissional;
	}

}
