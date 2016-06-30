package br.com.calendall.dto.ret;

import java.util.List;

public class CadastroUsuarioRET {

	private Long id;
	private List<ErroRET> erros;

	public Long getId() {
		return id;
	}

	public List<ErroRET> getErros() {
		return erros;
	}
	
	public CadastroUsuarioRET() {
		super();
	}

	public CadastroUsuarioRET(Long id) {
		super();
		this.id = id;
	}

	public CadastroUsuarioRET(Long id, List<ErroRET> erros) {
		super();
		this.id = id;
		this.erros = erros;
	}
}
