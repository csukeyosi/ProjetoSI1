package teste;

import mainclasses.Usuario;

import org.junit.Before;

public class TesteSistemaRecomendacao {
	
	public class TesteUsuario {
		Usuario antonio;
		Usuario bob;
		Usuario maria;
		Usuario barbara;
		final int VAZIO = 0;

		@Before
		public void setUp() {
			antonio = new Usuario("antonio123", "1234", "Antonio Silva",
					"antonio@email.com");
			bob = new Usuario("Bobs", "4567", "Bob Junior", "bobs@email.com");
			maria = new Usuario("mariadoida", "987", "Maria",
					"maria@sememail.com");
			barbara = new Usuario("barbarara", "huhu5", "Barbara",
					"barbara@sememail.com");
		}

}
}
