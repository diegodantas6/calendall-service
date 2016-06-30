package br.com.calendall.filter;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import br.com.calendall.business.UsuarioBusiness;
import br.com.calendall.dto.LoginDTO;

@Provider
public class LoginFilter implements ContainerRequestFilter {

	/**
	 * Servicos que nao serao validados pelo filtro
	 */
	private static final String LOGIN = "/service/login";
	private static final String ALTERAR_SENHA = "/service/alterar_senha";
	private static final String CADASTRO_USUAIRO = "/service/cadastro_usuairo";

	@EJB
	private UsuarioBusiness usuarioBusiness;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		String servico = requestContext.getUriInfo().getPath();

		if (!(servico.equals(LOGIN) || servico.equals(CADASTRO_USUAIRO) || servico.equals(ALTERAR_SENHA))) {
			String login = requestContext.getHeaderString("user");
			String senha = requestContext.getHeaderString("pass");

			LoginDTO dto = new LoginDTO(login, senha);

			if (!(usuarioBusiness.login(dto))) {
				requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
						.entity("User cannot access the resource.").build());
			}
		}
	}
}
