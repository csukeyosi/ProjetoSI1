package mainclasses;

public class Som {
	
	private String id, link, data;
	private int numeroDeFavotiros = 0;
	
	public Som(String id, String link, String data) {
		setLink(link);
		setData(data);
		setId(id);
	}

	public String getId() {
		return id;
	}

	private void setId(String id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	private void setLink(String link) {
		this.link = link;
	}

	public String getData() {
		return data;
	}

	private void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		
		return this.getLink();
	}
}