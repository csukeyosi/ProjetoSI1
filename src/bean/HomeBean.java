package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import mainClasses.Som;

@ManagedBean
public class HomeBean extends DefaultBean{

	private String mensagemDePostagem,
					idsessao,
					fotoUser;
	private final String caminhoFotoPadrao ="estilo/images/users/default.png";
	
	public HomeBean() {
		super();
		setIDSession();
		setMensagemDePostagem("Postagem de Mensagem..");
	}

	private void setIDSession() {
		this.idsessao = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idsessao");
	}
	
	private String getIDSession(){
		return this.idsessao;
	}

	public void postarSom(){
		
	}

	public List<Som> getMainFeed(){
		return this.interfaceWebAdapter.getMainFeed(getIDSession());
	}
	
	public List<String> getFontesDeSom(){
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
	
	public void apagarMensagemDePostagem(){
		setMensagemDePostagem("");
	}
}
