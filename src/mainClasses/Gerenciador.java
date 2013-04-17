package mainclasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.Utilitario;

/**
 * Classe reponsavel pelo gerenciamento de {@link Usuario}, {@link Som} e suas
 * interacoes.
 */
public class Gerenciador {

	private List<Usuario> usuarios;
	private Map<String, Som> sons;

	public Gerenciador() {
		this.usuarios = new ArrayList<Usuario>();
		this.sons = new HashMap<String, Som>();
	}

	public void criarUsuario(String login, String senha, String nome,
			String email) {
		Usuario novoUsuario = new Usuario(login, senha, nome, email);
		usuarios.add(novoUsuario);
	}

	public boolean verificaLoginESenha(String login, String senha) {
		for (Usuario usuario : usuarios) {
			if (usuario.getLogin().equals(login)) {
				if (usuario.getSenha().equals(senha)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean existeEmailUsuario(String email) {
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}

	public boolean existeLoginUsuario(String login) {
		for (Usuario usuario : usuarios) {
			if (usuario.getLogin().equals(login)) {
				return true;
			}
		}
		return false;
	}

	public String getAtributoUsuario(String login, String atributo) {
		Usuario usuario = getUsuario(login, "login");
		if (atributo.equals("nome")) {
			return usuario.getNome();

		} else if (atributo.equals("email")) {
			return usuario.getEmail();

		} else if (atributo.equals("id")) {
			return usuario.getId();
		}

		return "";
	}

	public List<Som> getPerfilMusical(String login) {
		return getUsuario(login, "login").getPerfilMusical();
	}

	public String postarSom(String login, String link, String dataCriacao) {
		Som som = criarSom(link, dataCriacao);
		Usuario usuario = getUsuario(login, "login");
		usuario.postarSom(som);
		addEmVisaoDosSons(usuario, som);
		addEmListaCustomizada(usuario, som);
		return som.getId();
	}

	public boolean favoritarSom(String login, String idSom) {
		Usuario usuario = getUsuario(login, "login");
		Som som = getSom(idSom);
		if (som == null) {
			return false;
		}
		usuario.addSonsFavoritos(som);
		som.incrementaFavoritos();
		addEmFeedExtra(usuario, som);
		return true;
	}

	public String getAtributoSom(String idSom, String atributo) {
		Som som;
		if (sons.containsKey(idSom)) {
			som = (Som) sons.get(idSom);
			if (atributo.equals("dataCriacao")) {
				return som.getData();
			}
		}
		return "";
	}

	public void seguirUsuario(String loginSeguidor, String loginSeguido) {
		Usuario seguidor = getUsuario(loginSeguidor, "login");
		Usuario seguido = getUsuario(loginSeguido, "login");

		seguidor.addFontesDeSom(seguido);
		seguido.addListaDeSeguidores(seguidor);
		addVisaoDosSons(seguidor, seguido);

	}

	public List<Usuario> getListaSeguidoresUsuario(String login) {
		return getUsuario(login, "login").getListaDeSeguidores();
	}

	public List<Usuario> getFontesSomUsuario(String login) {
		return getUsuario(login, "login").getFontesDeSom();
	}

	public List<Som> getVisaoSonsUsuario(String login) {
		return getUsuario(login, "login").getVisaoDosSons();
	}

	public List<Som> getSonsFavoritosUsuario(String login) {
		return getUsuario(login, "login").getSonsFavoritos();
	}

	public List<Som> getFeedExtraUsuario(String login) {
		return getUsuario(login, "login").getFeedExtra();
	}

	public String criarLista(String login, String nomeLista) {
		return getUsuario(login, "login").addListaCustomizada(nomeLista);
	}

	public boolean adicionaUsuario(String login, String idLista,
			String idUsuario) throws Exception {
		Usuario usuario1 = getUsuario(login, "login");
		Usuario usuario2 = getUsuario(idUsuario, "id");

		if (usuario1 == null || usuario2 == null) {
			throw new Exception("Usuário inválido");
		}
		if (!(Utilitario.elementIsValid(idLista))) {
			throw new Exception("Lista inválida");
		}
		if (usuario1.equals(usuario2)) {
			throw new Exception("Usuário não pode adicionar-se a própria lista");
		}
		if (!usuario1.addUsuarioListaCustomizada(idLista, usuario2)) {
			throw new Exception("Usuário já existe nesta lista");
		}
		return true;
	}

	public List<Som> getSonsEmLista(String login, String idLista) {
		return getUsuario(login, "login").getSonsEmLista(idLista);
	}

	/**
	 * Adiciona o som favoritado pelo usuario seguido ao feed extra do usuarios
	 * seguidores.
	 * 
	 * @param usuario
	 *            Usuario seguido que esta postando um som.
	 * @param som
	 *            Som postado.
	 */
	private void addEmFeedExtra(Usuario usuario, Som som) {
		for (Usuario seguidor : usuario.getListaDeSeguidores()) {
			seguidor.addFeedExtra(som);
		}
	}

	/**
	 * Adiciona o som postado pelo usuario seguido ao feed extra do usuarios
	 * seguidores.
	 * 
	 * @param usuario
	 *            Usuario seguido que esta postando um som.
	 * @param som
	 *            Som postado.
	 */
	private void addEmVisaoDosSons(Usuario usuario, Som som) {
		for (Usuario seguidor : usuario.getListaDeSeguidores()) {
			seguidor.addVisaoDosSons(som);
		}
	}

	/**
	 * Adiciona o som postado pelo usuario seguido ao sons das listas
	 * customizadas.
	 * 
	 * @param usuario
	 *            Usuario que esta na lista customizada.
	 * @param som
	 *            Som postado pelo usuario.
	 */
	private void addEmListaCustomizada(Usuario usuario, Som som) {
		for (Usuario seguidor : usuario.getListaDeSeguidores()) {
			for (ListaCustomizada lista : seguidor.getListasCustomizadas()
					.values()) {
				if (lista.getUsuarios().contains(usuario)) {
					List<Som> auxLista = new ArrayList<Som>();
					auxLista.add(som);
					lista.addSons(auxLista);
				}

			}
		}
	}

	/**
	 * Retorna o usuario correspondete ao login ou id passado como parametro.
	 * 
	 * @param valorLoginOuId
	 *            Valor do login ou id do usuario.
	 * @param loginOuId
	 *            Tipo "login" ou "id".
	 * @return Usuario correspondete. null caso não haja.
	 */
	private Usuario getUsuario(String valorLoginOuId, String loginOuId) {
		for (int i = 0; i < usuarios.size(); i++) {
			if ("login".equals(loginOuId)) {
				if (usuarios.get(i).getLogin().equals(valorLoginOuId)) {
					return usuarios.get(i);
				}

			} else if ("id".equals(loginOuId)) {
				if (usuarios.get(i).getId().equals(valorLoginOuId)) {
					return usuarios.get(i);
				}
			}
		}
		return null;
	}

	/**
	 * Retorna o som correspondente.
	 * 
	 * @param idSom
	 *            ID do som.
	 * @return Som.
	 */
	private Som getSom(String idSom) {
		return (Som) sons.get(idSom);
	}

	/**
	 * Adiciona os sons postados pelo seguido a visao de som do seguidor.
	 * 
	 * @param seguidor
	 *            Usuario seguidor.
	 * @param seguido
	 *            Usuario seguido.
	 */
	private void addVisaoDosSons(Usuario seguidor, Usuario seguido) {
		List<Som> perfilMusicalUsuarioSeguido = seguido.getPerfilMusical();
		if (perfilMusicalUsuarioSeguido != null) {
			for (int i = 0; i < perfilMusicalUsuarioSeguido.size(); i++) {
				seguidor.addVisaoDosSons(perfilMusicalUsuarioSeguido.get(i));
			}
		}
	}

	/**
	 * Cria um novo som.
	 * 
	 * @param link
	 *            Link do som.
	 * @param dataCriacao
	 *            Data de criacao.
	 * @return Som.
	 */
	private Som criarSom(String link, String dataCriacao) {
		String id = "som" + (this.sons.size() + 1) + "ID";
		Som som = new Som(id, link, dataCriacao);
		sons.put(id, som);
		return som;
	}

	public int getNumFontesEmComum(String login, String idUsuario) throws Exception {
		if (!Utilitario.elementIsValid(idUsuario)) {
			throw new Exception("Usuário inválido");
		}
		return new SistemaRecomendacao(this.usuarios,
				getUsuario(login, "login")).getNumFontesEmComum(getUsuario(
				idUsuario, "id"));
	}

	public int getNumFavoritosEmComum(String login, String idUsuario) throws Exception {
		if (!Utilitario.elementIsValid(idUsuario)) {
			throw new Exception("Usuário inválido");
		}
		return new SistemaRecomendacao(this.usuarios,
				getUsuario(login, "login")).getNumFavoritosEmComum(getUsuario(
				idUsuario, "id"));
	}

	public List<Usuario> getFontesDeSonsRecomendadas(String login) {
		Usuario u = getUsuario(login, "login");
		return new SistemaRecomendacao(this.usuarios, u)
				.getUsuariosRecomendados();
	}
}