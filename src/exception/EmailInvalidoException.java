package exception;

/**
 * Chamada quando o e-mail � igual a "" ou null. 
 *
 */
public class EmailInvalidoException extends Exception{
	
	public EmailInvalidoException(){
		super("Email inv�lido");
	}

}
