package exception;

/**
 * Chamada quando atributo é igual a "" ou null. 
 *
 */
public class AtributoInvalidoException extends Exception{
	
	public AtributoInvalidoException(){
		super("Atributo inválido");
	}

}
