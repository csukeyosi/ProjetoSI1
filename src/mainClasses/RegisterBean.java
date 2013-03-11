package mainclasses;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "register")
@SessionScoped
public class RegisterBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String login,nome,email,senha;
	
	public String getLogin() {return login;}
	
	public void setLogin(String login) {this.login = login;}
	
	public String getNome() {return nome;}
	
	public void setNome(String nome) {this.nome = nome;}
	
	public String getEmail() {return email;}
	
	public void setEmail(String email) {this.email = email;}
	
	public String getSenha() {return senha;}
	
	public void setSenha(String senha) {this.senha = senha;}

}
