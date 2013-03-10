package exception;

/**
 * Chamada quando o e-mail é igual a "" ou null. 
 *
 */
public class EmailInvalidoException extends Exception{
	
	public EmailInvalidoException(){
		super("Email inválido");
	}

}
