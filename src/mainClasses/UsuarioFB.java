package mainclasses;

import com.restfb.Facebook;

public class UsuarioFB {

    @Facebook
    private String id;
    @Facebook
    private String name;
    @Facebook
    private String email;
    @Facebook
    private String username;

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", name=" + name + ", email=" + email + "username="+username+'}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
