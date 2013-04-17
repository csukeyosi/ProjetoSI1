package mainclasses;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Classe que representa o sistema de recomendacao da NetMusicLive.
 * 
 * Regras da recomendacao: O sistema recomenda fontes de sons ao usuario, de
 * acordo com quantidade de sons favoritados em comum + quantidade de fontes de
 * som em comum. As fontes recomendadas nao devem estar entre as fontes já
 * existentes. Caso o primeiro criterio resulte em empate se coloca na frente a
 * fonte que seja mais favoritado pelo usuario. Caso o empate persista, deve se
 * colocar em ordem alfabetica segundo o nome do usuario. Caso o usuario nao
 * tenha nenhuma fonte de som nem tenha nenhum favorito, o sistema deve
 * recomendar as fontes que mais tem sons favoritados. Usuarios sem favoritos
 * nem fontes de sons, nao devem ser recomendados.
 * 
 */
public class SistemaRecomendacao {
	/* Usuario que recebera a recomendacao */
	private Usuario usuario;
	/* Os usuarios que serao recomendados */
	private List<Usuario> usuariosRecomendados;

	public SistemaRecomendacao(List<Usuario> usuariosSistema, Usuario usuario) {
		this.usuariosRecomendados = new ArrayList<Usuario>();
		this.usuario = usuario;
		retirarUsuariosInvalidos(usuariosSistema);
	}

	/**
	 * Retorna os usuarios recomendados.
	 * 
	 * @return Lista de usuarios recomendados.
	 */
	public List<Usuario> getUsuariosRecomendados() {
		if (!this.usuariosRecomendados.isEmpty()) {
			// Usuario nao favoritou sons nem possui fontes
			if (this.usuario.getSonsFavoritos().isEmpty()
					&& this.usuario.getFontesDeSom().isEmpty()) {
				ordenarUsuariosSonsMaisFavoritados();
			} else { // Usuario favoritou algum som ou tem fontes
				ordenarUsuariosRecomendados();
			}
		}
		return this.usuariosRecomendados;
	}

	/**
	 * Retorna o numero de fontes em comum entre o usuario da recomendacao e o
	 * usuarioRecomendado passado como parametro.
	 * 
	 * @param usuarioRecomendado
	 *            Usuario que terá suas fontes de sons comparadas.
	 * @return Numero de fontes de sons em comum.
	 */
	public int getNumFontesEmComum(Usuario usuarioRecomendado) {
		return Sets.intersection(
				Sets.newHashSet(usuarioRecomendado.getFontesDeSom()),
				Sets.newHashSet(this.usuario.getFontesDeSom())).size();
	}

	/**
	 * Retorna o numero de sons favoritados em comum entre o usuario da
	 * recomendacao e o usuarioRecomendado passado como parametro.
	 * 
	 * @param usuarioRecomendado
	 *            Usuario que terá seus sons favoritados comparados.
	 * @return Numero de sons favoritados em comum.
	 */
	public int getNumFavoritosEmComum(Usuario usuarioRecomendado) {
		return Sets.intersection(
				Sets.newHashSet(usuarioRecomendado.getSonsFavoritos()),
				Sets.newHashSet(this.usuario.getSonsFavoritos())).size();
	}

	/**
	 * Retira os usuarios que nao podem ser recomendados. Ou seja, usuarios que
	 * nao tem favoritos e fontes de sons e o usuario da recomendacao. Caso o
	 * este nao tenha favoritos e fontes, tambem serao tirados das recomendacoes
	 * os usuarios que nao tem favoritos.
	 * 
	 * @param usuariosSistema
	 *            Todos os usuarios dos sistema.
	 */
	private void retirarUsuariosInvalidos(List<Usuario> usuariosSistema) {
		for (Usuario u : usuariosSistema) {
			// Usuario que recebera a recomendacao nao tem sons nem fontes
			if (this.usuario.getSonsFavoritos().isEmpty()
					&& this.usuario.getFontesDeSom().isEmpty()) {
				if (!(u.getNumSonsFavoritados() == 0)) {
					this.usuariosRecomendados.add(u);
				}
				continue;
			}
			// Usuario sem sons favoritos e sem fontes de sons
			if (u.getFontesDeSom().isEmpty() && u.getSonsFavoritos().isEmpty()) {
				continue;
			}

			// Não recomendar usuarios que ja sao fontes de sons
			if (!this.usuario.getFontesDeSom().contains(u)) {
				this.usuariosRecomendados.add(u);
			}
		}
		// Remover o proprio usuario dos recomendados
		this.usuariosRecomendados.remove(this.usuario);
	}

	/**
	 * Retorna a quantidade de sons que o usuario da recomendacao favoritou
	 * dentre os sons postados pelo usuarioRecomendado.
	 * 
	 * @param usuarioRecomendado
	 *            Usuario recomendado.
	 * @return Numero de favoritos.
	 */
	private int qtdSonsFavoritados(Usuario usuarioRecomendado) {
		if (usuarioRecomendado.getPerfilMusical().isEmpty()) {
			return 0;
		}
		return Sets.intersection(
				Sets.newHashSet(usuarioRecomendado.getPerfilMusical()),
				Sets.newHashSet(this.usuario.getSonsFavoritos())).size();
	}

	/**
	 * Retorna a afinidade entre o usuarioda recomendacao e o usuarioRecomendado
	 * passado como parametro. Afinidade = numero de fontes em comum + numero de
	 * sons favoritos em comum.
	 * 
	 * @param usuarioRecomendado
	 *            Usuario que sera comparado.
	 * @return A 'afinidade' entre os usuarios.
	 */
	private int getAfinidade(Usuario usuarioRecomendado) {
		return getNumFontesEmComum(usuarioRecomendado)
				+ getNumFavoritosEmComum(usuarioRecomendado);
	}

	/**
	 * Ordena os usuarios recomendados de acordo com o numero de favoritos que
	 * cada um recebeu do sistema.
	 */
	private void ordenarUsuariosSonsMaisFavoritados() {
		List<Usuario> auxLista = Lists.newArrayList(this.usuariosRecomendados);

		boolean houveTroca = true;

		while (houveTroca) {
			houveTroca = false;

			for (int i = 0; i < (auxLista.size() - 1); i++) {

				Usuario usuarioAtual = this.usuariosRecomendados.get(i);
				Usuario usuarioSeguinte = this.usuariosRecomendados.get(i + 1);

				if (usuarioAtual.getNumSonsFavoritados() == usuarioSeguinte
						.getNumSonsFavoritados()) {
					if (usuarioAtual.getNome().compareToIgnoreCase(
							usuarioSeguinte.getNome()) > 0) {
						this.usuariosRecomendados.set(1 + i, usuarioAtual);
						this.usuariosRecomendados.set(i, usuarioSeguinte);
						houveTroca = true;
					}

				} else {
					if (usuarioAtual.getNumSonsFavoritados() < usuarioSeguinte
							.getNumSonsFavoritados()) {
						this.usuariosRecomendados.set(1 + i, usuarioAtual);
						this.usuariosRecomendados.set(i, usuarioSeguinte);
						houveTroca = true;
					}
				}
			}
		}
	}

	/**
	 * Ordena os usuarios recomendados de acordo com a afinidade com o usuario
	 * da recomendacao. Caso haja empate, o mais favoritado pelo usuario da
	 * recomendacao tera preferencia. Se ainda assim houver empate, a ordem
	 * alfabetica prevalecera.
	 */
	private void ordenarUsuariosRecomendados() {
		List<Usuario> auxLista = Lists.newArrayList(this.usuariosRecomendados);

		boolean houveTroca = true;

		while (houveTroca) {
			houveTroca = false;

			for (int i = 0; i < (auxLista.size() - 1); i++) {

				Usuario usuarioAtual = this.usuariosRecomendados.get(i);
				Usuario usuarioSeguinte = this.usuariosRecomendados.get(i + 1);
				// Afinidades iguais
				if (getAfinidade(usuarioAtual) == getAfinidade(usuarioSeguinte)) {
					// Receberam mesmo numero de favoritos do usuario da recomendacao
					if (qtdSonsFavoritados(usuarioAtual) == qtdSonsFavoritados(usuarioSeguinte)) {
						// Coloca em ordem alfabetica
						if (usuarioAtual.getNome().compareToIgnoreCase(
								usuarioSeguinte.getNome()) > 0) {
							this.usuariosRecomendados.set(1 + i, usuarioAtual);
							this.usuariosRecomendados.set(i, usuarioSeguinte);
							houveTroca = true;
						}

					} else { // NAO receberam a mesma quantidade de favoritos do usuario da recomendacao
						// Coloca as quantidades de favoritos em ordem decrescente
						if (qtdSonsFavoritados(usuarioAtual) < qtdSonsFavoritados(usuarioSeguinte)) {
							this.usuariosRecomendados.set(1 + i, usuarioAtual);
							this.usuariosRecomendados.set(i, usuarioSeguinte);
							houveTroca = true;
						}
					}

				} else { // Nao possui afinidades iguais
					// Coloca as afinidades em ordem decrescente
					if (getAfinidade(usuarioAtual) < getAfinidade(usuarioSeguinte)) {
						this.usuariosRecomendados.set(1 + i, usuarioAtual);
						this.usuariosRecomendados.set(i, usuarioSeguinte);
						houveTroca = true;
					}
				}
			}
		}
	}
}