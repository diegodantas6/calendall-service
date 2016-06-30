package br.com.calendall.ws;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.calendall.business.UsuarioBusiness;
import br.com.calendall.dto.AlterarSenhaDTO;
import br.com.calendall.dto.CadastroUsuarioDTO;
import br.com.calendall.dto.LoginDTO;
import br.com.calendall.dto.ret.CadastroUsuarioRET;
import br.com.calendall.dto.ret.RetornoRET;

@Stateless
@Path("service")
public class Service {

	@EJB
	private UsuarioBusiness usuarioBusiness;

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean login(LoginDTO dto) {
		return usuarioBusiness.login(dto);
	}

	// TODO preciso dos dados de email para fazer esse servico
	@POST
	@Path("/recuperar_senha")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean recuperarSenha(LoginDTO dto) {
		return usuarioBusiness.login(dto);
	}

	@POST
	@Path("/alterar_senha")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RetornoRET alterarSenha(AlterarSenhaDTO dto) {
		return usuarioBusiness.alterarSenha(dto);
	}

	@POST
	@Path("/cadastro_usuairo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CadastroUsuarioRET cadastroUsuario(CadastroUsuarioDTO dto) {
		return usuarioBusiness.cadastroUsuario(dto);
	}
}
