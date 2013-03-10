package mainClasses;

import java.io.Serializable;
import java.util.List;

import mainClasses.Sistema;
import mainClasses.Som;
import mainClasses.Usuario;

import tiposordenacao.TipoFeedPrincipal;

/**
 * Classe responsavel pela representacao do sistema e pela chamada das principais acoes.
 *
 */
public class NetMusicLive implements Serializable {

	private static final long serialVersionUID = 1L;
	private Sistema sistema;
	private static NetMusicLive netMusicLive;


	private NetMusicLive() {
		sistema = new Sistema();
	}

	/**
	 * Recupera a instancia de {@link NetMusicLive}. Caso seja null, uma intancia e criada.

	 * @return Instancia de Sistema.
	 */
	public static NetMusicLive getInstance() {
		if (netMusicLive == null) {
			netMusicLive = new NetMusicLive();
		}
		return netMusicLive;
	}


	/**
	 * Metodo que inicializa e limpa o sistema.
	 */
	public void zerarSistema() {
		sistema.zerarSistema();
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
		this.sistema.criarUsuario(login, senha, nome, email);
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
		return sistema.abrirSessao(login, senha);
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
		return sistema.getAtributoUsuario(login, atributo);
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
	public String postarSom(String sessao, String link, String dataCriacao) throws Exception{
		return sistema.postarSom(sessao, link, dataCriacao);
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
	public String getAtributoSom(String idSom, String atributo) throws Exception {
		return sistema.getAtributoSom(idSom, atributo);
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
		sistema.seguirUsuario(idSessaoSeguidor, loginSeguido);
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
		return sistema.getListaDeSeguidores(idsessao);
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
		return sistema.getNumeroDeSeguidores(idsessao);
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
		return sistema.getFontesDeSons(idsessao);
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
		return sistema.getIDUsuario(idsessao);
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
		return sistema.getVisaoDosSons(idsessao);
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
		return sistema.getSonsFavoritos(idsessao);
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
		return sistema.getFeedExtra(idsessao);
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
		sistema.favoritarSom(idsessao, idsom);
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
		return sistema.getMainFeed(idsessao);
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
		sistema.setMainFeedRule(idsessao, rule);
	}

	/**
	 * Encerra a sessao do login correspondente.
	 * @param login
	 * 			Login do usuario cuja sessao sera encerrada.
	 */
	public void encerrarSessao(String login) {
		sistema.encerrarSessao(login);
	}

	/**
	 * Metodo que ao ser chamado finaliza o sistema.
	 */
	public void encerrarSistema() {

	}


	/**
	 * Retorna o {@link Usuario} correspondente a sessao especificada.
	 * @param idsessao
	 * 				Id da sessao.
	 * @return O Usuario.
	 */
	public Usuario getUsuario(String idsessao) {
		return sistema.getUsuario(idsessao);
	}
	
	/**
	 * Verifica se existe sessao vinculada ao login passado como parametro.
	 * @param login
	 * 			Login do usuario.
	 * @return true se existir, false caso contraio.
	 */
	public boolean existeSessao(String login) {
		return sistema.existeSessao(login);
	}
	
	/** Metodo que verifica se o login e senha passados correspodem ao login e senha de algum 
	 * Usuario do sistema.
	 * 
	 * @param login
	 * 			Login a ser pesquisado.
	 * @param senha
	 * 			Senha a ser pesquisada.
	 * @return true caso login e senha correspodente existam no sistema, false caso contrario.
	 */
	public boolean verificaLoginESenha(String login, String senha) {
		return sistema.verificaLoginESenha(login, senha);
	}

}
