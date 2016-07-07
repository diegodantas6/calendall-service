package br.com.calendall.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.calendall.dto.in.DadoAtividadeIN;
import br.com.calendall.dto.in.UsuarioAtividadeIN;
import br.com.calendall.dto.out.ErroOUT;
import br.com.calendall.dto.out.RetornoOUT;
import br.com.calendall.dto.out.UsuarioAtividadeOUT;
import br.com.calendall.model.Atividade;
import br.com.calendall.model.Usuario;
import br.com.calendall.model.UsuarioAtividade;
import br.com.calendall.utils.BeanValidator;

@Stateless
public class AtividadeBusiness {

	@PersistenceContext
	private EntityManager entityManager;
	
	@EJB
	private BeanValidator beanValidator;

	public List<Atividade> atividades() {
		TypedQuery<Atividade> query = entityManager.createNamedQuery("atividades", Atividade.class);

		return query.getResultList();
	}

	public List<UsuarioAtividadeOUT> usuarioAtividades(UsuarioAtividadeIN in) {
		try {
			Usuario usuario = entityManager.find(Usuario.class, in.getUsuario());
			
			TypedQuery<Long> query = entityManager.createNamedQuery("id_atividades", Long.class);
			query.setParameter("usuario", usuario);
			List<Long> currentAtividades = query.getResultList();
			
			List<UsuarioAtividadeOUT> out = new ArrayList<UsuarioAtividadeOUT>();
			
			UsuarioAtividade usuarioAtividade;
			
			for (Long idAtividade : in.getAtividades()) {
				Atividade atividade = entityManager.find(Atividade.class, idAtividade);

				usuarioAtividade = new UsuarioAtividade();
				
				usuarioAtividade.setProfissional(usuario);
				usuarioAtividade.setAtividade(atividade);
				usuarioAtividade.setPreço(0D);
				usuarioAtividade.setTempo(15);
				
				entityManager.persist(usuarioAtividade);
				
				out.add(new UsuarioAtividadeOUT(usuarioAtividade.getId(), atividade.getNome(), usuarioAtividade.getPreço(), usuarioAtividade.getTempo()));
			}

			return out;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public RetornoOUT dadoAtividades(List<DadoAtividadeIN> in) {
		try {
			List<ErroOUT> erros = beanValidator.validarList(in);

			if (erros.size() > 0) {
				return new RetornoOUT(erros);
			} else {
				for (DadoAtividadeIN dados : in) {
					UsuarioAtividade usuarioAtividade = entityManager.find(UsuarioAtividade.class, dados.getId());
					usuarioAtividade.setPreço(dados.getPreco());
					usuarioAtividade.setTempo(dados.getTempo());
					entityManager.merge(usuarioAtividade);
				}

				return new RetornoOUT();
			}
		} catch (Exception e) {
			return new RetornoOUT(e);
		}
	}
}