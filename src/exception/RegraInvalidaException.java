package exception;

/**
 * Chamada quando a regra nao existe no sistema.
 *
 */
public class RegraInvalidaException extends Exception{
	
	public RegraInvalidaException(){
		super("Regra de composição inválida");
	}

}
