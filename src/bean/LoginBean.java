package bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import util.InterfaceWebAdapter;


@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String login,password;
	private InterfaceWebAdapter interfaceWebAdapter;

	public LoginBean(){
		this.interfaceWebAdapter = InterfaceWebAdapter.getInstance();
	}
	
	public String logar(){
		try {
			String idsessao = interfaceWebAdapter.abrirSessao(getLogin(),getPassword());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idsessao",idsessao);
			return "homepage?faces-redirect=true";
		} catch (Exception e) {
			System.out.println("erro ao abrir sessao (logar)");
			// gera erro na tela;
		}
		return "index?faces-redirect=true";
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}