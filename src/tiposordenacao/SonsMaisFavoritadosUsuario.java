package tiposordenacao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mainclasses.Som;
import mainclasses.Usuario;

/**
 * Representa um tipo de ordenacao.
 * Deverao aparecer primeiro os sons postados pelas suas fontes, os quais
 * estarao ordenados segundo o numero de sons favoritados pelo proprio usuario de cada
 * fonte de sons isoladamente. Quando duas fontes tem o mesmo numero de favoritos,
 * a ordem de entrada da lista de quem o usuario estah seguindo eh levada em consideracao
 * aqui para reordenar o feed principal, de maneira que os sons do usuario que o proprio usuario
 * comecou a seguir antes de seguir outro, aparecerao primeiro que os de
 *
 */
public class SonsMaisFavoritadosUsuario implements OrdenaFeedPrincipal{


	@Override
	public List<Som> ordena(List<Usuario> fontesDeSom, List<Som> sonsFavoritos) {
		List<Usuario> fontes = fontesDeSom;
		List<Som> favoritos = sonsFavoritos;
		List<Som> sonsOrdenados = new ArrayList<Som>();
		
		List<Integer> numeroFavoritos = new ArrayList<Integer>();
		
		int numFavorito;
		for(Usuario fonte : fontes){
			 numFavorito= 0;
			for(Som somDaFonte : fonte.getPerfilMusical()){
				if (favoritos.contains(somDaFonte)){
					numFavorito++;
				}
			}
			numeroFavoritos.add(fontes.indexOf(fonte), numFavorito);
		}
		
		int indexMaisFav = 0;
		for (int i = 0;  i < fontes.size(); i++ ){
			indexMaisFav = numeroFavoritos.indexOf(Collections.max(numeroFavoritos));
			numeroFavoritos.set(indexMaisFav, -1);
			
			for(Som som : fontes.get(indexMaisFav).getPerfilMusical()){
				sonsOrdenados.add(som);
			}
			
		}
		
		return sonsOrdenados;
	}
	
	

}
