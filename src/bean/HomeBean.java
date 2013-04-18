package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import util.Menssagens;

import mainclasses.Som;
import mainclasses.Usuario;

@ManagedBean
public class HomeBean extends DefaultBean {

	private String mensagemDePostagem, idsessao, fotoUser;
	private final String caminhoFotoPadrao = "estilo/images/users/default.png";
	private String textSearch;
	private List<Usuario> searchResults;

	private String fotoUserSelected, nameUserSelected;

	private String media = "http://www.youtube.com/v/KZnUr8lcqjo";

	public HomeBean() {
		super();
		setIDSession();
		setMensagemDePostagem("Postagem de Mensagem..");
	}

	private void setIDSession() {
		this.idsessao = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("idsessao");
	}

	private String getIDSession() {
		return this.idsessao;
	}

	public void postarSom() {
		boolean result = this.interfaceWebAdapter.postarSom(getIDSession(),
				mensagemDePostagem);
		apagarMensagemDePostagem();
		if (!result) {
			Menssagens.addMsgErro("Postagem De Som Invalida");
		}
	}

	public List<Som> getMainFeed() {
		return this.interfaceWebAdapter.getMainFeed(getIDSession());
	}

	public List<String> getFontesDeSom() {
		return this.interfaceWebAdapter.getNomesFontesDeSons(getIDSession());
	}

	public String getFotoUser() {

		setFotoUser(caminhoFotoPadrao);
		return fotoUser;
	}

	public void setFotoUser(String fotoUser) {
		this.fotoUser = fotoUser;
	}

	public String getMensagemDePostagem() {
		return mensagemDePostagem;
	}

	public void setMensagemDePostagem(String mensagemDePostagem) {
		this.mensagemDePostagem = mensagemDePostagem;
	}

	public void apagarMensagemDePostagem() {
		setMensagemDePostagem("");
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String search() {
		if (!getTextSearch().equals(null)) {
			setSearchResults(this.interfaceWebAdapter.search(getTextSearch()));
			return "searchpage?faces-redirect=false";
		}
		// printa mensagem de erro
		return "homepage?faces-redirect=true";
	}

	public List<Usuario> getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(List<Usuario> searchResults) {
		this.searchResults = searchResults;
	}

	public String getTextSearch() {
		return textSearch;
	}

	public void setTextSearch(String textSearch) {
		this.textSearch = textSearch;
	}

	public String getFotoUserSelected() {
		return fotoUserSelected;
	}

	public void setFotoUserSelected(String fotoUserSelected) {
		this.fotoUserSelected = fotoUserSelected;
	}

	public String getNameUserSelected() {
		return nameUserSelected;
	}

	public void setNameUserSelected(String nameUserSelected) {
		this.nameUserSelected = nameUserSelected;
	}
}
