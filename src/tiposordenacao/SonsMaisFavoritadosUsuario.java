package tiposordenacao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mainClasses.Som;
import mainClasses.Usuario;

public class SonsMaisFavoritadosUsuario implements OrdenaFeedPrincipal{

	@Override
	public List<Som> ordena(Usuario usuario) {
		List<Usuario> fontesDeSom = usuario.getFontesDeSom();
		List<Som> sonsFavoritos = usuario.getSonsFavoritos();
		List<Som> sonsOrdenados = new ArrayList<Som>();
		
		List<Integer> numeroFavoritos = new ArrayList<Integer>();
		
		int numFavorito;
		for(Usuario fonte : fontesDeSom){
			 numFavorito= 0;
			for(Som somDaFonte : fonte.getPerfilMusical()){
				if (sonsFavoritos.contains(somDaFonte)){
					numFavorito++;
				}
			}
			numeroFavoritos.add(fontesDeSom.indexOf(fonte), numFavorito);
		}
		
		int indexMaisFav = 0;
		for (int i = 0;  i < fontesDeSom.size(); i++ ){
			indexMaisFav = numeroFavoritos.indexOf(Collections.max(numeroFavoritos));
			numeroFavoritos.set(indexMaisFav, -1);
			
			for(Som som : fontesDeSom.get(indexMaisFav).getPerfilMusical()){
				sonsOrdenados.add(som);
			}
			
		}
		
		return sonsOrdenados;
	}
	
	

}
