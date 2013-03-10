package exception;
/**
 * Chamada quando o Id do som e igual a "" ou null.
 *
 */
public class SomInvalidoException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public SomInvalidoException(){
		super("Som inválido");
	}

}
