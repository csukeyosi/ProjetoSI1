package exception;
/**
 * Chamada quando a sessao pesquisada e null ou "". 
 *
 */
public class SessaoInvalidaException extends Exception{

	private static final long serialVersionUID = 1L;

	public SessaoInvalidaException(){
		super("Sessão inválida");
	}

}
