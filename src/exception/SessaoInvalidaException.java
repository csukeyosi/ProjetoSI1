package exception;

/**
 * Chamada quando a sessao pesquisada e null ou "". 
 *
 */
public class SessaoInvalidaException extends Exception{


	public SessaoInvalidaException(){
		super("Sess�o inv�lida");
	}

}
