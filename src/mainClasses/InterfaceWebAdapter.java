package mainClasses;

public class InterfaceWebAdapter {
	
	private Sistema sistema;
	
	public void criaUsuario(String login,String senha,String nome,String email) throws Exception{
		sistema.criarUsuario(login, senha, nome, email);
	}
}
