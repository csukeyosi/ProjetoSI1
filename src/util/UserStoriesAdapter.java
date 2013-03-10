package util;

import java.util.ArrayList;
import java.util.List;

import mainClasses.Sistema;
import mainClasses.Som;
import mainClasses.Usuario;

public class UserStoriesAdapter {

	private Sistema sistema;

	public UserStoriesAdapter() {
		this.sistema = Sistema.getInstance();
	}

	public void zerarSistema() {
		sistema.zerarSistema();
	}

	public void criarUsuario(String login, String senha, String nome,
			String email) throws Exception {
		this.sistema.criarUsuario(login, senha, nome, email);
	}

	public String abrirSessao(String login, String senha) throws Exception {
		return this.sistema.abrirSessao(login, senha);
	}

	public String getAtributoUsuario(String login, String atributo)
			throws Exception {
		return this.sistema.getAtributoUsuario(login, atributo);
	}

	/**
	 * 
	 * @param idsessao
	 * @return
	 */
	public String getPerfilMusical(String idsessao) {
		List<String> perfilMusical = new ArrayList<String>();
		for (Som som : this.sistema.getPerfilMusical(idsessao)){
			perfilMusical.add(som.getId());
		}
		return retornaComChaves(perfilMusical, "stack");
	}

	public String postarSom(String sessao, String link, String dataCriacao)
			throws Exception {
		return this.sistema.postarSom(sessao, link, dataCriacao);
	}

	public void favoritarSom(String idsessao, String idsom) throws Exception {
		this.sistema.favoritarSom(idsessao, idsom);
	}

	public void seguirUsuario(String idsessao, String login) throws Exception {
		this.sistema.seguirUsuario(idsessao, login);
	}

	public int getNumeroDeSeguidores(String idsessao) throws Exception {
		return this.sistema.getNumeroDeSeguidores(idsessao);
	}

	public String getIDUsuario(String sessao) throws Exception {
		return this.sistema.getIDUsuario(sessao);
	}

	public String getAtributoSom(String idSom, String atributo)
			throws Exception {
		return this.sistema.getAtributoSom(idSom, atributo);
	}
	
	/**
	 * 
	 * @param idsessao
	 * @return
	 * @throws Exception
	 */
	public String getListaDeSeguidores(String idsessao) throws Exception {
		List<String> seguidores = new ArrayList<String>();
		for (Usuario usuario : this.sistema.getListaDeSeguidores(idsessao)){
			seguidores.add(usuario.getId());
		}
		return retornaComChaves(seguidores,	"list");
	}

	/**
	 * 
	 * @param idsessao
	 * @return
	 * @throws Exception
	 */
	public String getFontesDeSons(String idsessao) throws Exception {
		List<String> fontesSom = new ArrayList<String>();
		for (Usuario fonte : this.sistema.getFontesDeSons(idsessao)){
			fontesSom.add(fonte.getId());
		}
		return retornaComChaves(fontesSom,"list");
	}
	
	/**
	 * 
	 * @param idsessao
	 * @return
	 * @throws Exception
	 */
	public String getVisaoDosSons(String idsessao) throws Exception {
		List<String> visaoSons = new ArrayList<String>();
		for (Som som : this.sistema.getVisaoDosSons(idsessao)){
			visaoSons.add(som.getId());
		}
		return retornaComChaves(visaoSons, "stack");
	}
	
	/**
	 * 
	 * @param idsessao
	 * @return
	 * @throws Exception
	 */
	public String getSonsFavoritos(String idsessao) throws Exception {
		List<String> sonsFavoritos = new ArrayList<String>();
		for (Som som : this.sistema.getSonsFavoritos(idsessao)){
			sonsFavoritos.add(som.getId());
		}
		return retornaComChaves(sonsFavoritos, "stack");
	}
	
	/**
	 * 
	 * @param idsessao
	 * @return
	 * @throws Exception
	 */
	public String getFeedExtra(String idsessao) throws Exception {
		List<String> feedExtra = new ArrayList<String>();
		for (Som som : this.sistema.getFeedExtra(idsessao)){
			feedExtra.add(som.getId());
		}
		return retornaComChaves(feedExtra, "stack");
	}

	public String getFirstCompositionRule(){
		return "PRIMEIRO OS SONS POSTADOS MAIS RECENTEMENTE PELAS FONTES DE SONS";
	}

	public String getSecondCompositionRule(){
		return "PRIMEIRO OS SONS COM MAIS FAVORITOS";
	}

	public String getThirdCompositionRule(){
		return "PRIMEIRO SONS DE FONTES DAS QUAIS FAVORITEI SONS NO PASSADO";
	}
	public void setMainFeedRule(String idsessao,String rule) throws Exception{
		this.sistema.setMainFeedRule(idsessao, rule);
	}
	
	/**
	 * 
	 * @param idsessao
	 * @return
	 * @throws Exception
	 */
	public String getMainFeed(String idsessao) throws Exception {
		List<String> feedPrincipal = new ArrayList<String>();
		for (Som som : this.sistema.getMainFeed(idsessao)){
			feedPrincipal.add(som.getId());
		}
		return retornaComChaves(feedPrincipal, "stack");
	}

	public void encerrarSessao(String login) {
		this.sistema.encerrarSessao(login);
	}

	public void encerrarSistema() {
		this.sistema.encerrarSistema();
	}
	

	private String retornaComChaves(List<String> list, String formato) {
		String retorno = "{";
		int sizeList = list.size();
		if (formato.equals("stack")) {
			for (int i = sizeList - 1; i >= 0; i--) {
				retorno = retorno + list.get(i);
				if (i > 0) {
					retorno = retorno + ",";
				}
			}
		} else if (formato.equals("list")) {
			for (int i = 0; i < sizeList; i++) {
				retorno = retorno + list.get(i);
				if (i < (sizeList - 1)) {
					retorno = retorno + ",";
				}
			}
		}
		retorno = retorno + "}";
		return retorno;
	}
}
