package mainClasses;

import java.util.List;

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

	public String getPerfilMusical(String sessao) {
		return retornaComChaves(this.sistema.getPerfilMusical(sessao), "stack");
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

	public String getListaDeSeguidores(String idsessao) throws Exception {
		return retornaComChaves(this.sistema.getListaDeSeguidores(idsessao),
				"list");
	}

	public String getFontesDeSons(String idsessao) throws Exception {
		return retornaComChaves(this.sistema.getFontesDeSons(idsessao),"list");
	}

	public String getVisaoDosSons(String idsessao) throws Exception {
		return retornaComChaves(this.sistema.getVisaoDosSons(idsessao), "stack");
	}

	public String getSonsFavoritos(String idsessao) throws Exception {
		return retornaComChaves(this.sistema.getSonsFavoritos(idsessao),
				"stack");
	}

	public String getFeedExtra(String idsessao) throws Exception {
		return retornaComChaves(this.sistema.getFeedExtra(idsessao), "stack");
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

	public String getMainFeed(String idsessao) throws Exception {
		return retornaComChaves(this.sistema.getMainFeed(idsessao), "stack");
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
