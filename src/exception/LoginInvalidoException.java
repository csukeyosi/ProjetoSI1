package exception;

/**
 * Chamada quando o login � igual a "" ou null.
 *
 */
public class LoginInvalidoException extends Exception{
	
	public LoginInvalidoException(){
		super ("Login inv�lido");
	}

}
