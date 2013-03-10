package mainclasses;

import java.util.HashMap;
import java.util.List;

import util.Utilitario;
import exception.SomInexistenteException;
import exception.SomInvalidoException;

/**
 * Classe responsavel pelo gerenciamento dos sons {@link Som} do Sistema.
 *
 */
public class GerenciaSons {
	/*Colecao dos sons do sistema */
	private HashMap sons;

	public GerenciaSons() {
		this.sons = new HashMap<String, Som>();
	}

	/**
	 * Simula a acao do usuario postar um novo som. Adiciona o Som na colecao de sons do sistema e 
	 * no perfil musical do usuario em questao. 
	 * 
	 * @param user
	 * 			Usuario que esta postando o som.
	 * @param link
	 * 			Link do som que esta sendo postado.
	 * @param dataCriacao
	 * 			Data da postagem.
	 * @return O id do som adicionado.
	 */
	public String postarSom(Usuario user, String link, String dataCriacao) {
		String id = "som" + (this.sons.size() + 1) + "ID";
		Som som = new Som(id, link, dataCriacao);
		this.sons.put(id, som);
		user.postarSom(som);
		return id;
	}

	/**
	 * Retorna o valor do atributo do idsom passado como parametro.
	 * 
	 * @param idSom
	 * 			Id do som que deseja-se saber o valor do atributo.
	 * @param atributo
	 * 			Atributo que deseja-se saber o valor.
	 * @return Valor correspondente ao atributo.
	 */
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

	/**
	 * Simula a acao de um usuario seguir outro usuario.
	 * 
	 * @param seguidor
	 * 				Usuario ativo da acao.
	 * @param seguido
	 * 				Usuario passivo da acao.
	 */
	public void seguirUsuario(Usuario seguidor, Usuario seguido){
		seguidor.addFontesDeSom(seguido);
		seguido.addListaDeSeguidores(seguidor);
		addVisaoDosSons(seguidor, seguido);
	}

	/**
	 * Adiciona os sons postados pelo seguido a visao dos sons do seguidor.
	 * 
	 * @param seguidor
	 * 				Usuario que vai ter sua visao de sons alterada.
	 * @param seguido
	 * 				Usuario cujo sons vao ser adicinados na visao de sons do outro usuario.
	 */
	private void addVisaoDosSons(Usuario seguidor, Usuario seguido){
		List<Som> perfilMusicalUsuarioSeguido = seguido.getPerfilMusical();
		if(perfilMusicalUsuarioSeguido != null){
			for(int i=0; i< perfilMusicalUsuarioSeguido.size(); i++){
				seguidor.addVisaoDosSons(perfilMusicalUsuarioSeguido.get(i));
			}
		}
	}

	/**
	 * Adiciona no feed extra dos usuarios o som passado como parametro.
	 * 
	 * @param usuariosSeguidores
	 * 			Lista de usuarios onde sera  adicionado o novo som.
	 * @param som
	 * 			Som a ser adicionado.
	 */
	public void addInFeedExtra(List<Usuario> usuariosSeguidores, Som som) {
		int sizeList = usuariosSeguidores.size();
		for(int i=0; i<sizeList; i++){
			usuariosSeguidores.get(i).addFeedExtra(som);
		}
	}

	/**
	 * Retorna o {@link Som} correspondente ao idsom passado como parametro.
	 * 
	 * @param idSom
	 * 			Id do som a ser recuperado.
	 * @return O Som do idsom.
	 * @throws Exception 
	 * 			{@link SomInvalidoException,  SomInexistenteException}
	 */
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