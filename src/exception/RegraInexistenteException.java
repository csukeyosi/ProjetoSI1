package exception;

/**
 * Chamada quando a regra de composicao nao existe no sistema.
 *
 */
public class RegraInexistenteException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public RegraInexistenteException(){
		super("Regra de composição inexistente");
	}

}
