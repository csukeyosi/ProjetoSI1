package util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;

import mainclasses.FacebookClient;
import mainclasses.UsuarioService;

public class FacebookFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) sr1;
        String code = sr.getParameter("code");

        if (code != "") {
        	String authURL = FacebookClient.getAuthURL(code);
            URL url = new URL(authURL);
            try {
                String result = readURL(url);
                String accessToken = null;
                Integer expires = null;
                String[] pairs = result.split("&");
                for (String pair : pairs) {
                    String[] kv = pair.split("=");
                    if (kv.length != 2) {
                        throw new RuntimeException("Unexpected auth response");
                    } else {
                        if (kv[0].equals("access_token")) {
                            accessToken = kv[1];
                        }
                        if (kv[0].equals("expires")) {
                            expires = Integer.valueOf(kv[1]);
                        }
                    }
                }
                if (accessToken != null && expires != null) {

                    UsuarioService us =  new UsuarioService();
                    us.authFacebookLogin(accessToken, expires);
                    res.sendRedirect("http://localhost:8080/ProjetoSI1/homepage.jsf");
                } else {
                    throw new RuntimeException("Access token e expires não encontrados.");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String readURL(URL url) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream is = url.openStream();
        int r;
        while ((r = is.read()) != -1) {
            baos.write(r);
        }
        return new String(baos.toByteArray());
    }

    @Override
    public void destroy() {
    }
}
