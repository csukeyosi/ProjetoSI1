package mainclasses;

import util.InterfaceWebAdapter;
import util.Menssagens;

import bean.DefaultBean;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;

public class UsuarioService extends DefaultBean {

	protected InterfaceWebAdapter interfaceWebAdapter;

	public void authFacebookLogin(String accessToken, int expires) {
		try {
			FacebookClient fb = new DefaultFacebookClient(accessToken);

			UsuarioFB user = fb.fetchObject("me", UsuarioFB.class);
			System.out.println(user);
			interfaceWebAdapter.criaUsuario(user.getUsername(),
					user.getUsername(), user.getName(), user.getEmail());
			if (!interfaceWebAdapter.existeSessao(getLogin())) {
				String idsessao = interfaceWebAdapter.abrirSessao(getLogin(),
						getSenha());
				putInSession("idsessao", idsessao);
			}
		} catch (Exception ex) {
			Menssagens.addMsgErro(ex.getMessage());
		}
	}
}
