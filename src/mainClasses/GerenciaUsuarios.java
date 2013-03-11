package mainclasses;

import java.util.ArrayList;
import java.util.List;

import util.Utilitario;
import exception.EmailDuplicadoException;
import exception.EmailInvalidoException;
import exception.LoginDuplicadoException;
import exception.LoginInvalidoException;
import exception.NomeInvalidoException;

/**
 * Classe responsavel pelo gerenciamento dos usuarios {@link Usuario} do sistema. 
 *
 */
public class GerenciaUsuarios {
	/* Colecao dos usuarios do sistema */
	private List<Usuario> usuarios;

	public GerenciaUsuarios() {
		this.usuarios = new ArrayList<Usuario>();
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
		if (!Utilitario.elementIsValid(login)) {
			throw new LoginInvalidoException();
		} else if (!Utilitario.elementIsValid(nome)) {
			throw new NomeInvalidoException();
		} else if (!Utilitario.elementIsValid(email)) {
			throw new EmailInvalidoException();
		} else {
			if (VerificaAtributoExiste(login,"login")) {
				throw new LoginDuplicadoException();
			}
			if (VerificaAtributoExiste(email,"email")) {
				throw new EmailDuplicadoException();
			}
			Usuario user = new Usuario(login, senha, nome, email);
			this.usuarios.add(user);
		}
	}

	/**
	 * Verifica se o valor de um determinado atributo passados como parametro ja existe.
	 * @param valor
	 * 			Valor do atributo.
	 * @param atributo
	 * 			Tipo do atributo (e-mail ou login).
	 * @return	true caso o valor do atributo ja exista, false caso contrario.
	 */
	public boolean VerificaAtributoExiste(String valor, String atributo) {
		int sizeList = this.usuarios.size();
		for (int i = 0; i <sizeList ; i++) {
			if(atributo.equals("email")){
				if (this.usuarios.get(i).getEmail().equals(valor)) {
					return true;
				}
			} else if(atributo.equals("login")){
				if (this.usuarios.get(i).getLogin().equals(valor)) {
					return true;
				}
			}

		}
		return false;
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
		for (int i = 0; i < this.usuarios.size(); i++) {
			if (this.usuarios.get(i).getLogin().equals(login)) {
				if (this.usuarios.get(i).getSenha().equals(senha)) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	/** Metodo que recebe o login de um determinado Usuario e retorna o valor do atributo especificado
	 * no segundo parametro.
	 * 
	 * @param login
	 * 			Login do usuario a ser pesquisado.
	 * @param atributo
	 * 			Atributo a ser pesquisado (nome, email ou id).
	 * @return O valor do atributo.
	 */
	public String getAtributoUsuario(String login, String atributo){ 
		for (int i = 0; i < this.usuarios.size(); i++) {
			if (this.usuarios.get(i).getLogin().equals(login)) {
				if (atributo.equals("nome")) {
					return this.usuarios.get(i).getNome();
				} else if (atributo.equals("email")) {
					return this.usuarios.get(i).getEmail();
				} else if (atributo.equals("id")) {
					return this.usuarios.get(i).getId();
				}
			}
		}
		return "";
	}

	/** Metodo que retorna um objeto do tipo {@link Usuario} a partir de seu login ou id.
	 * 
	 * @param valor
	 * 				Valor do atributo.
	 * @param atributo 
	 * 				Define se a busca � pelo login ou id.
	 * @return O {@link Usuario} correspondente.
	 */
	public Usuario getUser(String valor, String atributo) {
		int sizeListUsers = this.usuarios.size();
		for (int i = 0; i < sizeListUsers; i++) {
			if (atributo.equals("login")) {
				if (usuarios.get(i).getLogin().equals(valor)) {
					return usuarios.get(i);
				}
			} else if (atributo.equals("id")) {
				if (usuarios.get(i).getId().equals(valor)) {
					return usuarios.get(i);
				}
			}
		}
		return null;
	}
}