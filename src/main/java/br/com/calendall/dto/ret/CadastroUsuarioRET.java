package br.com.calendall.dto.ret;

import java.util.List;

public class CadastroUsuarioRET extends RetornoRET {

	private Long id;
	
	public Long getId() {
		return id;
	}

	public CadastroUsuarioRET(Long id) {
		super();
		this.id = id;
	}
	
	public CadastroUsuarioRET(List<ErroRET> erros) {
		super(erros);
	}

	public CadastroUsuarioRET(Exception e) {
		super(e);
	}
}
