package exception;

/**
 * Chamada quando o e-mail ja existe. 
 *
 */
public class EmailDuplicadoException extends Exception{
	
	public EmailDuplicadoException(){
		super("J� existe um usu�rio com este email");
	}

}
