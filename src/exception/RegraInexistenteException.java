package exception;

/**
 * Chamada quando a regra de composicao nao existe no sistema.
 *
 */
public class RegraInexistenteException extends Exception{
	
	public RegraInexistenteException(){
		super("Regra de composição inexistente");
	}

}
