package bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import mainClasses.InterfaceWebAdapter;
import mainClasses.Som;

@ManagedBean
public class HomeBean {

	private InterfaceWebAdapter interfaceWebAdapter;
	private List<String> nomeUsuariosQueSegue;
	private String fotoUser = "estilo/images/users/default.png";
	private String mensagemDePostagem;

	private List<Som> sonsPostados;
	private Som selectedSom;
	
	
	public HomeBean() {
		this.interfaceWebAdapter = InterfaceWebAdapter.getInstance();
		setMensagemDePostagem("Postagem de Mensagem..");
	}

	public List<String> getUsuariosQueSegue() {
		try {
			//tenho que descobrir o id da sessao, ou senao pega-la apos fazer login.
			nomeUsuariosQueSegue = this.interfaceWebAdapter.getNomesFontesDeSons("");			
		} catch (Exception e) {
			System.out.println("erro ao pegar usuarios que segue (suas fontes de som)");
		}
		return nomeUsuariosQueSegue;
	}

	public String getFotoUser() {
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

	public List<Som> getSonsPostados() {
		sonsPostados = new ArrayList<Som>();
		sonsPostados.add(new Som("som1ID", "http://www.youtube.com/", "data"));
		sonsPostados.add(new Som("som2ID", "http://www.kboing.com.br/", "data"));
		sonsPostados.add(new Som("som3ID", "http://www.vimeo.com/", "data"));

		return sonsPostados;
	}

	public void setSonsPostados(List<Som> sonsPostados) {
		this.sonsPostados = sonsPostados;
	}

	public Som getSelectedSom() {
		return selectedSom;
	}

	public void setSelectedSom(Som selectedSom) {
		this.selectedSom = selectedSom;
	}
}
