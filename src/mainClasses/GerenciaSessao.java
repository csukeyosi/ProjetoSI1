package mainClasses;

import java.util.HashMap;
import java.util.Map;

public class GerenciaSessao {
	private Map<String, String> sessoes; // Mapa com sessaoID e login.

	/**
	 * Construtor da classe GerenciaSessao.
	 */
	public GerenciaSessao() {
		this.sessoes = new HashMap<String, String>();
	}

	/**
	 * Metodo que exclui uma sessao do conjunto de sessoes presentes no sistema
	 * 
	 * @param login
	 */
	public void encerrarSessao(String login) {
		this.sessoes.remove(login);
	}

	/**
	 * Metodo que da inicio a uma sessao.
	 * 
	 * @param login
	 *            login do usuario
	 * @param senha
	 *            senha do usuario
	 * @return String com id da sessao.
	 */
	public String abrirSessao(String login, String senha) {  // falta conferir se login e senha batem.
		String id = "sessao" + login;
		if(!existeSessao(id)){
			this.sessoes.put(id, login);
			return id;
		}return null;
	}

	/**
	 * Metodo que retorno o login do usuario de uma determinada sessao a partir
	 * do id da sessao.
	 * 
	 * @param idsessao
	 * @return String String com o login do usuario.
	 */
	public String getLogin(String idsessao) {
		return this.sessoes.get(idsessao);
	}

	/** Metodo que verifica se uma sessao existe no sistema a partir de um id de sessao.
	 * 
	 * @param idsessao
	 * @return
	 */
	public boolean existeSessao(String idsessao) {
		return this.sessoes.containsKey(idsessao);
	}
}