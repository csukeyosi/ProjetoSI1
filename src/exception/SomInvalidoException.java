package exception;

/**
 * Chamada quando o Id do som � igual a "" ou null.
 *
 */
public class SomInvalidoException extends Exception{
	
	
	public SomInvalidoException(){
		super("Som inv�lido");
	}

}
