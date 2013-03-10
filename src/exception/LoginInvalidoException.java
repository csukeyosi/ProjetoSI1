package exception;

/**
 * Chamada quando o login e igual a "" ou null.
 *
 */
public class LoginInvalidoException extends Exception{
	

	private static final long serialVersionUID = 1L;

	public LoginInvalidoException(){
		super ("Login inválido");
	}

}
