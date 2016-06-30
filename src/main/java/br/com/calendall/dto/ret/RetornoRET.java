package br.com.calendall.dto.ret;

import java.util.List;

public class RetornoRET {

	private boolean ok;
	private List<ErroRET> erros;

	public boolean isOk() {
		return ok;
	}

	public List<ErroRET> getErros() {
		return erros;
	}

	public RetornoRET() {
		super();
		this.ok = true;
	}

	public RetornoRET(List<ErroRET> erros) {
		super();
		this.ok = false;
		this.erros = erros;
	}

	public RetornoRET(Exception e) {
		super();
		this.ok = false;
	}
	
}
