package exception;

/**
 * Chamada quando o atributo procurado nao existe. 
 *
 */
public class AtributoInexistenteException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public AtributoInexistenteException(){
		super("Atributo inexistente");
	}

}
