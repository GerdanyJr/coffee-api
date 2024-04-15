package com.example.demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Category;
import com.example.demo.model.Coffee;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.CoffeeRepository;

@SpringBootApplication
public class CoffeeApi implements CommandLineRunner {

	private final CategoryRepository categoryRepository;
	private final CoffeeRepository coffeeRepository;
	private static Random random = new Random();

	public CoffeeApi(CategoryRepository categoryRepository, CoffeeRepository coffeeRepository) {
		this.categoryRepository = categoryRepository;
		this.coffeeRepository = coffeeRepository;

	}

	public static void main(String[] args) {
		SpringApplication.run(CoffeeApi.class, args);
	}

	private static List<Category> getCategories() {
		List<Category> categories = new ArrayList<>();
		String[] categoryName = {
				"Tradicional", "Extraforte", "Superior", "Gourmet", "Arábico", "Robusto", "Espresso", "Coado", "Gelado",
				"Aromatizado", "Com Leite", "Café Aromatizado", "Especial"
		};
		for (int i = 0; i < categoryName.length; i++) {
			categories.add(new Category(i, categoryName[i]));
		}
		return categories;
	}

	private static List<Coffee> getCoffees(List<Category> categoryList) {
		String[] coffeeNames = {
				"Expresso tradicional",
				"Expresso americano",
				"Expresso cremoso",
				"Macchiato",
				"Café com leite",
				"Cappuccino",
				"Latte",
				"Mocha",
				"Ristretto",
				"Affogato",
				"Flat white",
				"Doppio",
				"Café au lait",
				"Café bombón",
				"Café con panna",
				"Café com chantilly",
				"Irish coffee",
				"Café irlandês",
				"Café vienense",
				"Café vienense com chantilly",
				"Café turco",
				"Café árabe",
				"Café indiano",
				"Café brasileiro",
				"Café colombiano",
				"Café peruano",
				"Café cubano",
				"Café havaiano",
				"Café mexicano",
				"Café guatemalteco",
				"Café etíope",
				"Café kenyano",
				"Café tanzaniano",
				"Café ugandense",
				"Café zambiano",
				"Café ruandês",
				"Café sudanês",
				"Café nigeriano",
				"Café marroquino",
				"Café argelino",
				"Café tunisiano",
				"Café líbio",
				"Café egípcio",
				"Café saudita",
				"Café iraniano",
				"Café iraquiano",
				"Café sírio",
				"Café jordaniano"
		};
		List<Coffee> coffees = new ArrayList<>();
		for (int i = 0; i < coffeeNames.length; i++) {
			Coffee coffee = new Coffee();
			coffee.setId(i);
			coffee.setName(coffeeNames[i]);
			coffee.setPrice(BigDecimal.valueOf(random.nextDouble() * 100));
			coffee.setImage("/images/Image-" + (random.nextInt(14) + 1) + ".png");
			coffee.setDescription("Descrição do café " + coffeeNames[i]);
			Set<Category> categories = new HashSet<>();
			for (int j = 0; j < random.nextInt(4) + 1; j++) {
				Category category = categoryList.get(random.nextInt(12));
				categories.add(category);
			}
			coffee.setCategories(List.copyOf(categories));
			coffees.add(coffee);
		}
		return coffees;
	}

	@Override
	public void run(String... args) throws Exception {
		List<Category> categories = getCategories();
		categoryRepository.saveAll(categories);
		List<Coffee> coffees = getCoffees(categoryRepository.findAll());
		coffeeRepository.saveAll(coffees);
	}

}