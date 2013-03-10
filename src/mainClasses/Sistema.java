package mainclasses;

import java.io.Serializable;
import java.util.List;

import ordenafeed.OrdenaFeedPrincipal;
import ordenafeed.SonsMaisFavoritadosSistema;
import ordenafeed.SonsMaisFavoritadosUsuario;
import ordenafeed.SonsMaisRecentes;
import ordenafeed.TipoFeedPrincipal;
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

public class Sistema implements Serializable {

	private static final long serialVersionUID = 1L;
	private GerenciaSons gerenciaSons;
	private GerenciaSessao gerenciaSessao;
	private GerenciaUsuarios gerenciaUsuarios;
	private static Sistema sistema;
	private OrdenaFeedPrincipal feedPrincipal;

	private Sistema() {
		zerarSistema();
	}

	/* Padrao Singleton. */
	public static Sistema getInstance() {
		if (sistema == null) {
			sistema = new Sistema();
		}
		return sistema;
	}

	/**
	 * Metodo que inicializa e limpa o sistema.
	 */
	public void zerarSistema() {
		this.gerenciaSons = new GerenciaSons();
		this.gerenciaSessao = new GerenciaSessao();
		this.gerenciaUsuarios = new GerenciaUsuarios();
		feedPrincipal = new SonsMaisRecentes();
	}

	/**
	 * Cria um novo usuario no sistema.
	 * 
	 * @param login
	 *            String login do novo usuario.
	 * @param senha
	 *            String senha do novo usuario.
	 * @param nome
	 *            String nome do novo usuario.
	 * @param email
	 *            String e-mail do novo usuario.
	 * @throws Exception
	 */
	public void criarUsuario(String login, String senha, String nome,
			String email) throws Exception {
		this.gerenciaUsuarios.criarUsuario(login, senha, nome, email);
	}

	/**
	 * 
	 * @param login
	 * @param senha
	 * @return
	 * @throws Exception
	 */
	public String abrirSessao(String login, String senha) throws Exception {
		if (!Utilitario.elementIsValid(login)) {
			throw new LoginInvalidoException();
		} else if (this.gerenciaUsuarios.VerificaAtributoExiste(login, "login")) {
			if (!this.gerenciaUsuarios.verificaLoginESenha(login, senha)) {
				throw new  LoginInvalidoException();
			}
			return this.gerenciaSessao.abrirSessao(login, senha);
		} else {
			throw new UsuarioInexistenteException();
		}
	}

	/**
	 * 
	 * @param login
	 * @param atributo
	 * @return
	 * @throws Exception
	 */
	public String getAtributoUsuario(String login, String atributo)
			throws Exception {
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

	/**
	 * 
	 * @param idsessao
	 * @return
	 */
	public List<Som> getPerfilMusical(String idsessao) {
		return getUsuario(idsessao).getPerfilMusical();
	}

	/**
	 * 
	 * @param sessao
	 * @param link
	 * @param dataCriacao
	 * @return
	 * @throws Exception
	 */
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

	/**
	 * 
	 * @param idSom
	 * @param atributo
	 * @return
	 * @throws Exception
	 */
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

	/**
	 * 
	 * @param idSessaoSeguidor
	 * @param loginSeguido
	 * @throws Exception
	 */
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

	/**
	 * 
	 * @param idsessao
	 * @return
	 * @throws Exception
	 */
	public List<Usuario> getListaDeSeguidores(String idsessao) throws Exception {
		verificaSessao(idsessao);
		return getUsuario(idsessao).getListaDeSeguidores();
	}

	/**
	 * 
	 * @param idsessao
	 * @return
	 * @throws Exception
	 */
	public int getNumeroDeSeguidores(String idsessao) throws Exception {
		verificaSessao(idsessao);
		Usuario user = getUsuario(idsessao);
		return user.getNumeroDeSeguidores();
	}

	/**
	 * 
	 * @param idsessao
	 * @return
	 * @throws Exception
	 */
	public List<Usuario> getFontesDeSons(String idsessao) throws Exception {
		verificaSessao(idsessao);
		Usuario user = getUsuario(idsessao);
		return user.getFontesDeSom();
	}

	/**
	 * Metodo que retorna o id de uma determinado usuario a partir do
	 * identificador de uma sessao.
	 * 
	 * @param sessao
	 * @return
	 */
	public String getIDUsuario(String sessao) throws Exception {
		String login = this.gerenciaSessao.getLogin(sessao);
		return this.gerenciaUsuarios.getAtributoUsuario(login, "id");
	}

	/**
	 * 
	 * @param idsessao
	 * @return
	 * @throws Exception
	 */
	public List<Som> getVisaoDosSons(String idsessao) throws Exception {
		verificaSessao(idsessao);
		return getUsuario(idsessao).getVisaoDosSons();
	}

	/**
	 * 
	 * @param idsessao
	 * @return
	 * @throws Exception
	 */
	public List<Som> getSonsFavoritos(String idsessao) throws Exception {
		verificaSessao(idsessao);
		return getUsuario(idsessao).getSonsFavoritos();
	}

	/**
	 * 
	 * @param idsessao
	 * @return
	 * @throws Exception
	 */
	public List<Som> getFeedExtra(String idsessao) throws Exception {
		verificaSessao(idsessao);
		return getUsuario(idsessao).getFeedExtra();
	}

	/**
	 * 
	 * @param idsessao
	 * @param idsom
	 * @throws Exception
	 */
	public void favoritarSom(String idsessao, String idsom) throws Exception {
		verificaSessao(idsessao);
		Usuario usuario = getUsuario(idsessao);
		Som som = gerenciaSons.getSom(idsom);
		som.incrementaFavoritos();
		usuario.addSonsFavoritos(som);
		gerenciaSons.addInFeedExtra(usuario.getListaDeSeguidores(), som);
	}

	/**
	 * 
	 * @param idsessao
	 * @return
	 * @throws Exception
	 */
	public List<Som> getMainFeed(String idsessao) throws Exception {
		verificaSessao(idsessao);
		return feedPrincipal.ordena(getUsuario(idsessao));
	}

	/**
	 * 
	 * @param idsessao
	 * @param rule
	 * @throws Exception
	 */
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

	/**
	 * 
	 * @param login
	 */
	public void encerrarSessao(String login) {
		this.gerenciaSessao.encerrarSessao("sessao" + login);
	}

	/**
	 * Metodo que ao ser chamado finaliza o sistema.
	 */
	public void encerrarSistema() {

	}

	/**
	 * 
	 * @param idsessao
	 * @throws Exception
	 */
	private void verificaSessao(String idsessao) throws Exception {
		if (!Utilitario.elementIsValid(idsessao)) {
			throw new SessaoInvalidaException();
		} else if (!this.gerenciaSessao.existeSessao(idsessao)) {
			throw new SessaoInexistenteException();
		}
	}

	/**
	 * 
	 * @param idsessao
	 * @return
	 */
	public Usuario getUsuario(String idsessao) {
		String loginUser = this.gerenciaSessao.getLogin(idsessao);
		return this.gerenciaUsuarios.getUser(loginUser, "login");
	}
}