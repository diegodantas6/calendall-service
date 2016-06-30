package br.com.calendall.dto.ret;

public class ErroRET {

	private String campo;
	private String mensagem;

	public String getCampo() {
		return campo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public ErroRET() {
		super();
	}

	public ErroRET(String campo, String mensagem) {
		super();
		this.campo = campo;
		this.mensagem = mensagem;
	}

}
