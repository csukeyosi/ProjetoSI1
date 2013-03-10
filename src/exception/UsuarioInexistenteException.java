package exception;

/**
 * Quando o usuario pesquisado nao existe no sistema. 
 *
 */
public class UsuarioInexistenteException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public UsuarioInexistenteException(){
		super("Usuário inexistente");
	}

}
