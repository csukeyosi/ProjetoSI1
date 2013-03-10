package exception;

/**
 * Chamada quando o login � igual a "" ou null.
 *
 */
public class LoginInvalidoException extends Exception{
	

	private static final long serialVersionUID = 1L;

	public LoginInvalidoException(){
		super ("Login inválido");
	}

}
