package exception;

public class EmailDuplicadoException extends Exception{
	
	public EmailDuplicadoException(){
		super("Já existe um usuário com este email");
	}

}
