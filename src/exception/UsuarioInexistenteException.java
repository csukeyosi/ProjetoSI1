package exception;

/**
 * Quando o usuario pesquisado nao existe no sistema. 
 *
 */
public class UsuarioInexistenteException extends Exception{
	
	public UsuarioInexistenteException(){
		super("Usuário inexistente");
	}

}
