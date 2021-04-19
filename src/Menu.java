import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.*;

public class Menu implements Serializable {
	
	private final Map<Integer, Pizza> pizzas; 

	public Menu(String filePath) throws FileNotFoundException {
		this.pizzas = loadMenu(filePath);
	}

	public Collection<Pizza> getAllPizzas() { return this.pizzas.values(); }
	
	public Pizza getPizza(Integer id) {
		return this.pizzas.get(id);
	}
	
	@Override
	public String toString() {
		return pizzas.toString();
	}
	
	private static Map<Integer, Pizza> loadMenu(String filePath) throws FileNotFoundException {
		Map<Integer, Pizza> pizzas = new HashMap<>();
		// Open menu file 
		File menuFile = new File(filePath);
		
		// If one of these validity test fails, throw exception
		if (!menuFile.exists() || !menuFile.canRead() || !menuFile.isFile()) {
			throw new FileNotFoundException();
		}
		
		// Read menu with scanner
		Scanner menuScanner = new Scanner(menuFile);
		menuScanner.nextLine(); // Don't use the first line, it contains the TSV header;
				
		while (menuScanner.hasNextLine()) {
			String[] menuLine  = menuScanner.nextLine().split("\t");
			
			Integer id = parseInteger(menuLine[0]);
			String name = parseString(menuLine[1]);
			String description = parseString(menuLine[2]);
			Double price = parseDouble(menuLine[3]);
			
			
			Pizza pizza = new Pizza( id, name, description, price);
			pizzas.put(pizza.getId(), pizza);
		}
		
		menuScanner.close();
		
		return pizzas;
	}
	
	private static String parseString(String str) {
		return str.strip();
	}
	
	private static Double parseDouble(String str) {
		return Double.parseDouble(str.strip());
	}
	
	private static Integer parseInteger(String str) {
		return Integer.parseInt(str.strip());
	}
}
