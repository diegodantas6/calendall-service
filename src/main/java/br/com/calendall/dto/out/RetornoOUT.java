package br.com.calendall.dto.out;

import java.util.List;

public class RetornoOUT {

	private boolean ok;
	private List<ErroOUT> erros;

	public boolean isOk() {
		return ok;
	}

	public List<ErroOUT> getErros() {
		return erros;
	}

	public RetornoOUT() {
		super();
		this.ok = true;
	}

	public RetornoOUT(boolean ok) {
		super();
		this.ok = ok;
	}

	public RetornoOUT(List<ErroOUT> erros) {
		super();
		this.ok = false;
		this.erros = erros;
	}

	public RetornoOUT(Exception e) {
		super();
		this.ok = false;
	}
	
}
