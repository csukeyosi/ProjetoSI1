package mainclasses;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.json.JsonObject;

public class UsuarioService {

    public void authFacebookLogin(String accessToken, int expires) {
        try {
//            JSONObject resp = new JSONObject(
//                    new URL("https://graph.facebook.com/me?access_token=" + accessToken));
//            id = resp.getString("id");
//            nome = resp.getString("first_name");
//            sobrenome = resp.getString("last_name");
//            email = resp.getString("email");

            FacebookClient fb = new DefaultFacebookClient(accessToken);

            
            UsuarioFB user = fb.fetchObject("me", UsuarioFB.class);

            System.out.println(user);


        } catch (Throwable ex) {
            throw new RuntimeException("failed login", ex);
        }
    }
}
