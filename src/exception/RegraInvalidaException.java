package exception;

/**
 * Chamada quando a regra de composicao e igual a null ou "".
 *
 */
public class RegraInvalidaException extends Exception{
	
	public RegraInvalidaException(){
		super("Regra de composição inválida");
	}

}
