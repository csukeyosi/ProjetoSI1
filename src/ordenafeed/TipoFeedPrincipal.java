package ordenafeed;

/**
 * Regras de ordenação do feed principal do usuário.
 *
 */
public enum TipoFeedPrincipal {
	/** Primeiro os sons postados mais recentementes pelas fontes de som */
	SONS_RECENTES("PRIMEIRO OS SONS POSTADOS MAIS RECENTEMENTE PELAS FONTES DE SONS"),
	/** Primeiro os sons com mais favoritos */
	SONS_FAVORITADOS_SISTEMA("PRIMEIRO OS SONS COM MAIS FAVORITOS"),
	/** Primeiro os sons de fontes das quais o usuário mais favoritou no passado */
	SONS_FAVORITADOS_USUARIO("PRIMEIRO SONS DE FONTES DAS QUAIS FAVORITEI SONS NO PASSADO");
	
	private String valor;
	
	TipoFeedPrincipal(String valor){
		this.valor = valor;
	}
	
	public String toString(){
		return valor;
		
	}
}
