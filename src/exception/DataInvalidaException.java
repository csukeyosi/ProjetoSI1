package exception;

/**
 * Chamada quando a data nao e valida.
 *
 */
public class DataInvalidaException extends Exception{
	
	public DataInvalidaException(){
		super("Data de Criação inválida");
	}

}
