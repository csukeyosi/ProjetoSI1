package mainClasses;

import java.util.ArrayList;
import java.util.List;

public class InterfaceWebAdapter{
	
	private Sistema sistema;
	public static InterfaceWebAdapter interfaceWebAdapter;
	
	private InterfaceWebAdapter(){
		sistema = Sistema.getInstance();
	}

	public static InterfaceWebAdapter getInstance(){
		if(interfaceWebAdapter == null){
			interfaceWebAdapter = new InterfaceWebAdapter();
		}
		return interfaceWebAdapter;
	}
	
	public void criaUsuario(String login,String senha,String nome,String email) throws Exception{
		this.sistema.criarUsuario(login, senha, nome, email);
	}

	public void abrirSessao(String login, String password) throws Exception {
		this.sistema.abrirSessao(login, password);
	}
	
	public List<String> getNomesFontesDeSons(String idsessao) throws Exception{
		List<String> idUsuarios = this.sistema.getFontesDeSons(idsessao);
		List<Usuario> usuariosQueSegue = this.sistema.retornaUsersPelosIDs(idUsuarios);
		List<String> nomeUsuarios = new ArrayList<String>();
		for(int i=0;i<usuariosQueSegue.size();i++){
			nomeUsuarios.add(usuariosQueSegue.get(i).getNome());
		}
		return nomeUsuarios;
	}
	
}
