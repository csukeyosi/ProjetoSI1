package exception;

/**
 * Chamada quando a sessao pesquisada nao existe no sistema. 
 *
 */
public class SessaoInexistenteException extends Exception{


	public SessaoInexistenteException(){
		super("Sessão inexistente");
	}
}
