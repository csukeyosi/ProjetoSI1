package bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import util.Menssagens;

@ManagedBean
@SessionScoped
public class RegisterBean extends DefaultBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nome, email;
	
	public RegisterBean(){
		super();
	}

	public String criaUsuario(){
		try {
			interfaceWebAdapter.criaUsuario(getLogin(), getSenha(), getNome(), getEmail());
			if(!interfaceWebAdapter.existeSessao(getLogin())){
				String idsessao = interfaceWebAdapter.abrirSessao(getLogin(),getSenha());
				putInSession("idsessao", idsessao);
			}
			return "homepage?faces-redirect=true";
		} catch (RuntimeException e) {
			Menssagens.addMsgErro(e.getMessage());
		}
		return "";
	}
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}