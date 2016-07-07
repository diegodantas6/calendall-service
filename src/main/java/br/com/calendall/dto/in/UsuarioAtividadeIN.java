package br.com.calendall.dto.in;

import java.util.List;

public class UsuarioAtividadeIN {

    private Long usuario;
    private List<Long> atividades;

    public Long getUsuario() {
		return usuario;
	}
    
    public List<Long> getAtividades() {
		return atividades;
	}
    
}
