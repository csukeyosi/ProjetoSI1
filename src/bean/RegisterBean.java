package bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;

import util.InterfaceWebAdapter;

@ManagedBean
public class RegisterBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String login, nome, email, senha;
	private InterfaceWebAdapter interfaceWebAdapter;
	
	public RegisterBean(){
		interfaceWebAdapter = InterfaceWebAdapter.getInstance();
	}

	public String criaUsuario(){
		try {
			interfaceWebAdapter.criaUsuario(getLogin(), getSenha(), getNome(), getEmail());
			// falta colocar o idsessao no mapa de sessao do jsf.
			//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("yourKey", yourObject);
			return "homepage?faces-redirect=true";
		} catch (Exception e) {
			System.out.println("erro ao criar usuario");
			// gera um erro na interface.
		}
		return "register?faces-redirect=true";
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}