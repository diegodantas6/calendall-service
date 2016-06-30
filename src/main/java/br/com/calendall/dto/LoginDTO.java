package br.com.calendall.dto;

public class LoginDTO {

	private String login;
	private String senha;

	public String getLogin() {
		return login;
	}
	
	public String getSenha() {
		return senha;
	}

	public LoginDTO() {
		super();
	}

	public LoginDTO(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}
}
