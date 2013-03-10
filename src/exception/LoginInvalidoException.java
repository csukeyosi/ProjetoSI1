package exception;

/**
 * Chamada quando o login é igual a "" ou null.
 *
 */
public class LoginInvalidoException extends Exception{
	
	public LoginInvalidoException(){
		super ("Login inválido");
	}

}
