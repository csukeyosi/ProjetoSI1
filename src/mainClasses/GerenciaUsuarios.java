package mainClasses;

import java.util.ArrayList;
import java.util.List;

import exception.EmailDuplicadoException;
import exception.EmailInvalidoException;
import exception.LoginDuplicadoException;
import exception.LoginInvalidoException;
import exception.NomeInvalidoException;

import util.Utilitario;

public class GerenciaUsuarios {

	private List<Usuario> usuarios;

	/**
	 * Contrutor da Classe. 
	 */
	public GerenciaUsuarios() {
		this.usuarios = new ArrayList<Usuario>();
	}

	/**
	 * Metodo que executa a criação de um novo usuario no sistema. São geradas
	 * excessões caso algum dos parametros seja inválido ou o Usuario já exista
	 * no sistema.
	 * 
	 * @param login
	 * @param senha
	 * @param nome
	 * @param email
	 * @throws Exception
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

	public boolean VerificaAtributoExiste(String param,String tipo) {
		int sizeList = this.usuarios.size();
		for (int i = 0; i <sizeList ; i++) {
			if(tipo.equals("email")){
				if (this.usuarios.get(i).getEmail().equals(param)) {
					return true;
				}
			} else if(tipo.equals("login")){
				if (this.usuarios.get(i).getLogin().equals(param)) {
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
	 * @param senha
	 * @return true caso login e senha correspodente estejam corretos, false caso contrário.
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

	/** Metodo que recebe o login de um determinado Usuario e retorna um tipo de atributo especificado
	 * no segundo parametro.
	 * 
	 * @param login
	 * @param atributo
	 * @return
	 * @throws Exception
	 */
	public String getAtributoUsuario(String login, String atributo) throws Exception{
		
		
		
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

	/** Metodo que retorna um objeto do tipo Usuario a partir de seu login ou id
	 * 
	 * @param atributo
	 * @param tipoDeAtributo String login ou id
	 * @return Usuario
	 */
	public Usuario getUser(String atributo, String tipoDeAtributo) {
		int sizeListUsers = this.usuarios.size();
		for (int i = 0; i < sizeListUsers; i++) {
			if (tipoDeAtributo.equals("login")) {
				if (usuarios.get(i).getLogin().equals(atributo)) {
					return usuarios.get(i);
				}
			} else if (tipoDeAtributo.equals("id")) {
				if (usuarios.get(i).getId().equals(atributo)) {
					return usuarios.get(i);
				}
			}
		}
		return null;
	}
}