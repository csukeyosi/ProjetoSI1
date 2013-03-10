package exception;

/**
 * Chamada quando a sessao pesquisada nao existe no sistema. 
 *
 */
public class SessaoInexistenteException extends Exception{

	private static final long serialVersionUID = 1L;

	public SessaoInexistenteException(){
		super("Sessão inexistente");
	}
}
