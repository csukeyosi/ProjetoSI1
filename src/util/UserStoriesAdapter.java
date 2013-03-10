package util;

import java.util.ArrayList;
import java.util.List;

import mainClasses.NetMusicLive;
import mainClasses.Som;
import mainClasses.Usuario;

/**
 * Classe responsavel pela conexao entre {@link NetMusicLive} e os testes easyaccept. 
 *
 */
public class UserStoriesAdapter {

	private NetMusicLive netMusicLive;

	public UserStoriesAdapter() {
		this.netMusicLive = NetMusicLive.getInstance();
	}

	public void zerarSistema() {
		netMusicLive.zerarSistema();
	}

	public void criarUsuario(String login, String senha, String nome,
			String email) throws Exception {
		this.netMusicLive.criarUsuario(login, senha, nome, email);
	}

	public String abrirSessao(String login, String senha) throws Exception {
		return this.netMusicLive.abrirSessao(login, senha);
	}

	public String getAtributoUsuario(String login, String atributo)
			throws Exception {
		return this.netMusicLive.getAtributoUsuario(login, atributo);
	}

	public String getPerfilMusical(String idsessao) {
		List<String> perfilMusical = new ArrayList<String>();
		for (Som som : this.netMusicLive.getPerfilMusical(idsessao)){
			perfilMusical.add(som.getId());
		}
		return retornaComChaves(perfilMusical, "stack");
	}

	public String postarSom(String sessao, String link, String dataCriacao)
			throws Exception {
		return this.netMusicLive.postarSom(sessao, link, dataCriacao);
	}

	public void favoritarSom(String idsessao, String idsom) throws Exception {
		this.netMusicLive.favoritarSom(idsessao, idsom);
	}

	public void seguirUsuario(String idsessao, String login) throws Exception {
		this.netMusicLive.seguirUsuario(idsessao, login);
	}

	public int getNumeroDeSeguidores(String idsessao) throws Exception {
		return this.netMusicLive.getNumeroDeSeguidores(idsessao);
	}

	public String getIDUsuario(String sessao) throws Exception {
		return this.netMusicLive.getIDUsuario(sessao);
	}

	public String getAtributoSom(String idSom, String atributo)
			throws Exception {
		return this.netMusicLive.getAtributoSom(idSom, atributo);
	}

	public String getListaDeSeguidores(String idsessao) throws Exception {
		List<String> seguidores = new ArrayList<String>();
		for (Usuario usuario : this.netMusicLive.getListaDeSeguidores(idsessao)){
			seguidores.add(usuario.getId());
		}
		return retornaComChaves(seguidores,	"list");
	}

	public String getFontesDeSons(String idsessao) throws Exception {
		List<String> fontesSom = new ArrayList<String>();
		for (Usuario fonte : this.netMusicLive.getFontesDeSons(idsessao)){
			fontesSom.add(fonte.getId());
		}
		return retornaComChaves(fontesSom,"list");
	}

	public String getVisaoDosSons(String idsessao) throws Exception {
		List<String> visaoSons = new ArrayList<String>();
		for (Som som : this.netMusicLive.getVisaoDosSons(idsessao)){
			visaoSons.add(som.getId());
		}
		return retornaComChaves(visaoSons, "stack");
	}

	public String getSonsFavoritos(String idsessao) throws Exception {
		List<String> sonsFavoritos = new ArrayList<String>();
		for (Som som : this.netMusicLive.getSonsFavoritos(idsessao)){
			sonsFavoritos.add(som.getId());
		}
		return retornaComChaves(sonsFavoritos, "stack");
	}

	public String getFeedExtra(String idsessao) throws Exception {
		List<String> feedExtra = new ArrayList<String>();
		for (Som som : this.netMusicLive.getFeedExtra(idsessao)){
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
		this.netMusicLive.setMainFeedRule(idsessao, rule);
	}

	/**
	 * 
	 * @param idsessao
	 * @return
	 * @throws Exception
	 */
	public String getMainFeed(String idsessao) throws Exception {
		List<String> feedPrincipal = new ArrayList<String>();
		for (Som som : this.netMusicLive.getMainFeed(idsessao)){
			feedPrincipal.add(som.getId());
		}
		return retornaComChaves(feedPrincipal, "stack");
	}

	public void encerrarSessao(String login) {
		this.netMusicLive.encerrarSessao(login);
	}

	public void encerrarSistema() {
		this.netMusicLive.encerrarSistema();
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
