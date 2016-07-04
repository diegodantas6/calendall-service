package br.com.calendall.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.calendall.dto.in.AlterarSenhaIN;
import br.com.calendall.dto.in.CadastroUsuarioIN;
import br.com.calendall.dto.in.LoginIN;
import br.com.calendall.dto.in.RecuperarSenhaIN;
import br.com.calendall.dto.out.CadastroUsuarioOUT;
import br.com.calendall.dto.out.ErroOUT;
import br.com.calendall.dto.out.RetornoOUT;
import br.com.calendall.enums.SituacaoUsuario;
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

	public boolean login(LoginIN in) {
		TypedQuery<Usuario> query = entityManager.createNamedQuery("login", Usuario.class);
		query.setParameter("login", in.getLogin());
		query.setParameter("senha", in.getSenha());

		try {
			query.getSingleResult();

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean recuperarSenha(RecuperarSenhaIN in) {
		try {
			TypedQuery<Usuario> query = entityManager.createNamedQuery("recuperar_senha", Usuario.class);
			query.setParameter("login", in.getLogin());

			//Usuario usuario = query.getSingleResult();

			 return email.enviaEmail("diegodantas6@gmail.com", "D2", "teste", "asd<br>asd<li>asd");
		} catch (Exception e) {
			return false;
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

			if (erros.size() > 0) {
				return new CadastroUsuarioOUT(erros);
			} else {
				Usuario usuario = new Usuario();

				usuario.setNome(in.getNome());
				usuario.setSenha(in.getSenha());
				usuario.setLogin(in.getLogin());
				usuario.setTipo(in.getTipo());
				usuario.setSituacao(SituacaoUsuario.ATIVO.getSittuacao());

				entityManager.persist(usuario);

				return new CadastroUsuarioOUT(usuario.getId());
			}
		} catch (Exception e) {
			return new CadastroUsuarioOUT(e);
		}
	}
}
