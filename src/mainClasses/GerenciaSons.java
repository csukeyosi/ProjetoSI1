package mainclasses;

import java.util.HashMap;
import java.util.List;

import util.Utilitario;
import exception.SomInexistenteException;
import exception.SomInvalidoException;

public class GerenciaSons {

	private HashMap sons;

	/**
	 * Construtor da classe.
	 */
	public GerenciaSons() {
		this.sons = new HashMap<String, Som>();
	}


	public String postarSom(Usuario user, String link, String dataCriacao) {
		String id = "som" + (this.sons.size() + 1) + "ID";
		Som som = new Som(id, link, dataCriacao);
		this.sons.put(id, som);
		user.postarSom(som);
		return id;
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

	public void seguirUsuario(Usuario seguidor, Usuario seguido) throws Exception{
		seguidor.addFontesDeSom(seguido);
		seguido.addListaDeSeguidores(seguidor);
		addVisaoDosSons(seguidor, seguido);
	}

	private void addVisaoDosSons(Usuario seguidor, Usuario seguido){
		List<Som> perfilMusicalUserSeguido = seguido.getPerfilMusical();
		if(perfilMusicalUserSeguido != null){
			auxiliaAddSonsVisao(seguidor, perfilMusicalUserSeguido);
		}

	}

	public void auxiliaAddSonsVisao(Usuario user, List<Som> perfilMusical){
		int sizeList = perfilMusical.size();
		for(int i=0; i<sizeList; i++){
			user.addVisaoDosSons(perfilMusical.get(i));
		}
	}


	public void addInFeedExtra(List<Usuario> usuariosSeguidores, Som som) {
		int sizeList = usuariosSeguidores.size();
		for(int i=0; i<sizeList; i++){
			usuariosSeguidores.get(i).addFeedExtra(som);
		}
	}
	
	public Som getSom(String idSom) throws Exception{
		if(!Utilitario.elementIsValid(idSom)){
			throw new SomInvalidoException();
		}
		if(!this.sons.containsKey(idSom)){
			throw new SomInexistenteException();
		}
		return (Som) sons.get(idSom);
	}
}