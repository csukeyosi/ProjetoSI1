package mainClasses;

import java.util.ArrayList;
import java.util.List;

import exception.SessaoInexistenteException;


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


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	/**
	 * Metodo que altera o identificador de um usuario ("ID" + loguin).
	 * 
	 * @param login
	 *            String login do Usuario.
	 */
	public void setId(String login) {
		this.id = "ID" + login.substring(0, 1).toUpperCase()
				+ login.substring(1);
	}

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
			throw new SessaoInexistenteException();
		}
	}

	/**
	 * Metodo que ordena a lista de seguidores em ordem alfabetica assim que um
	 * seguidor é adcionado ou removido.
	 */
	private void ordenaListaSeguidores() {
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
	
	/**
	 * Adiciona um son ao perfil musical do usuario.
	 * @param idSom
	 * 				Id do som a ser adicionado
	 */
	public void postarSom(String idSom) {
		this.perfilMusical.add(idSom);
	}

	public List<String> getPerfilMusical() {
		return this.perfilMusical;
	}

	public List<String> getSonsFavoritos() {
		return this.sonsFavoritos;
	}
	
	/**
	 * Adiciona um som aos sons favoritos do usuario.
	 * @param somFavorito
	 * 					Id do som a ser adicionado
	 */
	public void addSonsFavoritos(String somFavorito) {
		this.sonsFavoritos.add(somFavorito);
	}

	public List<String> getFeedExtra() {
		return this.feedExtra;
	}
	
	/**
	 * Adiciona um som ao feed extra do usuario.
	 * @param somID
	 * 				Id do som a ser adicionado
	 */
	public void addFeedExtra(String somID) {
		this.feedExtra.add(somID);
	}
}