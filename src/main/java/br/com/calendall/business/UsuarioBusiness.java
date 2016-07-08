package br.com.calendall.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.calendall.dto.in.AlterarSenhaIN;
import br.com.calendall.dto.in.CadastroUsuarioEnderecoIN;
import br.com.calendall.dto.in.CadastroUsuarioIN;
import br.com.calendall.dto.in.LoginIN;
import br.com.calendall.dto.in.RecuperarSenhaIN;
import br.com.calendall.dto.out.CadastroUsuarioOUT;
import br.com.calendall.dto.out.ErroOUT;
import br.com.calendall.dto.out.LoginOUT;
import br.com.calendall.dto.out.RetornoOUT;
import br.com.calendall.enums.SituacaoUsuario;
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

			boolean enviado = email.enviaEmail(usuario.getEmail(), usuario.getNome(), "recuperarSenha", "asd<br>asd<br>asd");

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
}
