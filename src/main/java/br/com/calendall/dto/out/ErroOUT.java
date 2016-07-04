package br.com.calendall.dto.out;

public class ErroOUT {

	private String campo;
	private String mensagem;

	public String getCampo() {
		return campo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public ErroOUT() {
		super();
	}

	public ErroOUT(String campo, String mensagem) {
		super();
		this.campo = campo;
		this.mensagem = mensagem;
	}

}
