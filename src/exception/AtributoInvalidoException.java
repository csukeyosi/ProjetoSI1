package exception;

/**
 * Chamada quando atributo � igual a "" ou null. 
 *
 */
public class AtributoInvalidoException extends Exception{
	
	public AtributoInvalidoException(){
		super("Atributo inv�lido");
	}

}
