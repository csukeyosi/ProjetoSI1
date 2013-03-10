package exception;
/**
 * Chamada quando a regra de composicao e igual a null ou "".
 *
 */
public class RegraInvalidaException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public RegraInvalidaException(){
		super("Regra de composição inválida");
	}

}
