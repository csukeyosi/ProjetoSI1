package exception;
/**
 * Chamada quando o ID do som n�o existe no sistema.
 *
 */
public class SomInexistenteException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public SomInexistenteException(){
		super("Som inexistente");
	}

}
