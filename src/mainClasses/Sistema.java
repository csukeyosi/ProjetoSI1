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
 * Classe responsavel pela representacao do sistema e suas principais acoes.
 */
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

	/**
	 * Recupera a instancia de Sistema. Caso seja null, uma intancia é criada.

	 * @return Instancia de Sistema.
	 */
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
	 * Metodo que executa a criacao de um novo usuario no sistema. 
	 *  
	 * @param login
	 * 			Login do usuario a ser adicionado.
	 * @param senha
	 * 			Senha do usuario a ser adicinado.
	 * @param nome
	 * 			Nome do usuario a ser adicionado.
	 * @param email
	 * 			Email do usuario a ser adicionado.
	 * @throws Exception
	 * 			{@link LoginInvalidoException, NomeInvalidoException, EmailInvalidoException, 
	 * 			LoginDuplicadoException, EmailDuplicadoException}
	 */
	public void criarUsuario(String login, String senha, String nome,
			String email) throws Exception {
		this.gerenciaUsuarios.criarUsuario(login, senha, nome, email);
	}

	/**
	 * Da inicio a uma sessao.
	 * 
	 * @param login
	 *            Login do usuario.
	 * @param senha
	 *            Senha do usuario.
	 * @return Id da sessao.
	 * @throws Exception
	 * 			  {@link LoginInvalidoException, UsuarioInexistenteException}
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

	/** Metodo que recebe o login de um determinado Usuario e retorna o valor do atributo especificado
	 * no segundo parametro.
	 * 
	 * @param login
	 * 			Login do usuario a ser pesquisado.
	 * @param atributo
	 * 			Atributo a ser pesquisado (nome, email ou id).
	 * @return O valor do atributo.
	 * @throws Exception
	 * 			{@link AtributoInvalidoException, LoginInvalidoException, UsuarioInexistenteException, 
	 * 			AtributoInexistenteException}
	 */
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

	/**
	 * Retorna o perfil musical.
	 * @param idsessao
	 * 				Id da sessao.
	 * @return	Lista de som correspondente ao perfil musical.
	 */
	public List<Som> getPerfilMusical(String idsessao) {
		return getUsuario(idsessao).getPerfilMusical();
	}

	/**
	 * Simula a acao do usuario postar um novo som. 
	 * 
	 * @param user
	 * 			Usuario que esta postando o som.
	 * @param link
	 * 			Link do som que esta sendo postado.
	 * @param dataCriacao
	 * 			Data da postagem.
	 * @return O id do som adicionado.
	 * @throws Exception
	 * 			{@link SomInvalidoException, DataInvalidaException}
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
	 * Retorna o valor do atributo do idsom passado como parametro.
	 * 
	 * @param idSom
	 * 			Id do som que deseja-se saber o valor do atributo.
	 * @param atributo
	 * 			Atributo que deseja-se saber o valor.
	 * @return Valor correspondente ao atributo.
	 * @throws Exception {@link SomInvalidoException, AtributoInvalidoException, AtributoInexistenteException}
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
	 * Simula a acao de um usuario seguir outro usuario.
	 * 
	 * @param seguidor
	 * 				Usuario ativo da acao.
	 * @param seguido
	 * 				Usuario passivo da acao.
	 * @throws Exception {@link LoginInvalidoException, LoginInexistenteException}
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
	 * Retorna a lista de seguidores.
	 * 
	 * @param idsessao
	 * 				Id da sessao.
	 * @return Lista de Usuarios correspondente a lista de seguidores.
	 * @throws Exception 
	 * 				{@link SessaoInvalidaException, SessaoInexistenteException}
	 */
	public List<Usuario> getListaDeSeguidores(String idsessao) throws Exception {
		verificaSessao(idsessao);
		return getUsuario(idsessao).getListaDeSeguidores();
	}

	/**
	 * Metodo que retorna o numero de seguidores.
	 * 
	 * @param idsessao
	 * 				Id da sessao.
	 * @return Tamanho da lista de seguidores.
     * @throws Exception 
	 *				{@link SessaoInvalidaException, SessaoInexistenteException}
	 */
	public int getNumeroDeSeguidores(String idsessao) throws Exception {
		verificaSessao(idsessao);
		Usuario user = getUsuario(idsessao);
		return user.getNumeroDeSeguidores();
	}

	/**
	 * Retorna as fontes de som.
	 * @param idsessao
	 * 			Id da sessao.
	 * @return Lista de Usuarios correspondente as fontes de som.
	 * @throws Exception
	 * 			{@link SessaoInvalidaException, SessaoInexistenteException}
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
	 * @param idsessao
	 * 				Id da sessao.
	 * @return O Id do usuario correspondete.
	 * @throws Exception
	 * 			{@link SessaoInvalidaException, SessaoInexistenteException}
	 */
	public String getIDUsuario(String idsessao) throws Exception {
		verificaSessao(idsessao);
		String login = this.gerenciaSessao.getLogin(idsessao);
		return this.gerenciaUsuarios.getAtributoUsuario(login, "id");
	}

	/**
	 * Recupera a visao de sons.
	 * @param idsessao
	 * 				Id da sessao.
	 * @return Lista de Sons da visao de sons da sessao correspondente.
	 * @throws Exception
	 * 				{@link SessaoInvalidaException, SessaoInexistenteException}
	 */
	public List<Som> getVisaoDosSons(String idsessao) throws Exception {
		verificaSessao(idsessao);
		return getUsuario(idsessao).getVisaoDosSons();
	}

	/**
	 * Retorna os sons favoritos da sessao correspondente.
	 * 
	 * @param idsessao
	 * 				Id da sessao.
	 * @return Lista de Sons correspondente aos sons favoritos.
	 * @throws Exception
	 * 			    {@link SessaoInvalidaException, SessaoInexistenteException}
	 */
	public List<Som> getSonsFavoritos(String idsessao) throws Exception {
		verificaSessao(idsessao);
		return getUsuario(idsessao).getSonsFavoritos();
	}

	/**
	 * Retorna o feed extra da sessao correspondente.
	 * @param idsessao
	 * 				Id da sessao.
	 * @return Lista de Sons correspondente ao feed extra.
	 * @throws Exception
	 * 				   {@link SessaoInvalidaException, SessaoInexistenteException}
	 */
	public List<Som> getFeedExtra(String idsessao) throws Exception {
		verificaSessao(idsessao);
		return getUsuario(idsessao).getFeedExtra();
	}

	/**
	 * Simula a acao de um usuario favoritar um som.
	 * @param idsessao
	 * 				Id sessao do usuario autor da acao.
	 * @param idsom
	 * 				Id do som a ser favoritado.
	 * @throws Exception
	 * 				{@link SessaoInvalidaException, SessaoInexistenteException, SomInvalidoException,  SomInexistenteException}
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
	 * Retorna o feed principal da sessao correspondente.
	 * @param idsessao
	 * 				Id da sessao.
	 * @return Lista de som do feed principal.
	 * @throws Exception
	 * 				{@link SessaoInvalidaException, SessaoInexistenteException}
	 */
	public List<Som> getMainFeed(String idsessao) throws Exception {
		verificaSessao(idsessao);
		return feedPrincipal.ordena(getUsuario(idsessao));
	}

	/**
	 * Altera o tipo de ordenacao {@link TipoFeedPrincipal} do feed principal.
	 * @param idsessao
	 * 				Id da sessao.
	 * @param rule	
	 * 				A regra de ordenacao.
	 * @throws Exception
	 * 				{@link RegraInvalidaException, RegraInexistenteException, SessaoInvalidaException, SessaoInexistenteException}
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
	 * Encerra a sessao do login correspondente.
	 * @param login
	 * 			Login do usuario cuja sessao sera encerrada.
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

	/**
	 * Retorna o {@link Usuario} correspondente a sessao especificada.
	 * @param idsessao
	 * 				Id da sessao.
	 * @return O Usuario.
	 */
	public Usuario getUsuario(String idsessao) {
		String loginUser = this.gerenciaSessao.getLogin(idsessao);
		return this.gerenciaUsuarios.getUser(loginUser, "login");
	}
}