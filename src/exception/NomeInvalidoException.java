package exception;

/**
 * Chamada quando o nome ï¿½ igual a "" ou null. 
 *
 */
public class NomeInvalidoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NomeInvalidoException(){
		super("Nome inválido");
	}

}
