package br.com.calendall.dto.out;

import java.util.List;

public class CadastroUsuarioOUT extends RetornoOUT {

	private Long id;
	
	public Long getId() {
		return id;
	}

	public CadastroUsuarioOUT(Long id) {
		super();
		this.id = id;
	}
	
	public CadastroUsuarioOUT(List<ErroOUT> erros) {
		super(erros);
	}

	public CadastroUsuarioOUT(Exception e) {
		super(e);
	}
}
