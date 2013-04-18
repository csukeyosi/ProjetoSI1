package teste;

import java.util.List;

import junit.framework.Assert;

import mainclasses.Gerenciador;
import mainclasses.SistemaRecomendacao;
import mainclasses.Usuario;

import org.junit.Before;
import org.junit.Test;

/**
 * Teste da classe {@link SistemaRecomendacao}.
 * 
 */
public class TesteSistemaRecomendacao {

	private Usuario antonio;
	private Usuario bob;
	private Usuario maria;
	private Usuario barbara;
	private Usuario jose;
	private Gerenciador gerenciador;

	@Before
	public void setUp() {
		gerenciador = new Gerenciador();

		antonio = new Usuario("antonio123", "1234", "Antonio Silva",
				"antonio@email.com");
		bob = new Usuario("Bobs", "4567", "Bob Junior", "bobs@email.com");
		maria = new Usuario("mariadoida", "987", "Maria", "maria@sememail.com");
		barbara = new Usuario("barbarara", "huhu5", "Barbara",
				"barbara@sememail.com");
		jose = new Usuario("jose12", "senha0909", "José Silva", "jose@email");

		// Cria os usuarios no sistema
		gerenciador.criarUsuario(antonio.getLogin(), antonio.getSenha(),
				antonio.getNome(), antonio.getEmail());
		gerenciador.criarUsuario(bob.getLogin(), bob.getSenha(), bob.getNome(),
				bob.getEmail());
		gerenciador.criarUsuario(maria.getLogin(), maria.getSenha(),
				maria.getNome(), maria.getEmail());
		gerenciador.criarUsuario(barbara.getLogin(), barbara.getSenha(),
				barbara.getNome(), barbara.getEmail());
		gerenciador.criarUsuario(jose.getLogin(), jose.getSenha(),
				jose.getNome(), jose.getEmail());

		// Antonio posta sons
		gerenciador.postarSom(antonio.getLogin(), "link", "21/05/2013");
		gerenciador.postarSom(antonio.getLogin(), "link", "21/05/2013");
		// Bob posta sons
		gerenciador.postarSom(bob.getLogin(), "link", "21/05/2013");
		gerenciador.postarSom(bob.getLogin(), "link", "21/05/2013");
		// Maria posta sons
		gerenciador.postarSom(maria.getLogin(), "link", "21/05/2013");
		gerenciador.postarSom(maria.getLogin(), "link", "21/05/2013");
		gerenciador.postarSom(maria.getLogin(), "link", "21/05/2013");
		// Barbara posta sons
		gerenciador.postarSom(barbara.getLogin(), "link", "21/05/2013");

		// Antonio favorita sons
		gerenciador.favoritarSom(antonio.getLogin(), "som6ID");
		gerenciador.favoritarSom(antonio.getLogin(), "som8ID");
		gerenciador.favoritarSom(antonio.getLogin(), "som9ID");
		// Bob favorita sons
		gerenciador.favoritarSom(bob.getLogin(), "som8ID");
		gerenciador.favoritarSom(bob.getLogin(), "som6ID");
		// Barbara favorita som
		gerenciador.favoritarSom(barbara.getLogin(), "som6ID");
	}

	@Test
	public void testaRemocaoFontesInvalidas() {
		// antonio segue barbara
		gerenciador.seguirUsuario(antonio.getLogin(), barbara.getLogin());
		List<Usuario> recomendacoesAntonio = gerenciador
				.getFontesDeSonsRecomendadas(antonio.getLogin());

		Assert.assertFalse(recomendacoesAntonio.contains(antonio));
		Assert.assertFalse(recomendacoesAntonio.contains(barbara));
		Assert.assertFalse(recomendacoesAntonio.contains(jose));

		List<Usuario> recomendacoesJose = gerenciador
				.getFontesDeSonsRecomendadas(jose.getLogin());
		Assert.assertFalse(recomendacoesJose.contains(bob));
		Assert.assertFalse(recomendacoesJose.contains(jose));
	}

	@Test
	public void testaRecomendacaoSemEmpate() {
		// antonio segue jose
		gerenciador.seguirUsuario(antonio.getLogin(), jose.getLogin());
		// maria segue jose
		gerenciador.seguirUsuario(maria.getLogin(), jose.getLogin());
		// maria favorita sons
		gerenciador.favoritarSom(maria.getLogin(), "som8ID");
		gerenciador.favoritarSom(maria.getLogin(), "som9ID");

		Assert.assertEquals("{IDMariadoida, IDBobs, IDBarbarara}",
				toString(gerenciador.getFontesDeSonsRecomendadas(antonio
						.getLogin())));
	}

	@Test
	public void testaDesempateMaisFavoritadoPeloUsuario() {
		// antonio segue jose
		gerenciador.seguirUsuario(antonio.getLogin(), jose.getLogin());
		// maria segue jose
		gerenciador.seguirUsuario(maria.getLogin(), jose.getLogin());
		// maria favorita som
		gerenciador.favoritarSom(maria.getLogin(), "som8ID");

		Assert.assertEquals("{IDMariadoida, IDBobs, IDBarbarara}",
				toString(gerenciador.getFontesDeSonsRecomendadas(antonio
						.getLogin())));
	}

	@Test
	public void testaDesempateOrdemAlfabetica() {
		// antonio segue jose
		gerenciador.seguirUsuario(antonio.getLogin(), jose.getLogin());
		// antonio favorita som
		gerenciador.favoritarSom(antonio.getLogin(), "som3ID");
		// maria favorita som
		gerenciador.favoritarSom(maria.getLogin(), "som2ID");
		// barbara segue jose
		gerenciador.seguirUsuario(barbara.getLogin(), jose.getLogin());

		Assert.assertEquals("{IDBarbarara, IDBobs, IDMariadoida}",
				toString(gerenciador.getFontesDeSonsRecomendadas(antonio
						.getLogin())));
	}

	@Test
	public void testaRecomendacaoUsuarioSemFontesEFavoritos() {
		Assert.assertEquals("{IDMariadoida, IDBarbarara}", toString(gerenciador
				.getFontesDeSonsRecomendadas(jose.getLogin())));
	}

	private String toString(List<Usuario> recomendacoes) {
		String retorno = "{";
		for (int i = 0; i < recomendacoes.size(); i++) {
			retorno = retorno + recomendacoes.get(i).getId();
			if (i != (recomendacoes.size() - 1)) {
				retorno = retorno + ", ";
			}
		}
		retorno = retorno + "}";
		return retorno;
	}
}
