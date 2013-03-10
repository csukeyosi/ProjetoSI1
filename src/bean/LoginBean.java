package bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;

@ManagedBean
@SessionScoped
public class LoginBean extends DefaultBean implements Serializable{

	private static final long serialVersionUID = 1L;

	public LoginBean(){
		super();
	}
	
	public String logar(){
		try {
			if(interfaceWebAdapter.verificaLoginESenha(getLogin(),getSenha())){
				String idsessao;
				if(interfaceWebAdapter.existeSessao(getLogin())){
					idsessao = "sessao"+getLogin();
				}else{
					idsessao = interfaceWebAdapter.abrirSessao(getLogin(),getSenha());
				}
				putInSession("idsessao", idsessao);
				return "homepage?faces-redirect=true";
			}				
		} catch (Exception e) {
			System.out.println("erro ao abrir sessao (logar)");
			// gera erro na tela;
		}
		return "index?faces-redirect=true";
	}
}