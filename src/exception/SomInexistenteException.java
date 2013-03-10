package exception;

/**
 * Chamada quando o ID do som n�o existe no sistema.
 *
 */
public class SomInexistenteException extends Exception{
	
	public SomInexistenteException(){
		super("Som inexistente");
	}

}
