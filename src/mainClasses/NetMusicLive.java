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
import exception.EmailDuplicadoException;
import exception.EmailInvalidoException;
import exception.LoginDuplicadoException;
import exception.LoginInexistenteException;
import exception.LoginInvalidoException;
import exception.NomeInvalidoException;
import exception.RegraInexistenteException;
import exception.RegraInvalidaException;
import exception.SomInexistenteException;
import exception.SomInvalidoException;
import exception.UsuarioInexistenteException;

/**
 * Classe que representa as principais acoes do Sistema.
 * 
 */
public class NetMusicLive implements Serializable {

	private static final long serialVersionUID = 1L;
	private GerenciaSessao gerenciaSessao;
	private Gerenciador gerenciador;
	private OrdenaFeedPrincipal feedPrincipal;
	private static NetMusicLive netMusicLive;

	private NetMusicLive() {
		zerarSistema();
	}

	/**
	 * Recupera a instancia de {@link NetMusicLive}. Caso seja null, uma
	 * intancia e criada.
	 * 
	 * @return Instancia de Sistema.
	 */
	public static NetMusicLive getInstance() {
		if (netMusicLive == null) {
			netMusicLive = new NetMusicLive();
		}
		return netMusicLive;
	}

	/**
	 * Todos os dados do sistema é zerado. Utilizado também para instaciar as
	 * principais classes de gerencia.
	 */
	public void zerarSistema() {
		gerenciaSessao = new GerenciaSessao();
		gerenciador = new Gerenciador();
		feedPrincipal = new SonsMaisRecentes();
	}

	/**
	 * Cria um novo usuario.
	 * 
	 * @param login
	 *            Login do usuario.
	 * @param senha
	 *            Senha do usuario.
	 * @param nome
	 *            Nome do usuario.
	 * @param email
	 *            Email do usuario.
	 * @throws Exception
	 *             Login, nome ou email inválidos (ou seja null ou ""). Login ou
	 *             email ja existente.
	 */
	public void criarUsuario(String login, String senha, String nome,
			String email) throws Exception {
		if (!Utilitario.elementIsValid(login)) {
			throw new LoginInvalidoException();

		} else if (!Utilitario.elementIsValid(nome)) {
			throw new NomeInvalidoException();

		} else if (!Utilitario.elementIsValid(email)) {
			throw new EmailInvalidoException();

		} else {
			if (gerenciador.existeLoginUsuario(login)) {
				throw new LoginDuplicadoException();
			}
			if (gerenciador.existeEmailUsuario(email)) {
				throw new EmailDuplicadoException();
			}
			gerenciador.criarUsuario(login, senha, nome, email);
		}
	}

	/**
	 * Verifica se o login e senha correspondente existem no sistema.
	 * 
	 * @param login
	 *            Login a ser pesquisado.
	 * @param senha
	 *            Senha a ser pesquisada.
	 * @return true, caso o existam.
	 */
	public boolean verificaLoginESenha(String login, String senha) {
		return gerenciador.verificaLoginESenha(login, senha);
	}

	/**
	 * Uma nova sessao e criada.
	 * 
	 * @param login
	 *            Login do usuario dono da sessao.
	 * @param senha
	 *            Senha do usuario dono da sessao.
	 * @return O id da sessao.
	 * @throws Exception
	 *             Login invalido (ou seja, null ou ""). Login e senha
	 *             incompativeis. Login de usuario inexistente.
	 */
	public String abrirSessao(String login, String senha) throws Exception {
		if (!Utilitario.elementIsValid(login)) {
			throw new LoginInvalidoException();

		} else if (gerenciador.existeLoginUsuario(login)) {

			if (!verificaLoginESenha(login, senha)) {
				throw new LoginInvalidoException();
			}
			return gerenciaSessao.abrirSessao(login, senha);

		} else {
			throw new UsuarioInexistenteException();
		}
	}

	/**
	 * Verifica se a sessao em questao existe.
	 * 
	 * @param login
	 *            Login do usuario dono da sessao.
	 * @return true, no caso de exitir.
	 */
	public boolean existeSessao(String login) {
		String idsessao = "sessao" + login;
		return gerenciaSessao.existeSessao(idsessao);
	}

	/**
	 * Retorna o atributo desejado de um usuario.
	 * 
	 * @param login
	 *            Login do usuario em que se deseja saber um atributo.
	 * @param atributo
	 *            Atributo desejado, podendo ser: login, nome, email ou id.
	 * @return O valor do atributo.
	 * @throws Exception
	 *             Atributo ou login invalidos (ou seja, null ou ""). Login ou
	 *             atributo inexistentes.
	 */
	public String getAtributoUsuario(String login, String atributo)
			throws Exception {
		if (!Utilitario.elementIsValid(atributo)) {
			throw new AtributoInvalidoException();

		} else if (!Utilitario.elementIsValid(login)) {
			throw new LoginInvalidoException();

		} else if (!this.gerenciador.existeLoginUsuario(login)) {
			throw new UsuarioInexistenteException();

		} else {
			String result = this.gerenciador
					.getAtributoUsuario(login, atributo);
			if (!result.isEmpty()) {
				return result;
			}
		}
		throw new AtributoInexistenteException();
	}

	/**
	 * Retorna o perfil musical do usuario correspondente ao idsessao.
	 * 
	 * @param idsessao
	 *            ID da sessao.
	 * @return Lista dos sons postados pelo usuario.
	 * @throws Exception
	 *             Sessao invalida ou inexistente.
	 * 
	 */
	public List<Som> getPerfilMusical(String idsessao) throws Exception {
		return gerenciador.getPerfilMusical(gerenciaSessao.getLogin(idsessao));
	}

	/**
	 * Acao do usuario de postar um novo som.
	 * 
	 * @param idSessao
	 *            ID da sessao do usuario.
	 * @param link
	 *            Link do som.
	 * @param dataCriacao
	 *            Data de criacao do som.
	 * @return ID do som postado.
	 * @throws Exception
	 *             Link invalido (ou seja, null ou ""). Data invalida.
	 */
	public String postarSom(String idSessao, String link, String dataCriacao)
			throws Exception {
		if (!Utilitario.elementIsValid(link)) {
			throw new SomInvalidoException();

		} else if (!Utilitario.dataIsValida(dataCriacao)) {
			throw new DataInvalidaException();
		}

		return gerenciador.postarSom(gerenciaSessao.getLogin(idSessao), link,
				dataCriacao);
	}

	/**
	 * Retorna o atributo desejado de um som.
	 * 
	 * @param idSom
	 *            ID do som em questao.
	 * @param atributo
	 *            Atributo desejado.
	 * @return O valor do atributo.
	 * @throws Exception
	 *             ID do som ou atributo invalidos (ou seja, null ou "").
	 *             Atributo inexistente.
	 */
	public String getAtributoSom(String idSom, String atributo)
			throws Exception {
		if (!Utilitario.elementIsValid(idSom)) {
			throw new SomInvalidoException();
		} else if (!Utilitario.elementIsValid(atributo)) {
			throw new AtributoInvalidoException();
		}

		String valorAtributo = gerenciador.getAtributoSom(idSom, atributo);
		if (!valorAtributo.isEmpty()) {
			return valorAtributo;
		}
		throw new AtributoInexistenteException();
	}

	/**
	 * Acao de um usuario seguir outro.
	 * 
	 * @param idSessaoSeguidor
	 *            ID do usuario seguidor.
	 * @param loginSeguido
	 *            Login do usuario a ser seguido.
	 * @throws Exception
	 *             Sessao invalida ou inexistente. Login do seguido invalido ou
	 *             inexistente. Seguidor e seguido serem o mesmo usuario.
	 */
	public void seguirUsuario(String idSessaoSeguidor, String loginSeguido)
			throws Exception {
		String loginSeguidor = gerenciaSessao.getLogin(idSessaoSeguidor);

		if (!Utilitario.elementIsValid(loginSeguido)) {
			throw new LoginInvalidoException();

		} else if (loginSeguidor.equals(loginSeguido)) {
			throw new LoginInvalidoException();
		}

		if (gerenciador.existeLoginUsuario(loginSeguido)) {
			gerenciador.seguirUsuario(loginSeguidor, loginSeguido);

		} else {
			throw new LoginInexistenteException();
		}
	}

	/**
	 * Retorna a lista de seguidores de um usuario.
	 * 
	 * @param idSessao
	 *            ID da sessao correspondente ao usuario em questao.
	 * @return Lista de usuarios seguidores.
	 * @throws Exception
	 *             Sessao invalida ou inexistente.
	 */
	public List<Usuario> getListaDeSeguidores(String idSessao) throws Exception {
		return gerenciador.getListaSeguidoresUsuario(gerenciaSessao
				.getLogin(idSessao));
	}

	/**
	 * Retorna o numero de seguidores de um usuario.
	 * 
	 * @param idSessao
	 *            ID da sessao correspondente ao usuario em questao.
	 * @return O numero de seguidores.
	 * @throws Exception
	 *             Sessao invalida ou inexistente.
	 */
	public int getNumeroDeSeguidores(String idSessao) throws Exception {
		return getListaDeSeguidores(idSessao).size();
	}

	/**
	 * Retorna as fontes de um usuario.
	 * 
	 * @param idSessao
	 *            ID da sessao correspondente ao usuario em questao.
	 * @return Lista das fontes.
	 * @throws Exception
	 *             Sessao invalida ou inexistente.
	 * 
	 */
	public List<Usuario> getFontesDeSons(String idSessao) throws Exception {
		return gerenciador.getFontesSomUsuario(gerenciaSessao
				.getLogin(idSessao));
	}

	/**
	 * Retorna o ID do usuario.
	 * 
	 * @param idsessao
	 *            ID da sessao correspondente ao usuario em questao.
	 * @return ID do usuario.
	 * @throws Exception
	 *             Sessao invalida ou inexistente.
	 */
	public String getIDUsuario(String idsessao) throws Exception {
		return gerenciador.getAtributoUsuario(
				gerenciaSessao.getLogin(idsessao), "id");
	}

	/**
	 * Retorna a visao dos sons do usuario.
	 * 
	 * @param idSessao
	 *            ID da sessao correspondente ao usuario em questao.
	 * @return Lista das visoes.
	 * @throws Exception
	 *             Sessao invalida ou inexistente.
	 */
	public List<Som> getVisaoDosSons(String idSessao) throws Exception {
		return gerenciador.getVisaoSonsUsuario(gerenciaSessao
				.getLogin(idSessao));
	}

	/**
	 * Retorna os sons favoritos do usuario.
	 * 
	 * @param idSessao
	 *            ID da sessao correspondente ao usuario em questao.
	 * @return Lista com sons favoritos.
	 * @throws Exception
	 *             Sessao invalida ou inexistente.
	 */
	public List<Som> getSonsFavoritos(String idSessao) throws Exception {
		return gerenciador.getSonsFavoritosUsuario(gerenciaSessao
				.getLogin(idSessao));
	}

	/**
	 * Retorna o feed extra do usuario.
	 * 
	 * @param idSessao
	 *            ID da sessao correspondente ao usuario em questao.
	 * @return Feed extra.
	 * @throws Exception
	 *             Sessao invalida ou inexistente.
	 */
	public List<Som> getFeedExtra(String idSessao) throws Exception {
		return gerenciador.getFeedExtraUsuario(gerenciaSessao
				.getLogin(idSessao));
	}

	/**
	 * Retorna o feed principal do usuario.
	 * 
	 * @param idSessao
	 *            ID da sessao correspondente ao usuario em questao.
	 * @return Lista das visoes.
	 * @throws Exception
	 *             Sessao invalida ou inexistente.
	 */
	public List<Som> getMainFeed(String idSessao) throws Exception {
		return feedPrincipal.ordena(getFontesDeSons(idSessao),
				getSonsFavoritos(idSessao));
	}

	/**
	 * Acao do usuario de favoritar um som.
	 * 
	 * @param idSessao
	 *            ID da sessao correspondente ao usuario em questao.
	 * @param idSom
	 *            ID do som a ser favoritado.
	 * @throws Exception
	 *             ID do som invalido ou inexistente. Sessao inexitente ou
	 *             invalida.
	 */
	public void favoritarSom(String idSessao, String idSom) throws Exception {
		if (!Utilitario.elementIsValid(idSom)) {
			throw new SomInvalidoException();
		}

		if (!gerenciador.favoritarSom(gerenciaSessao.getLogin(idSessao), idSom)) {
			throw new SomInexistenteException();
		}
	}

	/**
	 * Altera o tipo da ordenacao do feed principal do usuairo.
	 * 
	 * @param idsessao
	 *            ID da sessao correspondente ao usuario em questao.
	 * @param rule
	 *            Tipo da ordenacao.
	 * @throws Exception
	 *             Sessao invalida ou inexistente. Regra invalida ou
	 *             inexistente.
	 */
	public void setMainFeedRule(String idsessao, String rule) throws Exception {
		gerenciaSessao.verificaSessao(idsessao);
		if (!Utilitario.elementIsValid(rule)) {
			throw new RegraInvalidaException();
		}

		if (rule.equals(TipoFeedPrincipal.SONS_RECENTES.toString())) {
			feedPrincipal = new SonsMaisRecentes();

		} else if (rule.equals(TipoFeedPrincipal.SONS_FAVORITADOS_SISTEMA
				.toString())) {
			feedPrincipal = new SonsMaisFavoritadosSistema();

		} else if (rule.equals(TipoFeedPrincipal.SONS_FAVORITADOS_USUARIO
				.toString())) {
			feedPrincipal = new SonsMaisFavoritadosUsuario();

		} else {
			throw new RegraInexistenteException();
		}
	}

	/**
	 * Encerra uma sessao.
	 * 
	 * @param login
	 *            Login do usuario que terá a sessao encerrada.
	 */
	public void encerrarSessao(String login) {
		gerenciaSessao.encerrarSessao("sessao" + login);
	}

	/**
	 * Encerra o sistema.
	 */
	public void encerrarSistema() {

	}

	/**
	 * Acao do usuario criar uma lista personalizada de usuarios.
	 * 
	 * @param idSessao
	 *            ID da sessao correspondente ao usuario em questao.
	 * @param nomeLista
	 *            Nome da lista a ser criada.
	 * @return ID da lista criada.
	 * @throws Exception
	 *             Sessao invalida ou inexistente. Nome invalido ou já
	 *             existente.
	 */
	public String criarLista(String idSessao, String nomeLista)
			throws Exception {
		String login = gerenciaSessao.getLogin(idSessao);
		if (!Utilitario.elementIsValid(nomeLista)) {
			throw new Exception("Nome inválido");
		}
		String idLista = gerenciador.criarLista(login, nomeLista);

		if (!Utilitario.elementIsValid(idLista)) {
			throw new Exception("Nome já escolhido");
		}
		return idLista;
	}

	/**
	 * Adiciona um usuario a uma lista personalizada.
	 * 
	 * @param idSessao
	 *            ID da sessao correspondente ao usuario dono da lista
	 *            personalizada.
	 * @param idLista
	 *            ID da lista.
	 * @param idUsuario
	 *            ID do usuario que será adicionado.
	 * @throws Exception
	 *             Sessao inexistente ou invalida. Usuario invalido ou ja
	 *             existente. Lista invalida. Usuario não pode adicionar-se a
	 *             propria lista.
	 */
	public void adicionarUsuario(String idSessao, String idLista,
			String idUsuario) throws Exception {
		gerenciador.adicionaUsuario(gerenciaSessao.getLogin(idSessao), idLista,
				idUsuario);
	}

	/**
	 * Retorna os sons de uma lista personalizada.
	 * 
	 * @param idSessao
	 *            ID da sessao correspondente ao usuario dono da lista
	 * @param idLista
	 *            ID da lista.
	 * @return Lista contendo os sons.
	 * @throws Exception
	 *             Sessao invalida ou inexistente. Lista invalida.
	 */
	public List<Som> getSonsEmLista(String idSessao, String idLista)
			throws Exception {
		List<Som> sons = gerenciador.getSonsEmLista(
				gerenciaSessao.getLogin(idSessao), idLista);
		if (sons == null) {
			throw new Exception("Lista inválida");
		}
		return sons;
	}
}