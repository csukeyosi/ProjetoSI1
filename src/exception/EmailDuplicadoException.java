package exception;

/**
 * Chamada quando o e-mail ja existe. 
 *
 */

public class EmailDuplicadoException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public EmailDuplicadoException(){
		super("Já existe um usuário com este email");
	}

}
