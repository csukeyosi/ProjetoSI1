package ordenafeed;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.sun.xml.internal.fastinfoset.algorithm.IntEncodingAlgorithm;

import mainclasses.Som;
import mainclasses.Usuario;

public class SonsMaisFavoritadosSistema implements OrdenaFeedPrincipal{
	
	@Override
	public List<Som> ordena(Usuario usuario){
		OrdenaFeedPrincipal ordenacaoAux = new SonsMaisRecentes();
		List<Som> listaAux = ordenacaoAux.ordena(usuario);
		Collections.reverse(listaAux);
		
		List<Som> favoritosDecres = new ArrayList<Som>();
		List<Integer> numeroFavoritos = new ArrayList<Integer>();
		
		for (Som som :listaAux){
			numeroFavoritos.add(som.getNumeroDeFavoritos());
		}
		
		int indexMaisFav = 0;
		for (int i = 0;  i < listaAux.size(); i++ ){
			indexMaisFav = numeroFavoritos.indexOf(Collections.max(numeroFavoritos));
			numeroFavoritos.set(indexMaisFav, -1);
			favoritosDecres.add(listaAux.get(indexMaisFav));
		}
		Collections.reverse(favoritosDecres);
		return favoritosDecres;
		
	}

}
