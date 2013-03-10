package tiposordenacao;

import java.util.List;

import mainClasses.Som;
import mainClasses.Usuario;

/**
 * Interface dos tipos de ordenacao do feed principal do {@link Usuario}.
 */
public interface OrdenaFeedPrincipal {


	public List<Som> ordena(Usuario usuario);
}
