package exception;

/**
 * Chamada quando o nome � igual a "" ou null. 
 *
 */
public class NomeInvalidoException extends Exception {
	
	public NomeInvalidoException(){
		super("Nome inv�lido");
	}

}
