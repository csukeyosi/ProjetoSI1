package exception;

public class EmailDuplicadoException extends Exception{
	
	public EmailDuplicadoException(){
		super("J� existe um usu�rio com este email");
	}

}
