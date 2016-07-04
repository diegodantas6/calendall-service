package br.com.calendall.ws;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.calendall.business.UsuarioBusiness;
import br.com.calendall.dto.in.AlterarSenhaIN;
import br.com.calendall.dto.in.CadastroUsuarioIN;
import br.com.calendall.dto.in.LoginIN;
import br.com.calendall.dto.in.RecuperarSenhaIN;
import br.com.calendall.dto.out.CadastroUsuarioOUT;
import br.com.calendall.dto.out.RetornoOUT;

@Stateless
@Path("service")
public class Service {

	@EJB
	private UsuarioBusiness usuarioBusiness;

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean login(LoginIN in) {
		return usuarioBusiness.login(in);
	}

	@POST
	@Path("/recuperar_senha")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean recuperarSenha(RecuperarSenhaIN in) {
		return usuarioBusiness.recuperarSenha(in);
	}

	@POST
	@Path("/alterar_senha")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RetornoOUT alterarSenha(AlterarSenhaIN in) {
		return usuarioBusiness.alterarSenha(in);
	}

	@POST
	@Path("/cadastro_usuairo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CadastroUsuarioOUT cadastroUsuario(CadastroUsuarioIN in) {
		return usuarioBusiness.cadastroUsuario(in);
	}
}
