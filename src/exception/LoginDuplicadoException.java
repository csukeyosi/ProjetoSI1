package exception;

/**
 * Chamada quando o login ja existe. 
 *
 */
public class LoginDuplicadoException extends Exception{
	
	public LoginDuplicadoException(){
		super("Já existe um usuário com este login");
	}

}
