package exception;

/**
 * Chamada quando o login ja existe. 
 *
 */
public class LoginDuplicadoException extends Exception{
	
	public LoginDuplicadoException(){
		super("J� existe um usu�rio com este login");
	}

}
