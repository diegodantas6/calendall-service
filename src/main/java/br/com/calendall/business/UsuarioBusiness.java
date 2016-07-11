package br.com.calendall.business;

import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.calendall.dto.in.AlterarSenhaIN;
import br.com.calendall.dto.in.CadastroCartaoIN;
import br.com.calendall.dto.in.CadastroFormaPagamentoIN;
import br.com.calendall.dto.in.CadastroUsuarioEnderecoIN;
import br.com.calendall.dto.in.CadastroUsuarioIN;
import br.com.calendall.dto.in.LoginIN;
import br.com.calendall.dto.in.RecuperarSenhaIN;
import br.com.calendall.dto.out.CadastroUsuarioOUT;
import br.com.calendall.dto.out.ErroOUT;
import br.com.calendall.dto.out.LoginOUT;
import br.com.calendall.dto.out.RetornoOUT;
import br.com.calendall.enums.SituacaoUsuario;
import br.com.calendall.model.DadosCartao;
import br.com.calendall.model.DadosPro;
import br.com.calendall.model.Usuario;
import br.com.calendall.utils.BeanValidator;
import br.com.calendall.utils.Email;

@Stateless
public class UsuarioBusiness {

	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	private BeanValidator beanValidator;

	@EJB
	private Email email;

	public boolean loginFilter(LoginIN in) {
		TypedQuery<Usuario> query = entityManager.createNamedQuery("login", Usuario.class);
		query.setParameter("email", in.getEmail());
		query.setParameter("senha", in.getSenha());

		try {
			query.getSingleResult();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public LoginOUT login(LoginIN in) {
		TypedQuery<Usuario> query = entityManager.createNamedQuery("login", Usuario.class);
		query.setParameter("email", in.getEmail());
		query.setParameter("senha", in.getSenha());

		try {
			Usuario usuario = query.getSingleResult();

			return new LoginOUT(usuario.getId(), usuario.getNome());
		} catch (Exception e) {
			return new LoginOUT(e);
		}
	}

	public RetornoOUT recuperarSenha(RecuperarSenhaIN in) {
		try {
			TypedQuery<Usuario> query = entityManager.createNamedQuery("findUsuarioByEmail", Usuario.class);
			query.setParameter("email", in.getEmail());

			Usuario usuario = query.getSingleResult();
			String senha = getNewPassword();
			usuario.setSenha(senha);
			entityManager.merge(usuario);

			StringBuilder html = new StringBuilder();

			// html.append("Para alterar sua senha clique no link abaixo:");
			// html.append("<br><br>");
			// html.append("<a
			// href='http://localhost:8080/calendall-service/rest/service/recuperar_senha/");
			// html.append(usuario.getId());
			// html.append("'>Clique aqui</a><br><br>");
			// html.append("Caso não tenha solicitado, Favor desconsiderar este
			// email.");

			html.append("Olá ");
			html.append(usuario.getNome());
			html.append("!<br><br>");
			html.append("Sua nova senha é: ");
			html.append(senha);
			html.append("<br><br>");
			html.append("Equipe Calendall");

			boolean enviado = email.enviaEmail(usuario.getEmail(), usuario.getNome(), "recuperarSenha",
					html.toString());

			if (enviado) {
				return new RetornoOUT();
			} else {
				return new RetornoOUT(false);
			}
		} catch (Exception e) {
			return new RetornoOUT(e);
		}
	}

	public RetornoOUT alterarSenha(AlterarSenhaIN in) {
		try {
			List<ErroOUT> erros = beanValidator.validar(in);

			if (erros.size() > 0) {
				return new RetornoOUT(erros);
			} else {
				Usuario usuario = entityManager.find(Usuario.class, in.getId());

				usuario.setSenha(in.getSenha());

				entityManager.merge(usuario);

				return new RetornoOUT();
			}
		} catch (Exception e) {
			return new RetornoOUT(e);
		}
	}

	public CadastroUsuarioOUT cadastroUsuario(CadastroUsuarioIN in) {
		try {
			List<ErroOUT> erros = beanValidator.validar(in);

			TypedQuery<Usuario> query = entityManager.createNamedQuery("findUsuarioByEmail", Usuario.class);
			query.setParameter("email", in.getEmail());

			Usuario usuarioEmail;
			try {
				usuarioEmail = query.getSingleResult();
			} catch (NoResultException e) {
				usuarioEmail = null;
			}

			if (usuarioEmail != null) {
				ErroOUT erroOUT = new ErroOUT("email", "Email ja cadastrado!");
				erros.add(erroOUT);
			}

			if (!(email.isEmailValid(in.getEmail()))) {
				ErroOUT erroOUT = new ErroOUT("email", "Email invalido!");
				erros.add(erroOUT);
			}

			if (erros.size() > 0) {
				return new CadastroUsuarioOUT(erros);
			} else {
				Usuario usuario = new Usuario();

				usuario.setNome(in.getNome());
				usuario.setEmail(in.getEmail());
				usuario.setSenha(in.getSenha());
				usuario.setTipo(in.getTipo());
				usuario.setSituacao(SituacaoUsuario.ATIVO.getSittuacao());

				entityManager.persist(usuario);

				return new CadastroUsuarioOUT(usuario.getId());
			}
		} catch (Exception e) {
			return new CadastroUsuarioOUT(e);
		}
	}

	public RetornoOUT cadastroUsuarioEndereco(CadastroUsuarioEnderecoIN in) {
		try {
			List<ErroOUT> erros = beanValidator.validar(in);

			TypedQuery<Usuario> query = entityManager.createNamedQuery("findUsuarioByCpf", Usuario.class);
			query.setParameter("cpf", in.getCpf());

			Usuario usuarioCpf;
			try {
				usuarioCpf = query.getSingleResult();
			} catch (NoResultException e) {
				usuarioCpf = null;
			}

			if (usuarioCpf != null) {
				ErroOUT erroOUT = new ErroOUT("cpf", "CPF ja cadastrado!");
				erros.add(erroOUT);
			}

			if (erros.size() > 0) {
				return new RetornoOUT(erros);
			} else {

				DadosPro dadosPro = new DadosPro();
				dadosPro.setCep(in.getCep());
				dadosPro.setNumero(in.getNumero());
				dadosPro.setComplemento(in.getComplemento());
				dadosPro.setTipoAtendimento(in.getTipoAtendimento());
				dadosPro.setTempoEntreAgendas(30);

				entityManager.persist(dadosPro);

				Usuario usuario = entityManager.find(Usuario.class, in.getId());
				usuario.setCpf(in.getCpf());
				usuario.setCelular(in.getCelular());
				usuario.setDadosPro(dadosPro);

				entityManager.merge(usuario);

				return new RetornoOUT();
			}
		} catch (Exception e) {
			return new RetornoOUT(e);
		}
	}

	public RetornoOUT cadastroFormaPagamento(CadastroFormaPagamentoIN in) {
		try {
			List<ErroOUT> erros = beanValidator.validar(in);

			if (erros.size() > 0) {
				return new RetornoOUT(erros);
			} else {

				Usuario usuario = entityManager.find(Usuario.class, in.getId());

				DadosPro dadosPro = usuario.getDadosPro();

				dadosPro.setTipoPagamento(in.getTipoPagamento());

				entityManager.merge(dadosPro);

				return new RetornoOUT();
			}
		} catch (Exception e) {
			return new RetornoOUT(e);
		}
	}

	public RetornoOUT cadastroUsuarioCartao(CadastroCartaoIN in) {
		try {
			List<ErroOUT> erros = beanValidator.validar(in);

			if (erros.size() > 0) {
				return new RetornoOUT(erros);
			} else {

				Usuario usuario = entityManager.find(Usuario.class, in.getId());
				
				DadosCartao cartao = new DadosCartao();
				cartao.setNumero(in.getNumero());
				cartao.setNome(in.getNome());
				cartao.setCvv(in.getCvv());
				cartao.setMes(in.getMes());
				cartao.setAno(in.getAno());
				cartao.setProfissional(usuario);
				cartao.setSituacao("A");
				cartao.setBandeira(1);
				
				entityManager.persist(cartao);

				return new RetornoOUT();
			}
		} catch (Exception e) {
			return new RetornoOUT(e);
		}
	}

	private String getNewPassword() {
		int min = 100000;
		int max = 999999;

		Random rand = new Random();
		Integer numero = rand.nextInt(max + 1);
		Integer soma = 0;

		if (numero < min)
			soma = numero + min;
		else
			soma = numero;

		return soma.toString();
	}
}
