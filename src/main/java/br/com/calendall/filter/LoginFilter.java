package br.com.calendall.filter;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import br.com.calendall.business.UsuarioBusiness;
import br.com.calendall.dto.in.LoginIN;

@Provider
public class LoginFilter implements ContainerRequestFilter {

	/**
	 * Servicos que nao serao validados pelo filtro
	 */
	private static final String LOGIN = "/service/login";
	private static final String ALTERAR_SENHA = "/service/alterar_senha";
	private static final String CADASTRO_USUARIO = "/service/cadastro_usuario";
	private static final String RECUPERAR_SENHA = "/service/recuperar_senha";

	@EJB
	private UsuarioBusiness usuarioBusiness;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		String servico = requestContext.getUriInfo().getPath();

		if (!(servico.equals(LOGIN) || servico.equals(CADASTRO_USUARIO) || servico.equals(RECUPERAR_SENHA)
				|| servico.equals(ALTERAR_SENHA))) {
			String email = requestContext.getHeaderString("user");
			String senha = requestContext.getHeaderString("pass");

			LoginIN dto = new LoginIN(email, senha);

			if (!(usuarioBusiness.loginFilter(dto))) {
				requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
						.entity("User cannot access the resource.").build());
			}
		}
	}
}
