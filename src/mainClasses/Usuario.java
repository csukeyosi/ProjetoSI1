package mainclasses;

import java.util.ArrayList;
import java.util.List;

import exception.SessaoInexistenteException;


public class Usuario {

	private String id, login, senha, nome, email;
	private List<Usuario> listaSeguidores, fontesDeSom;
	private List<Som> perfilMusical, sonsFavoritos, feedExtra, visaoDosSons;

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
		this.fontesDeSom = new ArrayList<Usuario>();
		this.listaSeguidores = new ArrayList<Usuario>();
		this.visaoDosSons = new ArrayList<Som>();
		this.perfilMusical = new ArrayList<Som>();
		this.sonsFavoritos = new ArrayList<Som>();
		this.feedExtra = new ArrayList<Som>();
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

	public List<Usuario> getFontesDeSom() {
		return this.fontesDeSom;
	}

	/**
	 * Metodo que adciona um id de usuario que é fonte de sons.
	 * 
	 * @param String
	 *            idFontesDeSom
	 */
	public void addFontesDeSom(Usuario usuario) {
		this.fontesDeSom.add(usuario);
	}

	public List<Som> getVisaoDosSons() {
		return this.visaoDosSons;
	}

	/**
	 * Metodo que adciona um som na visao de sons do Usuario
	 * 
	 */
	public void addVisaoDosSons(Som som) {
		this.visaoDosSons.add(som);
	}

	/**
	 * Metodo que retorna o numero de seguidores que o usuario possui.
	 * 
	 * @return int tamanho da lista de seguidores.
	 */
	public int getNumeroDeSeguidores() {
		return this.listaSeguidores.size();
	}

	public List<Usuario> getListaDeSeguidores() {
		return this.listaSeguidores;
	}

	/**
	 * Metodo que adciona um id de um Usuario seguidor na lista de seguidores do
	 * Usuario
	 * 
	 * @param IdUser
	 *            String Id do Usuario.
	 */
	public void addListaDeSeguidores(Usuario usuario) throws Exception {
		if (!this.listaSeguidores.contains(usuario)) {
			this.listaSeguidores.add(usuario);
			ordenaListaSeguidores();
		} else {
			throw new SessaoInexistenteException();
		}
	}

	/**
	 * Metodo que ordena a lista de seguidores em ordem alfabetica.
	 */
	private void ordenaListaSeguidores() {
		List<String> idsSeguidores = new ArrayList<String>();
		for (Usuario usuario : listaSeguidores){
			idsSeguidores.add(usuario.getId());
		}

		boolean houveTroca = true;
		while (houveTroca) {
			houveTroca = false;
			for (int i = 0; i < (idsSeguidores.size() - 1); i++) {
				String nomeSemId1 = idsSeguidores.get(i).substring(2);
				String nomeSemId2 = idsSeguidores.get(i + 1).substring(2);
				if (nomeSemId1.compareTo(nomeSemId2) > 0) {
					String aux1 = idsSeguidores.get(i + 1);
					idsSeguidores.set(i + 1, idsSeguidores.get(i));
					idsSeguidores.set(i, aux1);

					Usuario aux2 = listaSeguidores.get(i + 1);
					listaSeguidores.set(i+1, listaSeguidores.get(i));
					listaSeguidores.set(i, aux2);
					houveTroca = true;
				}
			}
		}
	}

	/**
	 * Adiciona um som ao perfil musical do usuario.
	 * @param idSom
	 * 				Id do som a ser adicionado
	 */
	public void postarSom(Som som) {
		this.perfilMusical.add(som);
	}

	public List<Som> getPerfilMusical() {
		return this.perfilMusical;
	}

	public List<Som> getSonsFavoritos() {
		return this.sonsFavoritos;
	}

	/**
	 * Adiciona um som aos sons favoritos do usuario.
	 * @param somFavorito
	 * 					Id do som a ser adicionado
	 */
	public void addSonsFavoritos(Som somFavorito) {
		this.sonsFavoritos.add(somFavorito);
	}

	public List<Som> getFeedExtra() {
		return this.feedExtra;
	}

	/**
	 * Adiciona um som ao feed extra do usuario.
	 * @param somID
	 * 				Id do som a ser adicionado
	 */
	public void addFeedExtra(Som som) {
		this.feedExtra.add(som);
	}
}