package org.java.spring;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.java.spring.auth.pojo.Role;
import org.java.spring.auth.pojo.User;
import org.java.spring.auth.services.RoleServ;
import org.java.spring.auth.services.UserServ;
import org.java.spring.pojo.Ingredient;
import org.java.spring.pojo.Pizza;
import org.java.spring.pojo.SpecialOffer;
import org.java.spring.services.IngredientServ;
import org.java.spring.services.PizzaServ;
import org.java.spring.services.SpecialOfferServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.github.javafaker.Faker;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {
	@Autowired
	private UserServ userServ;
	
	@Autowired
	private RoleServ roleServ;
	
	@Autowired
	private PizzaServ pizzaServ;
	
	@Autowired
	private SpecialOfferServ specialOfferServ;
	
	@Autowired
	private IngredientServ ingredientServ;
	
	private static List<Pizza> pizzasList = new ArrayList<>();
	private static List<Ingredient> ingredientsList = new ArrayList<>();

	public static void main(String[] args){
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	public void run(String... args) throws Exception{
		createUsersAndRoles();
		addToDb();
	}
	
	private void createUsersAndRoles(){
		Role roleUser = new Role("USER");
		Role roleAdmin = new Role("ADMIN");
		
		roleServ.save(roleUser);
		roleServ.save(roleAdmin);
		
		final String pws = new BCryptPasswordEncoder().encode("pws");
		
		User userUser = new User("user", pws, roleUser);
		User userAdmin = new User("admin", pws, roleAdmin);
				
		userServ.save(userUser);
		userServ.save(userAdmin);
	}
	
	private List<Ingredient> createIngredientsList(){
		Faker faker = new Faker();
		int min = 10;
		int max = 50;
		int rndNumber = faker.number().numberBetween(min,max);
		
		for(int i=1 ; i<rndNumber ; i++) {
			String rndName = String.join(", " , faker.lorem().words());
			Ingredient rndIngredient = new Ingredient(rndName);
			ingredientsList.add(rndIngredient);
		}
		
		return ingredientsList;
	}
	
	private List<Pizza> createPizzasList(List<Ingredient> ingredients) {
		Faker faker = new Faker();
		int min = 10;
		int max = 100;
		int rndNumber = faker.number().numberBetween(min,max);
		int ingredientsSize = ingredients.size();
		
		for(int i=1 ; i<rndNumber ; i++) {
			String rndName = String.join(", " , faker.lorem().words());
			String rndDescription = faker.lorem().sentence(min);
			String rndImgUrl = faker.internet().image();
			float rndPrice = (float) faker.number().randomDouble(2, 1, max);
			int rndIngredientsIndex = faker.number().numberBetween(0, ingredientsSize);
			Ingredient selectedIngredient = ingredients.get(rndIngredientsIndex);
			Pizza rndPizza = new Pizza(rndName , rndDescription , rndImgUrl , rndPrice , selectedIngredient);
			pizzasList.add(rndPizza);
		}
		
		return pizzasList;
	}
	
	private List<SpecialOffer> createSpecialOffersList(List<Pizza> pizzas){
		Faker faker = new Faker();
		int min = 1;
		int max = 30;
		int minDiscount = 5;
		int maxDiscount = 30;
		int rndNumber = faker.number().numberBetween(min,max);
		int pizzasSize = pizzas.size();
		List<SpecialOffer> specialOffersList = new ArrayList<>();
		
		for(int i=1 ; i<rndNumber ; i++) {
			String rndTitle = String.join(", " , faker.lorem().words());
			LocalDate rndStartDate = LocalDate.parse("2007-12-03");
			LocalDate rndEndDate = LocalDate.parse("2045-12-03");
			int rndDiscount = faker.number().numberBetween(minDiscount, maxDiscount);
			int rndPizzasIndex = faker.number().numberBetween(0, pizzasSize);
			Pizza selectedPizza = pizzas.get(rndPizzasIndex);
			SpecialOffer rndSpecialOffer = new SpecialOffer(selectedPizza , rndTitle , rndStartDate , rndEndDate , rndDiscount);
			specialOffersList.add(rndSpecialOffer);
		}
		
		return specialOffersList;
	}
	
	private void addToDb() {
		for(Ingredient i : createIngredientsList()) {
			ingredientServ.save(i);
		}
		
		for(Pizza p : createPizzasList(ingredientsList)) {
			pizzaServ.save(p);
		}
		
		for(SpecialOffer so : createSpecialOffersList(pizzasList)) {
			specialOfferServ.save(so);
		}
	}

}
