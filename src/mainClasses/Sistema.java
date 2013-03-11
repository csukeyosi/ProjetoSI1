package mainclasses;

import java.io.Serializable;
import java.util.List;

import tiposordenacao.OrdenaFeedPrincipal;
import tiposordenacao.SonsMaisFavoritadosSistema;
import tiposordenacao.SonsMaisFavoritadosUsuario;
import tiposordenacao.SonsMaisRecentes;
import tiposordenacao.TipoFeedPrincipal;
import util.Utilitario;
import exception.AtributoInexistenteException;
import exception.AtributoInvalidoException;
import exception.DataInvalidaException;
import exception.LoginInexistenteException;
import exception.LoginInvalidoException;
import exception.RegraInexistenteException;
import exception.RegraInvalidaException;
import exception.SessaoInexistenteException;
import exception.SessaoInvalidaException;
import exception.SomInvalidoException;
import exception.UsuarioInexistenteException;

/**
 * Executa as acoes solicitadas pela {@link NetMusicLive}, gerencia as instrucoes.
 */
public class Sistema implements Serializable {

	private static final long serialVersionUID = 1L;
	private GerenciaSons gerenciaSons;
	private GerenciaSessao gerenciaSessao;
	private GerenciaUsuarios gerenciaUsuarios;
	private OrdenaFeedPrincipal feedPrincipal;

	public Sistema() {
		zerarSistema();
	}

	public void zerarSistema() {
		this.gerenciaSons = new GerenciaSons();
		this.gerenciaSessao = new GerenciaSessao();
		this.gerenciaUsuarios = new GerenciaUsuarios();
		feedPrincipal = new SonsMaisRecentes();
	}


	public void criarUsuario(String login, String senha, String nome,
			String email) throws Exception {
		this.gerenciaUsuarios.criarUsuario(login, senha, nome, email);
	}

	public boolean verificaLoginESenha(String login,String senha){
		return this.gerenciaUsuarios.verificaLoginESenha(login, senha);
	}

	public String abrirSessao(String login, String senha) throws Exception {
		if (!Utilitario.elementIsValid(login)) {
			throw new LoginInvalidoException();
		} else if (this.gerenciaUsuarios.VerificaAtributoExiste(login, "login")) {
			if (!verificaLoginESenha(login, senha)) {

				throw new  LoginInvalidoException();
			}
			return this.gerenciaSessao.abrirSessao(login, senha);
		} else {
			throw new UsuarioInexistenteException();
		}
	}

	public boolean existeSessao(String login){
		String idsessao = "sessao"+login;
		return this.gerenciaSessao.existeSessao(idsessao);
	}

	public String getAtributoUsuario(String login, String atributo) throws Exception {
		if (!Utilitario.elementIsValid(atributo)) {
			throw new AtributoInvalidoException();
		} else if (!Utilitario.elementIsValid(login)) {
			throw new LoginInvalidoException();
		} else if (!this.gerenciaUsuarios
				.VerificaAtributoExiste(login, "login")) {
			throw new UsuarioInexistenteException();
		} else {
			String result = this.gerenciaUsuarios.getAtributoUsuario(login,
					atributo);
			if (!result.isEmpty()) {
				return result;
			}
		}
		throw new AtributoInexistenteException();
	}

	public List<Som> getPerfilMusical(String idsessao) {
		return getUsuario(idsessao).getPerfilMusical();
	}

	public String postarSom(String sessao, String link, String dataCriacao)
			throws Exception {
		if (!Utilitario.elementIsValid(link)) {
			throw new SomInvalidoException();
		} else if (!Utilitario.dataIsValida(dataCriacao)) {
			throw new DataInvalidaException();
		}
		String login = this.gerenciaSessao.getLogin(sessao);
		Usuario user = this.gerenciaUsuarios.getUser(login, "login");
		return this.gerenciaSons.postarSom(user, link, dataCriacao);
	}

	public String getAtributoSom(String idSom, String atributo)
			throws Exception {
		if (!Utilitario.elementIsValid(idSom)) {
			throw new SomInvalidoException();
		} else if (!Utilitario.elementIsValid(atributo)) {
			throw new AtributoInvalidoException();
		}
		String result = this.gerenciaSons.getAtributoSom(idSom, atributo);
		if (!result.isEmpty()) {
			return result;
		}
		throw new AtributoInexistenteException();
	}

	public void seguirUsuario(String idSessaoSeguidor, String loginSeguido) throws Exception {
		verificaSessao(idSessaoSeguidor);

		if (!Utilitario.elementIsValid(loginSeguido)) {
			throw new LoginInvalidoException();
		} else if (this.gerenciaSessao.getLogin(idSessaoSeguidor).equals(loginSeguido)) {
			throw new LoginInvalidoException();
		}

		if (this.gerenciaUsuarios.VerificaAtributoExiste(loginSeguido, "login")) {
			Usuario userSeguido = this.gerenciaUsuarios.getUser(loginSeguido, "login");
			String loginSeguidor = this.gerenciaSessao.getLogin(idSessaoSeguidor);
			Usuario userSeguidor = this.gerenciaUsuarios.getUser(loginSeguidor, "login");

			this.gerenciaSons.seguirUsuario(userSeguidor, userSeguido);

		} else {
			throw new LoginInexistenteException();
		}
	}

	public List<Usuario> getListaDeSeguidores(String idsessao) throws Exception {
		verificaSessao(idsessao);
		return getUsuario(idsessao).getListaDeSeguidores();
	}

	public int getNumeroDeSeguidores(String idsessao) throws Exception {
		verificaSessao(idsessao);
		Usuario user = getUsuario(idsessao);
		return user.getNumeroDeSeguidores();
	}

	public List<Usuario> getFontesDeSons(String idsessao) throws Exception {
		verificaSessao(idsessao);
		Usuario user = getUsuario(idsessao);
		return user.getFontesDeSom();
	}

	public String getIDUsuario(String idsessao) throws Exception {
		verificaSessao(idsessao);
		String login = this.gerenciaSessao.getLogin(idsessao);
		return this.gerenciaUsuarios.getAtributoUsuario(login, "id");
	}

	public List<Som> getVisaoDosSons(String idsessao) throws Exception {
		verificaSessao(idsessao);
		return getUsuario(idsessao).getVisaoDosSons();
	}

	public List<Som> getSonsFavoritos(String idsessao) throws Exception {
		verificaSessao(idsessao);
		return getUsuario(idsessao).getSonsFavoritos();
	}

	public List<Som> getFeedExtra(String idsessao) throws Exception {
		verificaSessao(idsessao);
		return getUsuario(idsessao).getFeedExtra();
	}

	public void favoritarSom(String idsessao, String idsom) throws Exception {
		verificaSessao(idsessao);
		Usuario usuario = getUsuario(idsessao);
		Som som = gerenciaSons.getSom(idsom);
		som.incrementaFavoritos();
		usuario.addSonsFavoritos(som);
		gerenciaSons.addInFeedExtra(usuario.getListaDeSeguidores(), som);
	}

	public List<Som> getMainFeed(String idsessao) throws Exception {
		verificaSessao(idsessao);
		return feedPrincipal.ordena(getUsuario(idsessao));
	}

	public void setMainFeedRule(String idsessao, String rule) throws Exception {
		verificaSessao(idsessao);
		if (!Utilitario.elementIsValid(rule)) {
			throw new RegraInvalidaException();
		}

		if (rule.equals(TipoFeedPrincipal.SONS_RECENTES.toString())){
			feedPrincipal = new SonsMaisRecentes();
		} 
		else if (rule.equals(TipoFeedPrincipal.SONS_FAVORITADOS_SISTEMA.toString())){
			feedPrincipal = new SonsMaisFavoritadosSistema();
		}
		else if (rule.equals(TipoFeedPrincipal.SONS_FAVORITADOS_USUARIO.toString())){
			feedPrincipal = new SonsMaisFavoritadosUsuario();
		}else{
			throw new RegraInexistenteException();
		}
	}

	public void encerrarSessao(String login) {
		this.gerenciaSessao.encerrarSessao("sessao" + login);
	}

	public void encerrarSistema() {

	}

	/**
	 * Verifica se a sessao e valida/existe.
	 * @param idsessao
	 * 				Id da sessao a ser verificada.
	 * @throws Exception
	 * 				{@link SessaoInvalidaException, SessaoInexistenteException}
	 */
	private void verificaSessao(String idsessao) throws Exception {
		if (!Utilitario.elementIsValid(idsessao)) {
			throw new SessaoInvalidaException();
		} else if (!this.gerenciaSessao.existeSessao(idsessao)) {
			throw new SessaoInexistenteException();
		}
	}

	public Usuario getUsuario(String idsessao) {
		String loginUser = this.gerenciaSessao.getLogin(idsessao);
		return this.gerenciaUsuarios.getUser(loginUser, "login");
	}
}