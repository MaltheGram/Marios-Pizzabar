import java.io.Serializable;

public class Pizza implements Serializable {
	
	private final Integer id;
	private final String name;
	private final String description;
	private final Double price;

	public Pizza(Integer id, String name, String description, Double price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return String.format("src.Pizza {id=%d, name=%s, description=%s, price=%f}\n",
				this.getId(), this.getName(), this.getDescription(), this.getPrice() );
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}



}
