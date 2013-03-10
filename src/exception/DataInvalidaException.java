package exception;

/**
 * Chamada quando a data nao e valida.
 *
 */
public class DataInvalidaException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public DataInvalidaException(){
		super("Data de Criação inválida");
	}

}
