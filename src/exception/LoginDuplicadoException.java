package exception;

public class LoginDuplicadoException extends Exception{
	
	public LoginDuplicadoException(){
		super("J� existe um usu�rio com este login");
	}

}
