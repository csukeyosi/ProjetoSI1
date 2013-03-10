package exception;
/**
 * Chamada quando o login ja existe. 
 *
 */
public class LoginDuplicadoException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public LoginDuplicadoException(){
		super("Já existe um usuário com este login");
	}

}
