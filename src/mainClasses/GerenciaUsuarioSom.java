package mainclasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.Utilitario;
import exception.SomInexistenteException;
import exception.SomInvalidoException;

public class GerenciaUsuarioSom {

	private List<Usuario> usuarios;
	private Map<String, Som> sons;

	public GerenciaUsuarioSom() {
		this.usuarios = new ArrayList<Usuario>();
		this.sons = new HashMap<String, Som>();
	}

	public void criarUsuario(String login, String senha, String nome,
			String email) {
		Usuario novoUsuario = new Usuario(login, senha, nome, email);
		usuarios.add(novoUsuario);
		System.out.println(novoUsuario.getNome());
	}

	public boolean verificaLoginESenha(String login, String senha) {
		for (Usuario usuario : usuarios){
			if (usuario.getLogin().equals(login)) {
				if (usuario.getSenha().equals(senha)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean existeEmailUsuario(String email){
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}

	public boolean existeLoginUsuario(String login){
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
		getUsuario(login, "login").postarSom(som);
		return som.getId();
	}

	public boolean favoritarSom(String login, String idSom) {
		Usuario usuario = getUsuario(login, "login");
		Som som = getSom(idSom);
		if (som == null){
			return false;
		}
		usuario.addSonsFavoritos(som);
		som.incrementaFavoritos();
		addInFeedExtra(usuario, som);
		return true;
	}

	public String getAtributoSom(String idSom, String atributo) {
		Som som;
		if (sons.containsKey(idSom)){
			som = (Som) sons.get(idSom);
			if(atributo.equals("dataCriacao")){
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
		return  getUsuario(login, "login").getFontesDeSom();
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

	private void addInFeedExtra(Usuario usuario, Som som) {
		for (Usuario seguidor : usuario.getListaDeSeguidores()) {
			seguidor.addFeedExtra(som);
		}
	}

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

	private Som getSom(String idSom) {
		return (Som) sons.get(idSom);
	}

	private void addVisaoDosSons(Usuario seguidor, Usuario seguido) {
		List<Som> perfilMusicalUsuarioSeguido = seguido.getPerfilMusical();
		if(perfilMusicalUsuarioSeguido != null){
			for(int i=0; i< perfilMusicalUsuarioSeguido.size(); i++){
				seguidor.addVisaoDosSons(perfilMusicalUsuarioSeguido.get(i));
			}
		}
	}

	private Som criarSom(String link, String dataCriacao) {
		String id = "som" + (this.sons.size() + 1) + "ID";
		Som som = new Som(id, link, dataCriacao);
		sons.put(id, som);
		return som;
	}

















}
