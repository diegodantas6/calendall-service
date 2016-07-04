package br.com.calendall.enums;

public enum SituacaoUsuario {

	ATIVO("A"), BLOQUEADO("B");
	
	private String sittuacao;
	
	public String getSittuacao() {
		return sittuacao;
	}
	
	private SituacaoUsuario(String sittuacao) {
		this.sittuacao = sittuacao;
	}
	
}
