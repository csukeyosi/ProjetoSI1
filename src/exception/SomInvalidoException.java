package exception;

/**
 * Chamada quando o Id do som é igual a "" ou null.
 *
 */
public class SomInvalidoException extends Exception{
	
	
	public SomInvalidoException(){
		super("Som inválido");
	}

}
