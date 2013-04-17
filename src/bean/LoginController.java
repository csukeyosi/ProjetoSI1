package bean;

import java.net.URLEncoder;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import mainclasses.FacebookClient;
import mainclasses.UsuarioFB;

@ManagedBean
@SessionScoped
public class LoginController {

//	@ManagedProperty(value="#{param.code}")
	//private String acessToken;
	
    private String login;
    private String senha;
    private boolean facebook;
    private boolean logado;
    private String conexaoFB;
    private UsuarioFB usuario;

    @PostConstruct
    public void conectarComFB() {
        conexaoFB = "http://www.facebook.com/dialog/oauth?"
                + "client_id=352872411480339&";
        conexaoFB = conexaoFB
                + "redirect_uri="
                + URLEncoder.encode("http://localhost:8080/ProjetoSI1/homepage.jsf")
                + "";
    }

    public UsuarioFB getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioFB usuario) {
        login = usuario.getEmail();
        logado = true;
        this.usuario = usuario;
    }

    public String conectarComFace() {
        return FacebookClient.getLoginRedirectURL();
    }

    public void logar() {
        facebook = false;
        logado = true;
    }

    public void logout() {
        facebook = false;
        logado = false;
        login = "";
        senha = "";
    }

    public String getConexaoFB() {
        return conexaoFB;
    }

    public void setConexaoFB(String conexaoFB) {
        this.conexaoFB = conexaoFB;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public boolean isFacebook() {
        return facebook;
    }

    public void setFacebook(boolean facebook) {
        this.facebook = facebook;
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
