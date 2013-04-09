package util;

import java.util.ArrayList;
import java.util.List;

import mainclasses.NetMusicLive;
import mainclasses.Som;
import mainclasses.Usuario;

public class InterfaceWebAdapter{
	
	private NetMusicLive netMusicLive;
	public static InterfaceWebAdapter interfaceWebAdapter;
	
	private InterfaceWebAdapter(){
		netMusicLive = NetMusicLive.getInstance();
	}

	public static InterfaceWebAdapter getInstance(){
		if(interfaceWebAdapter == null){
			interfaceWebAdapter = new InterfaceWebAdapter();
		}
		return interfaceWebAdapter;
	}
	
	public void criaUsuario(String login,String senha,String nome,String email) throws Exception{
		this.netMusicLive.criarUsuario(login, senha, nome, email);
	}

	public boolean existeSessao(String login){
		return this.netMusicLive.existeSessao(login);
	}
	
	public String abrirSessao(String login, String password) throws Exception {
		return this.netMusicLive.abrirSessao(login, password);
	}

	public List<Som> getMainFeed(String idsessao) {
		try {
			return this.netMusicLive.getMainFeed(idsessao);			
		} catch (Exception e) {
			List<Som> mainFeed = new ArrayList<Som>();
			mainFeed.add(new Som("", "", ""));
			return mainFeed;
		}
	}
	
	public boolean verificaLoginESenha(String login,String senha){
		return this.netMusicLive.verificaLoginESenha(login, senha);
	}

	public List<String> getNomesFontesDeSons(String idsessao){
		List<String> nomeUsuarios = new ArrayList<String>();
		try{
			List<Usuario> usuariosQueSegue = this.netMusicLive.getFontesDeSons(idsessao);
			for(int i=0;i<usuariosQueSegue.size();i++){
				nomeUsuarios.add(usuariosQueSegue.get(i).getNome());
			}		
		}catch(Exception e){}
		
		return nomeUsuarios;
	}	
}