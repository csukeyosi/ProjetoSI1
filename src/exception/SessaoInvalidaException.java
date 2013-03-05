package exception;

public class SessaoInvalidaException extends Throwable{

	private static final long serialVersionUID = 1L;

	public SessaoInvalidaException(){
		super("Sessão inválida");
	}

}
