package mainclasses;

import javax.servlet.http.HttpServletRequest;

public class FacebookClient {

    public static String API_KEY = "352872411480339";
    public static String SECRET = "926b6e71b6aa4877ef7d4f781e9d9256";
    private static final String client_id = "352872411480339";
    private static final String redirect_uri = "http://localhost:8080/ProjetoSI1/login/homepage.jsf";
    private static final String[] perms = new String[]{"publish_stream", "email"};

    public static String getAPIKey() {
        return API_KEY;
    }

    public static String getSecret() {
        return SECRET;
    }

    public static String getLoginRedirectURL() {
        return "https://graph.facebook.com/oauth/authorize?client_id="
                + API_KEY + "&display=page&redirect_uri="
                + redirect_uri + "&scope=publish_stream, email";
    }

    public static String getAuthURL(String authCode) {
    	if(authCode == null) {
    		throw new RuntimeException("failed login");
    	}
        return "https://graph.facebook.com/oauth/access_token?client_id="
                + API_KEY + "&redirect_uri="
                + redirect_uri + "&client_secret=" + SECRET + "&code=" + authCode;
    }
}
