package exception;

/**
 * Chamada quando o login nao existe no sistema.
 *
 */
public class LoginInexistenteException extends Exception{
	
	public LoginInexistenteException() {
		super("Login inexistente");
	}

}
