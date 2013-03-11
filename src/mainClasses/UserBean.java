package mainclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;


@ManagedBean(name = "user")
@SessionScoped
public class UserBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String login,password;
	private List<SelectItem> selectFriends;
	private Usuario friend;
	
	public Usuario getFriend(){
		return this.friend;
	}
	
	public void setFriend(Usuario user){
		this.friend = user;
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
	
	public List<Usuario> getFriends(){
		List<Usuario> friends = new ArrayList<Usuario>();
		friends.add(new Usuario("thalysson@gmail", "senha", "nome", "email"));
		friends.add(new Usuario("davyd@gmail", "senha2", "nome2", "email2"));
		return friends;
	}
	

	public List<SelectItem> getSelectFriends(){
		this.selectFriends = new ArrayList<SelectItem>();
		List<Usuario> list = getFriends();           
	       for(Usuario user : list){  
	          SelectItem si = new SelectItem();  
	          si.setLabel(user.getId());  
	          si.setValue(user.getNome());  
	          this.selectFriends.add(si);     
	       }        
	       return this.selectFriends;
	}
}
