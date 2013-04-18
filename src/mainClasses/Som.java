package mainclasses;

import com.google.common.base.Objects;

/**
 * Classe responsavel pela representacao dos sons(musicas postadas pelos usuarios) no sistema. 
 */
public class Som {

	private String id, link, data;
	/* Numero de usuarios que favoritaram este som */
	private int numeroDeFavotiros = 0;

	public Som(String id, String link, String data) {
		setLink(link);
		setData(data);
		setId(id);
	}

	public int getNumeroDeFavoritos(){
		return this.numeroDeFavotiros;
	}

	/**
	 * Incrementa em 1 o numero de usuarios que favoritaram o som.
	 */
	public void incrementaFavoritos(){
		this.numeroDeFavotiros++;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return this.getLink();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if ((obj == null) || !(obj instanceof Som)) {
			return false;
		}

		Som other = (Som) obj;
		return Objects.equal(this.id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.id);
	}
}