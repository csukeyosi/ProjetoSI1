package exception;

/**
 * Chamada quando o atributo procurado nao existe. 
 *
 */
public class AtributoInexistenteException extends Exception{
	
	public AtributoInexistenteException(){
		super("Atributo inexistente");
	}

}
