package exception;
/**
 * Chamada quando o e-mail e igual a "" ou null. 
 *
 */
public class EmailInvalidoException extends Exception{
	

	private static final long serialVersionUID = 1L;

	public EmailInvalidoException(){
		super("Email inválido");
	}

}
