package exception;

/**
 * Chamada quando o nome é igual a "" ou null. 
 *
 */
public class NomeInvalidoException extends Exception {
	
	public NomeInvalidoException(){
		super("Nome inválido");
	}

}
