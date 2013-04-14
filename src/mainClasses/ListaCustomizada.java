package mainclasses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Representacao da listas personalizadas que o usuario pode criar.
 * 
 */
public class ListaCustomizada {

	private String nome;
	private String id;
	private Set<Usuario> usuarios;
	private List<Som> sons;

	public ListaCustomizada(String nomeLista) {
		this.nome = nomeLista;
		this.id = "ID" + nomeLista;
		this.usuarios = new HashSet<Usuario>();
		this.sons = new ArrayList<Som>();
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nomeLista) {
		this.nome = nomeLista;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String idLista) {
		this.id = idLista;
	}

	public List<Som> getSons() {
		return this.sons;
	}

	public Set<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public boolean addUsuario(Usuario usuario) {
		if (!usuarios.contains(usuario)) {
			usuarios.add(usuario);
			addSons(usuario);
			return true;
		}
		return false;
	}

	/**
	 * Adiciona os sons do novo usuario nos sons da lista, organizada em ordem
	 * cronologica.
	 * 
	 * @param usuario
	 *            Usuario cujos sons serao adicinados.
	 */
	private void addSons(Usuario usuario) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (sons.isEmpty()) {
			sons.addAll(usuario.getPerfilMusical());

		} else {

			int indiceInicial = 0;

			for (Som som : usuario.getPerfilMusical()) {
				int qtdeSons = sons.size();

				for (int i = indiceInicial; i < qtdeSons; i++) {
					Date data1 = null;
					Date data2 = null;
					try {
						data1 = (Date) sdf.parse(som.getData());
						data2 = (Date) sdf.parse(sons.get(i).getData());

					} catch (ParseException e) {
						e.printStackTrace();
					}

					if (sons.size() == 1) {
						if ((data1.compareTo(data2) >= 0)) {
							sons.add(som);
						} else {
							sons.add(indiceInicial, som);
						}
					}

					if (data1.compareTo(data2) < 0) {
						indiceInicial = sons.indexOf(sons.get(i));
						sons.add(indiceInicial, som);
						indiceInicial++;
						break;
					}

					if (indiceInicial >= sons.size()) {
						sons.add(som);
						indiceInicial++;
					}
				}
			}
		}
	}
}