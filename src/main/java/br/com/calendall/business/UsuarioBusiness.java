package br.com.calendall.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.calendall.dto.AlterarSenhaDTO;
import br.com.calendall.dto.CadastroUsuarioDTO;
import br.com.calendall.dto.LoginDTO;
import br.com.calendall.dto.ret.CadastroUsuarioRET;
import br.com.calendall.dto.ret.ErroRET;
import br.com.calendall.enums.SituacaoUsuario;
import br.com.calendall.model.Usuario;
import br.com.calendall.utils.BeanValidator;

@Stateless
public class UsuarioBusiness {

	@PersistenceContext
	private EntityManager entityManager;
	
	@EJB
	private BeanValidator beanValidator;
	
	public boolean login(LoginDTO dto) {
		if (dto.getLogin() != null && dto.getSenha() != null) {
			TypedQuery<Usuario> query = entityManager.createNamedQuery("login", Usuario.class);
			query.setParameter("login", dto.getLogin());
			query.setParameter("senha", dto.getSenha());
			
			try {
				query.getSingleResult();
				
				return true;
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean alterarSenha(AlterarSenhaDTO dto) {
		try {
			Usuario usuario = entityManager.find(Usuario.class, dto.getId());
			
			usuario.setSenha(dto.getSenha());
			
			entityManager.merge(usuario);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public CadastroUsuarioRET cadastroUsuario(CadastroUsuarioDTO dto) {
		try {
			Usuario usuario = new Usuario();
			
			usuario.setNome(dto.getNome());
			usuario.setSenha(dto.getSenha());
			usuario.setLogin(dto.getLogin());
			usuario.setTipo(dto.getTipo());
			usuario.setSituacao(SituacaoUsuario.ATIVO.getSittuacao());
			
			List<ErroRET> erros = beanValidator.validar(usuario);
			
			if (erros.size() > 0) {
				return new CadastroUsuarioRET(0L, erros);
			} else {
				entityManager.persist(usuario);
				
				return new CadastroUsuarioRET(usuario.getId());
			}
		} catch (Exception e) {
			return new CadastroUsuarioRET(0L);
		}
	}
}
