package exception;

/**
 * Chamada quando o e-mail ja existe. 
 *
 */
public class EmailDuplicadoException extends Exception{
	
	public EmailDuplicadoException(){
		super("Já existe um usuário com este email");
	}

}
