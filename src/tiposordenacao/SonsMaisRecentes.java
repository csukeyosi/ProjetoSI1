package tiposordenacao;

import java.util.ArrayList;
import java.util.List;

import mainclasses.Som;
import mainclasses.Usuario;

/** 
 * Representa um tipo de ordenacao.
 * Deverao aparecer primeiro no feed principal do usuario apenas os sons    
 * postados pelas suas fontes de sons mais recentemente.
 * 
 */
public class SonsMaisRecentes implements OrdenaFeedPrincipal{

	@Override
	public List<Som> ordena(List<Usuario> fontesDeSom, List<Som> sonsFavoritos) {
		List<Usuario> fontes = fontesDeSom;
		List<Som> feedPrincipal = new ArrayList<Som>();
		
		for(int fonte = fontes.size() - 1; fonte >= 0; fonte--){
			for (Som som : fontes.get(fonte).getPerfilMusical()){
				feedPrincipal.add(som);
			}
		}
		return feedPrincipal;
	
	}
}
