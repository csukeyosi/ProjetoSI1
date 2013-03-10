package bean;

import javax.faces.context.FacesContext;

import util.InterfaceWebAdapter;

public class DefaultBean {

	protected InterfaceWebAdapter interfaceWebAdapter;
	protected String login,senha;

	public DefaultBean(){
		interfaceWebAdapter = InterfaceWebAdapter.getInstance();
	}

	protected void putInSession(String key, Object value) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
}
