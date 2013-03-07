package mainClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import util.Utilitario;

public class Sistema implements Serializable {

	private static final long serialVersionUID = 1L;
	private GerenciaSons gerenciaSons;
	private GerenciaSessao gerenciaSessao;
	private GerenciaUsuarios gerenciaUsuarios;
	private static Sistema sistema;

	/**
	 * Construtor.
	 */
	private Sistema() {
		zerarSistema();
	}

	// Padrao Singleton.
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
	}

	/**
	 * Metodo que repassa a função de criar um novo usuario no sistema.
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

	public String abrirSessao(String login, String senha) throws Exception {
		if (!Utilitario.elementIsValid(login)) {
			throw new Exception("Login inválido");
		} else if (this.gerenciaUsuarios.VerificaAtributoExiste(login, "login")) {
			if (!this.gerenciaUsuarios.verificaLoginESenha(login, senha)) {
				throw new Exception("Login inválido");
			}
			return this.gerenciaSessao.abrirSessao(login, senha);
		} else {
			throw new Exception("Usuário inexistente");
		}
	}

	public String getAtributoUsuario(String login, String atributo)
			throws Exception {
		if (!Utilitario.elementIsValid(atributo)) {
			throw new Exception("Atributo inválido");
		} else if (!Utilitario.elementIsValid(login)) {
			throw new Exception("Login inválido");
		} else if (!this.gerenciaUsuarios
				.VerificaAtributoExiste(login, "login")) {
			throw new Exception("Usuário inexistente");
		} else {
			String result = this.gerenciaUsuarios.getAtributoUsuario(login,
					atributo);
			if (!result.isEmpty()) {
				return result;
			}
		}
		throw new Exception("Atributo inexistente");
	}

	public List<String> getPerfilMusical(String idsessao) {
		Usuario user = retornaUserPeloIdsessao(idsessao);
		return this.gerenciaSons.getPerfilMusical(user);
	}

	public String postarSom(String sessao, String link, String dataCriacao)
			throws Exception {
		if (!Utilitario.elementIsValid(link)) {
			throw new Exception("Som inválido");
		} else if (!dataIsValida(dataCriacao)) {
			throw new Exception("Data de Criação inválida");
		}
		String login = this.gerenciaSessao.getLogin(sessao);
		Usuario user = this.gerenciaUsuarios.getUser(login, "login");
		return this.gerenciaSons.postarSom(user, link, dataCriacao);
	}

	public String getAtributoSom(String idSom, String atributo)
			throws Exception {
		if (!Utilitario.elementIsValid(idSom)) {
			throw new Exception("Som inválido");
		} else if (!Utilitario.elementIsValid(atributo)) {
			throw new Exception("Atributo inválido");
		}
		String result = this.gerenciaSons.getAtributoSom(idSom, atributo);
		if (!result.isEmpty()) {
			return result;
		}
		throw new Exception("Atributo inexistente");
	}

	public boolean dataIsValida(String dataParam) {
		try {
			String[] datas = dataParam.split("/");
			Integer dia = Integer.parseInt(datas[0]);
			Integer mes = Integer.parseInt(datas[1]);
			Integer ano = Integer.parseInt(datas[2]);
			if (dia < 1 || dia > 31) {
				return false;
			} else if (ano < 2013) {
				return false;
			} else if (mes < 1 || mes > 12) {
				return false;
			} else {
				if (mes == 2) {
					if (dia > 29) {
						return false;
					}
					// se o ano for bisexto e dia for igual a 29, entao false.
					else if (!((ano % 4 == 0) && ((ano % 100 != 0) || (ano % 400 == 0)))
							&& dia == 29) {
						return false;
					}
				} else {
					List<Integer> meses30dias = new ArrayList<Integer>();
					meses30dias.add(4);
					meses30dias.add(6);
					meses30dias.add(9);
					meses30dias.add(11);
					if (meses30dias.contains(mes) && dia >= 31) {
						return false;
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void seguirUsuario(String idsessao, String login) throws Exception {
		verificaSessao(idsessao);
		if (!Utilitario.elementIsValid(login)) {
			throw new Exception("Login inválido");
		} else if (this.gerenciaSessao.getLogin(idsessao).equals(login)) {
			throw new Exception("Login inválido");
		}

		// Identificando Usuario que sera seguido:
		if (this.gerenciaUsuarios.VerificaAtributoExiste(login, "login")) {
			Usuario userSeguido = this.gerenciaUsuarios.getUser(login, "login");
			// Identificando Usuario que decidiu ser seguidor:
			String loginSeguidor = this.gerenciaSessao.getLogin(idsessao);
			Usuario userSeguidor = this.gerenciaUsuarios.getUser(loginSeguidor,
					"login");
			// Passando para a classe que gerencia o sons a responsabilidade de
			// adcionar nas fontes de som, e na lista de seguidores.
			this.gerenciaSons.seguirUsuario(userSeguidor, userSeguido);
		} else {
			throw new Exception("Login inexistente");
		}
	}

	public List<String> getListaDeSeguidores(String idsessao) throws Exception {
		verificaSessao(idsessao);
		Usuario user = retornaUserPeloIdsessao(idsessao);
		return user.getListaDeSeguidores();
	}

	public int getNumeroDeSeguidores(String idsessao) throws Exception {
		verificaSessao(idsessao);
		Usuario user = retornaUserPeloIdsessao(idsessao);
		return user.getNumeroDeSeguidores();
	}

	public List<String> getFontesDeSons(String idsessao) throws Exception {
		verificaSessao(idsessao);
		Usuario user = retornaUserPeloIdsessao(idsessao);
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

	public List<String> getVisaoDosSons(String idsessao) throws Exception {
		verificaSessao(idsessao);
		Usuario user = retornaUserPeloIdsessao(idsessao);
		return this.gerenciaSons.getVisaoDosSons(user);
	}

	public List<String> getSonsFavoritos(String idsessao) throws Exception {
		verificaSessao(idsessao);
		Usuario user = retornaUserPeloIdsessao(idsessao);
		return this.gerenciaSons.getSonsFavoritos(user);
	}

	public List<String> getFeedExtra(String idsessao) throws Exception {
		verificaSessao(idsessao);
		Usuario user = retornaUserPeloIdsessao(idsessao);
		return this.gerenciaSons.getFeedExtra(user);
	}

	public void favoritarSom(String idsessao, String idsom) throws Exception {
		verificaSessao(idsessao);
		this.gerenciaSons.verificaIdSom(idsom);
		Usuario user = retornaUserPeloIdsessao(idsessao);
		user.addSonsFavoritos(idsom);
		addInFeedExtra(user, idsom);
	}

	private void addInFeedExtra(Usuario user, String idsom) {
		List<String> listIdSeguidores = user.getListaDeSeguidores();
		List<Usuario> usuariosSeguidores = retornaUsersPelosIDs(listIdSeguidores);
		this.gerenciaSons.addInFeedExtra(usuariosSeguidores, idsom);
	}

	// Metodos da implementação da US05
	public List<String> getMainFeed(String idsessao) throws Exception {
		verificaSessao(idsessao);
		Usuario user = retornaUserPeloIdsessao(idsessao);
		List<String> fontesDeSom = user.getFontesDeSom();
		List<Usuario> usuarios = retornaUsersPelosIDs(fontesDeSom);
		
		List<String> feedPrincipal = new ArrayList<String>();
		for (int i = 0; i < usuarios.size(); i++) {
			List<String> perfilMusical = usuarios.get(i).getPerfilMusical();
			for (int j = 0; j < perfilMusical.size(); j++) {
				feedPrincipal.add(perfilMusical.get(j));
			}
		}

		return feedPrincipal;
	}

	public void setMainFeedRule(String idsessao, String rule) throws Exception {
		verificaSessao(idsessao);
		if (!Utilitario.elementIsValid(rule)) {
			throw new Exception("Regra de composição inválida");
		}
	}

	public void encerrarSessao(String login) {
		this.gerenciaSessao.encerrarSessao("sessao" + login);
	}

	/**
	 * Metodo que ao ser chamado finaliza o sistema.
	 */
	public void encerrarSistema() {

	}

	private void verificaSessao(String idsessao) throws Exception {
		if (!Utilitario.elementIsValid(idsessao)) {
			throw new Exception("Sessão inválida");
		} else if (!this.gerenciaSessao.existeSessao(idsessao)) {
			throw new Exception("Sessão inexistente");
		}
	}

	public List<Usuario> retornaUsersPelosIDs(List<String> idUsuarios) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		int sizeList = idUsuarios.size();
		for(int i=0;i<sizeList;i++){
			Usuario userAuxiliar = this.gerenciaUsuarios.getUser(idUsuarios.get(i), "id");
			usuarios.add(userAuxiliar);
		}
		return usuarios;
	}
	
	private Usuario retornaUserPeloIdsessao(String idsessao) {
		String loginUser = this.gerenciaSessao.getLogin(idsessao);
		return this.gerenciaUsuarios.getUser(loginUser, "login");
	}
}