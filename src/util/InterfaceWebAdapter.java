package util;

import java.util.ArrayList;
import java.util.List;

import mainClasses.Sistema;
import mainClasses.Som;
import mainClasses.Usuario;

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

	public boolean existeSessao(String login){
		return this.sistema.existeSessao(login);
	}
	
	public String abrirSessao(String login, String password) throws Exception {
		return this.sistema.abrirSessao(login, password);
	}

	public List<Som> getMainFeed(String idsessao) {
		try {
			return this.sistema.getMainFeed(idsessao);			
		} catch (Exception e) {
			List<Som> mainFeed = new ArrayList<Som>();
			mainFeed.add(new Som("", "", ""));
			return mainFeed;
		}
	}
	
	public boolean verificaLoginESenha(String login,String senha){
		return this.sistema.verificaLoginESenha(login, senha);
	}

	public List<String> getNomesFontesDeSons(String idsessao){
		List<String> nomeUsuarios = new ArrayList<String>();
		try{
			List<Usuario> usuariosQueSegue = this.sistema.getFontesDeSons(idsessao);
			for(int i=0;i<usuariosQueSegue.size();i++){
				nomeUsuarios.add(usuariosQueSegue.get(i).getNome());
			}		
		}catch(Exception e){}
		
		return nomeUsuarios;
	}	
}