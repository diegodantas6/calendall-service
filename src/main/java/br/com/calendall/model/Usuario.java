package br.com.calendall.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name = "login", query = "select u from Usuario u where u.email = :email and u.senha = :senha and u.situacao = 'A'"),
	@NamedQuery(name = "recuperar_senha", query = "select u from Usuario u where u.email = :email and u.situacao = 'A'")
})
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "senha")
	private String senha;

	@Column(name = "tipo")
	private String tipo;

	@Column(name = "nome")
	private String nome;

	@Column(name = "situacao")
	private String situacao;

	@Column(name = "cpf")
	private String cpf;

	@Column(name = "celular")
	private String celular;

	@Column(name = "email")
	private String email;

	@Column(name = "foto")
	private byte[] foto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dados_pro_id")
	private DadosPro dadosPro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public DadosPro getDadosPro() {
		return dadosPro;
	}

	public void setDadosPro(DadosPro dadosPro) {
		this.dadosPro = dadosPro;
	}

}