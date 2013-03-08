package mainClasses;

import java.util.ArrayList;
import java.util.List;

import exception.SomInexistenteException;
import exception.SomInvalidoException;

import util.Utilitario;

public class GerenciaSons {

	private List<Som> listaDeSons;
	
	/**
	 * Construtor da classe.
	 */
	public GerenciaSons() {
		this.listaDeSons = new ArrayList<Som>();
	}


	public String postarSom(Usuario user, String link, String dataCriacao) {
		String id = "som" + (this.listaDeSons.size() + 1) + "ID";
		Som som = new Som(id, link, dataCriacao);
		this.listaDeSons.add(som);
		user.postarSom(id);
		return id;
	}

	public List<String> getPerfilMusical(Usuario user) {
		return user.getPerfilMusical();
	}
	
	public List<String> getSonsFavoritos(Usuario user){
		return user.getSonsFavoritos();
	}

	public List<String> getFeedExtra(Usuario user){
		return user.getFeedExtra();
	}
	
	public String getAtributoSom(String idSom, String atributo) {
		int sizeList = this.listaDeSons.size();
		for(int i=0;i<sizeList;i++){
			if(this.listaDeSons.get(i).getId().equals(idSom)){
				if(atributo.equals("dataCriacao")){
					return listaDeSons.get(i).getData();
				}
			}
		}
		return "";
	}
	
	public void seguirUsuario(Usuario userSeguidor, Usuario userSeguido) throws Exception{
		userSeguidor.addFontesDeSom(userSeguido.getId());
		userSeguido.addListaDeSeguidores(userSeguidor.getId());
		addVisaoDosSons(userSeguidor, userSeguido);
	}

	private void addVisaoDosSons(Usuario seguidor, Usuario seguido){
		List<String> perfilMusicalUserSeguido = getPerfilMusical(seguido);
		//System.out.println(seguido.getPerfilMusical().size());
		if(perfilMusicalUserSeguido != null){
			auxiliaAddSonsVisao(seguidor,perfilMusicalUserSeguido);
		}

	}
	
	public void auxiliaAddSonsVisao(Usuario user, List<String> perfilMusical){
		int sizeList = perfilMusical.size();
		for(int i=0; i<sizeList; i++){
			user.addEmVisaoDosSons(perfilMusical.get(i));
		}
	}
	
	/** Metodo que retorna a lista de visao de sons de um determinado Usuario.
	 * 
	 * @param user
	 * @return List<String>
	 */
	public List<String> getVisaoDosSons(Usuario user) {
		return user.getVisaoDosSons();
	}


	public void verificaIdSom(String idsom) throws Exception{
		 if(this.listaDeSons.contains(idsom)){
				throw new SomInexistenteException();
			}
		if(!Utilitario.elementIsValid(idsom)){
			throw new SomInvalidoException();
		}
	}

	public void addInFeedExtra(List<Usuario> usuariosSeguidores, String idsom) {
		int sizeList = usuariosSeguidores.size();
		for(int i=0; i<sizeList; i++){
			usuariosSeguidores.get(i).addFeedExtra(idsom);
		}
		
	}
}