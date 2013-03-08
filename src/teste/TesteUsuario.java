package teste;

import mainClasses.Usuario;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TesteUsuario {
	Usuario usuario1;
	Usuario usuario2;
	final int VAZIO = 0;
	
	@Before
	public void setUp(){
		usuario1 = new Usuario("antonio123", "1234", "Antonio Silva", "antonio@email.com");
		usuario2 = new Usuario("Bobs", "4567", "Bob Junior", "bobs@email.com");
		
		
	}
	
	@Test
	public void testaCriarUsuario(){
		Assert.assertEquals(VAZIO, usuario1.getPerfilMusical().size());
		Assert.assertEquals(VAZIO, usuario1.getFontesDeSom().size());
		Assert.assertEquals(VAZIO, usuario1.getListaDeSeguidores().size());
		Assert.assertEquals(VAZIO, usuario1.getVisaoDosSons().size());
		Assert.assertEquals(VAZIO, usuario1.getNumeroDeSeguidores());
		Assert.assertEquals(VAZIO, usuario1.getSonsFavoritos().size());		
		
	}
	
	@Test
	public void testaOrdenaListaSeguidores() throws Exception{
		usuario1.addListaDeSeguidores("IDMaria");
		usuario1.addListaDeSeguidores(usuario2.getId());
		usuario1.addListaDeSeguidores("IDBarbara");
		
		Assert.assertEquals("IDBarbara", usuario1.getListaDeSeguidores().get(0));
		Assert.assertEquals("IDBobs", usuario1.getListaDeSeguidores().get(1));
		Assert.assertEquals("IDMaria", usuario1.getListaDeSeguidores().get(2));
	}
	
	@Test
	public void testaPostarSom(){
		usuario2.postarSom("IDSom1");
		usuario2.postarSom("IDSom2");
		Assert.assertEquals(2, usuario2.getPerfilMusical().size());
	}
}
