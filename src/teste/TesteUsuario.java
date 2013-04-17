package teste;

import mainclasses.Som;
import mainclasses.Usuario;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TesteUsuario {
	Usuario usuario1;
	Usuario usuario2;
	Usuario usuario3;
	Usuario usuario4;
	final int VAZIO = 0;

	@Before
	public void setUp() {
		usuario1 = new Usuario("antonio123", "1234", "Antonio Silva",
				"antonio@email.com");
		usuario2 = new Usuario("Bobs", "4567", "Bob Junior", "bobs@email.com");
		usuario3 = new Usuario("mariadoida", "987", "Maria",
				"maria@sememail.com");
		usuario4 = new Usuario("barbarara", "huhu5", "Barbara",
				"barbara@sememail.com");
	}

	@Test
	public void testaCriarUsuario() {
		Assert.assertEquals(VAZIO, usuario1.getPerfilMusical().size());
		Assert.assertEquals(VAZIO, usuario1.getFontesDeSom().size());
		Assert.assertEquals(VAZIO, usuario1.getListaDeSeguidores().size());
		Assert.assertEquals(VAZIO, usuario1.getVisaoDosSons().size());
		Assert.assertEquals(VAZIO, usuario1.getNumeroDeSeguidores());
		Assert.assertEquals(VAZIO, usuario1.getSonsFavoritos().size());

	}

	@Test
	public void testaOrdenaListaSeguidores() throws Exception {
		usuario1.addListaDeSeguidores(usuario3);
		usuario1.addListaDeSeguidores(usuario2);
		usuario1.addListaDeSeguidores(usuario4);

		Assert.assertEquals(usuario4.getNome(), usuario1.getListaDeSeguidores()
				.get(0).getNome());
		Assert.assertEquals(usuario2.getNome(), usuario1.getListaDeSeguidores()
				.get(1).getNome());
		Assert.assertEquals(usuario3.getNome(), usuario1.getListaDeSeguidores()
				.get(2).getNome());
	}

	@Test
	public void testaPostarSom() {
		Som som1 = new Som("IDSom1", "linklink", "06/05/2013");
		Som som2 = new Som("IDSom2", "linklink", "06/05/2016");
		usuario2.postarSom(som1);
		usuario2.postarSom(som2);
		Assert.assertEquals(2, usuario2.getPerfilMusical().size());
	}
}