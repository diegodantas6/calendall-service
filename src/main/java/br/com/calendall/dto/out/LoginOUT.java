package br.com.calendall.dto.out;

import java.util.List;

public class LoginOUT extends RetornoOUT {

	private Long id;
	private String nome;
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public LoginOUT(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public LoginOUT(List<ErroOUT> erros) {
		super(erros);
	}

	public LoginOUT(Exception e) {
		super(e);
	}
}
