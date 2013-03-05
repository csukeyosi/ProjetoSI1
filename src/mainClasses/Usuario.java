package mainClasses;

import java.util.ArrayList;
import java.util.List;


public class Usuario {

	private String id, login, senha, nome, email;
	private List<String> fontesDeSom, listaSeguidores, perfilMusical,
			sonsFavoritos, feedExtra, visaoDosSons;

	/**
	 * Construtor da Classe Usuario.
	 * 
	 * @param login
	 *            String login do Usuario
	 * @param senha
	 *            String senha do Usuario
	 * @param nome
	 *            String nome do Usuario
	 * @param email
	 *            String email do Usuario
	 */
	public Usuario(String login, String senha, String nome, String email) {
		setLogin(login);
		setSenha(senha);
		setNome(nome);
		setEmail(email);
		setId(login);
		this.fontesDeSom = new ArrayList<String>();
		this.listaSeguidores = new ArrayList<String>();
		this.visaoDosSons = new ArrayList<String>();
		this.perfilMusical = new ArrayList<String>();
		this.sonsFavoritos = new ArrayList<String>();
		this.feedExtra = new ArrayList<String>();
	}

	/**
	 * Metodo que retorna o login do Usuario.
	 * 
	 * @return String login.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Metodo que altera o login do Usuario.
	 * 
	 * @param login
	 *            String login
	 */
	private void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Metodo que retorna a senha do Usuario
	 * 
	 * @return String senha.
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Metodo que altera a senha do Usuario.
	 * 
	 * @param senha
	 *            String senha
	 */
	private void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * Metodo que retorna o nome do Usuario
	 * 
	 * @return String nome.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Metodo que altera o nome do Usuario.
	 * 
	 * @param nome
	 *            String nome.
	 */
	private void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Metodo que retorna o email do Usuario
	 * 
	 * @return String email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Metodo que altera o email do Usuario.
	 * 
	 * @param email
	 *            String e-mail.
	 */
	private void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Metodo que retorna o identificador do Usuario
	 * 
	 * @return String ID.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Metodo que altera o identificador de um usuario.
	 * 
	 * @param login
	 *            String login do Usuario.
	 */
	private void setId(String login) {
		this.id = "ID" + login.substring(0, 1).toUpperCase()
				+ login.substring(1);
	}

	/**
	 * Metodo que retorna a lista de fontes de Som do Usuario que trata-se dos
	 * id's do usuario que o usuario segue.
	 * 
	 * @return List<String> fontesDeSom.
	 */
	public List<String> getFontesDeSom() {
		return this.fontesDeSom;
	}

	/**
	 * Metodo que adciona um id de usuario que é fonte de sons.
	 * 
	 * @param String
	 *            idFontesDeSom
	 */
	public void addFontesDeSom(String idFontesDeSom) {
		this.fontesDeSom.add(idFontesDeSom);
	}

	/**
	 * Metodo que retorna a lista de visaoDeSons do Usuario
	 * 
	 * @return List<String> visaoDosSons.
	 */
	public List<String> getVisaoDosSons() {
		return this.visaoDosSons;
	}

	/**
	 * Metodo que adciona um som na visao de sons do Usuario
	 * 
	 */
	public void addEmVisaoDosSons(String idSom) {
		this.visaoDosSons.add(idSom);
	}

	/**
	 * Metodo que retorna o numero de seguidores que o usuario possui.
	 * 
	 * @return int tamanho da lista de seguidores.
	 */
	public int getNumeroDeSeguidores() {
		return this.listaSeguidores.size();
	}

	/**
	 * Metodo que retorna a lista de seguidores que o usuario possui.
	 * 
	 * @return List<String> lista dos seguidores.
	 */
	public List<String> getListaDeSeguidores() {
		return this.listaSeguidores;
	}

	/**
	 * Metodo que adciona um id de um Usuario seguidor na lista de seguidores do
	 * Usuario
	 * 
	 * @param IdUser
	 *            String Id do Usuario.
	 */
	public void addListaDeSeguidores(String IdUser) throws Exception {
		if (!this.listaSeguidores.contains(IdUser)) {
			this.listaSeguidores.add(IdUser);
			ordenaListaSeguidores();
		} else {
			throw new Exception("Sessão inexistente");
		}
	}

	/**
	 * Metodo que ordena a lista de seguidores em ordem alfabetica assim que um
	 * seguidor é adcionado ou removido.
	 */
	public void ordenaListaSeguidores() {
		boolean houveTroca = true;
		List<String> list = this.listaSeguidores;
		int sizeList = list.size();
		while (houveTroca) {
			houveTroca = false;
			for (int i = 0; i < (sizeList - 1); i++) {
				String nomeSemId1 = list.get(i).substring(2);
				String nomeSemId2 = list.get(i + 1).substring(2);
				if (nomeSemId1.compareTo(nomeSemId2) > 0) {
					String variavelAuxiliar = list.get(i + 1);
					list.set(i + 1, list.get(i));
					list.set(i, variavelAuxiliar);
					houveTroca = true;
				}
			}
		}
	}

	public void postarSom(String idSom) {
		this.perfilMusical.add(idSom);
	}

	public List<String> getPerfilMusical() {
		return this.perfilMusical;
	}

	public List<String> getSonsFavoritos() {
		return sonsFavoritos;
	}

	public void addSonsFavoritos(String somFavorito) {
		this.sonsFavoritos.add(somFavorito);
	}

	public List<String> getFeedExtra() {
		return this.feedExtra;
	}

	public void addFeedExtra(String somID) {
		this.feedExtra.add(somID);
	}
}