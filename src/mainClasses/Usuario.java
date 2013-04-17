package mainclasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.common.base.Objects;

/**
 * Classe responsavel pela representacao do usuario no sistema.
 * 
 */
public class Usuario {
	/* Informacoes da conta do usuario */
	private String id, login, senha, nome, email;
	/* Usuarios que seguem este usuario */
	private List<Usuario> listaSeguidores,
	/* Usuarios seguidos */
	fontesDeSom;
	/* sons postados */
	private List<Som> perfilMusical,
	/* sons favoritados */
	sonsFavoritos,
	/* sons favoritados pelos usuarios seguidos */
	feedExtra,
	/* sons de dos usuarios seguidos */
	visaoDosSons;

	private Map<String, ListaCustomizada> listasCustomizadas;
	private int numSonsFavoritados;

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
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.email = email;
		setId(login);
		this.fontesDeSom = new ArrayList<Usuario>();
		this.listaSeguidores = new ArrayList<Usuario>();
		this.visaoDosSons = new ArrayList<Som>();
		this.perfilMusical = new ArrayList<Som>();
		this.sonsFavoritos = new ArrayList<Som>();
		this.feedExtra = new ArrayList<Som>();
		this.listasCustomizadas = new HashMap<String, ListaCustomizada>();
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
	 * Adicionado uma nova fonte de som.
	 * 
	 * @param usuario
	 *            O usuario que sera a nova fonte de som.
	 */
	public void addFontesDeSom(Usuario usuario) {
		this.fontesDeSom.add(usuario);
	}

	public List<Som> getVisaoDosSons() {
		return this.visaoDosSons;
	}

	/**
	 * Metodo que adciona um som na visao de sons do Usuario.
	 * 
	 */
	public void addVisaoDosSons(Som som) {
		this.visaoDosSons.add(som);
	}

	/**
	 * Metodo que retorna o numero de seguidores que o usuario possui.
	 * 
	 * @return Tamanho da lista de seguidores.
	 */
	public int getNumeroDeSeguidores() {
		return this.listaSeguidores.size();
	}

	public List<Usuario> getListaDeSeguidores() {
		return this.listaSeguidores;
	}

	/**
	 * Adiciona um novo usuario a lista de seguidores.
	 * 
	 * @param usuario
	 *            Usuario a ser adicionado.
	 */
	public void addListaDeSeguidores(Usuario usuario) {
		if (!this.listaSeguidores.contains(usuario)) {
			this.listaSeguidores.add(usuario);
			ordenaListaSeguidores();
		}
	}

	/**
	 * Adiciona um som ao perfil musical do usuario.
	 * 
	 * @param som
	 *            Som a ser adicinado.
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
	 * 
	 * @param somFavorito
	 *            O novo som favoritado.
	 */
	public void addSonsFavoritos(Som somFavorito) {
		this.sonsFavoritos.add(somFavorito);
	}

	public List<Som> getFeedExtra() {
		return this.feedExtra;
	}

	/**
	 * Adiciona um som ao feed extra do usuario.
	 * 
	 * @param som
	 *            Som a ser adicionado.
	 */
	public void addFeedExtra(Som som) {
		this.feedExtra.add(som);
	}
	
	public Map<String, ListaCustomizada> getListasCustomizadas() {
		return this.listasCustomizadas;
	}

	/**
	 * Adiciona uma nova lista customizada, caso ela nao exista.
	 * 
	 * @param nomeLista
	 *            Nome da nova lista.
	 * @return O ID da nova lista ou null, caso a lista ja exista.
	 */
	public String addListaCustomizada(String nomeLista) {
		ListaCustomizada novaLista = new ListaCustomizada(nomeLista);
		if (listasCustomizadas.containsKey(novaLista.getId())) {
			return null;
		}
		listasCustomizadas.put(novaLista.getId(), novaLista);
		return novaLista.getId();
	}

	public boolean addUsuarioListaCustomizada(String idLista, Usuario usuario) {
		return listasCustomizadas.get(idLista).addUsuario(usuario);
	}

	/**
	 * Retorna a lista personalizada de sons de ID correspondente.
	 * 
	 * @param idLista
	 *            ID da lista.
	 * @return Lista de sons.
	 */
	public List<Som> getSonsEmLista(String idLista) {
		ListaCustomizada lista = listasCustomizadas.get(idLista);
		if (lista != null) {
			return lista.getSons();
		}
		return null;
	}

	/**
	 * Retorna a soma do numero de favoritos dos sons postados.
	 * 
	 * @return Numero total de favoritos.
	 */
	public int getNumSonsFavoritados() {
		for (Som som : perfilMusical) {
			numSonsFavoritados = numSonsFavoritados
					+ som.getNumeroDeFavoritos();
		}
		return numSonsFavoritados;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if ((obj == null) || !(obj instanceof Usuario)) {
			return false;
		}

		Usuario other = (Usuario) obj;
		return Objects.equal(this.login, other.login)
				&& Objects.equal(this.senha, other.senha)
				&& Objects.equal(this.email, other.email);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.login, this.senha, this.email);
	}

	/**
	 * Metodo que ordena a lista de seguidores em ordem alfabetica.
	 */
	private void ordenaListaSeguidores() {
		List<String> idsSeguidores = new ArrayList<String>();
		for (Usuario usuario : listaSeguidores) {
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
					listaSeguidores.set(i + 1, listaSeguidores.get(i));
					listaSeguidores.set(i, aux2);
					houveTroca = true;
				}
			}
		}
	}
}