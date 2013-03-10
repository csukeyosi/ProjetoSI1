package exception;
/**
 * Chamada quando o login nao existe no sistema.
 *
 */
public class LoginInexistenteException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public LoginInexistenteException() {
		super("Login inexistente");
	}

}
