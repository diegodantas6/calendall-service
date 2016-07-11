package br.com.calendall.ws;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.calendall.business.AtividadeBusiness;
import br.com.calendall.business.UsuarioBusiness;
import br.com.calendall.dto.in.AlterarSenhaIN;
import br.com.calendall.dto.in.CadastroCartaoIN;
import br.com.calendall.dto.in.CadastroFormaPagamentoIN;
import br.com.calendall.dto.in.CadastroUsuarioEnderecoIN;
import br.com.calendall.dto.in.CadastroUsuarioIN;
import br.com.calendall.dto.in.DadoAtividadeIN;
import br.com.calendall.dto.in.LoginIN;
import br.com.calendall.dto.in.RecuperarSenhaIN;
import br.com.calendall.dto.in.UsuarioAtividadeIN;
import br.com.calendall.dto.out.CadastroUsuarioOUT;
import br.com.calendall.dto.out.LoginOUT;
import br.com.calendall.dto.out.RetornoOUT;
import br.com.calendall.dto.out.UsuarioAtividadeOUT;
import br.com.calendall.model.Atividade;

@Stateless
@Path("service")
public class Service {

	@EJB
	private UsuarioBusiness usuarioBusiness;

	@EJB
	private AtividadeBusiness atividadeBusiness;

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LoginOUT login(LoginIN in) {
		return usuarioBusiness.login(in);
	}

	@POST
	@Path("/recuperar_senha")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RetornoOUT recuperarSenha(RecuperarSenhaIN in) {
		return usuarioBusiness.recuperarSenha(in);
	}

//	@POST
//	@Path("/recuperar_senha/{id}")
//	@Produces(MediaType.TEXT_HTML)
//	public String recuperarSenhaForm(@PathParam("id") Long id) {
//		StringBuilder html = new StringBuilder();
//		
//		html.append("<h1>Teste</h1>");
//		
//		return html.toString();
//	}

	@POST
	@Path("/alterar_senha")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RetornoOUT alterarSenha(AlterarSenhaIN in) {
		return usuarioBusiness.alterarSenha(in);
	}

	@POST
	@Path("/cadastro_usuario")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CadastroUsuarioOUT cadastroUsuario(CadastroUsuarioIN in) {
		return usuarioBusiness.cadastroUsuario(in);
	}

	@POST
	@Path("/cadastro_usuario_endereco")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RetornoOUT cadastroUsuarioEndereco(CadastroUsuarioEnderecoIN in) {
		return usuarioBusiness.cadastroUsuarioEndereco(in);
	}

	@POST
	@Path("/cadastro_forma_pagamento")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RetornoOUT cadastroFormaPagamento(CadastroFormaPagamentoIN in) {
		return usuarioBusiness.cadastroFormaPagamento(in);
	}

	@POST
	@Path("/cadastro_usuario_cartao")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RetornoOUT cadastroUsuarioCartao(CadastroCartaoIN in) {
		return usuarioBusiness.cadastroUsuarioCartao(in);
	}

	@POST
	@Path("/atividades")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Atividade> atividades() {
		return atividadeBusiness.atividades();
	}

	@POST
	@Path("/usuario_atividades")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<UsuarioAtividadeOUT> usuarioAtividades(UsuarioAtividadeIN in) {
		return atividadeBusiness.usuarioAtividades(in);
	}

	@POST
	@Path("/dado_atividades")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RetornoOUT dadoAtividades(List<DadoAtividadeIN> in) {
		return atividadeBusiness.dadoAtividades(in);
	}
}
