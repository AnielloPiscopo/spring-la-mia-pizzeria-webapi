package org.java.spring.api.controller;

import java.util.List;
import java.util.Optional;

import org.java.spring.pojo.Pizza;
import org.java.spring.pojo.SpecialOffer;
import org.java.spring.services.IngredientServ;
import org.java.spring.services.PizzaServ;
import org.java.spring.services.SpecialOfferServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class ApiPizzaController {
	
	/**
	 * 
	 * Change the boolean deleted value of a Pizza element 
	 */
	private void changeTheDeletedValue(Pizza pizza , boolean trashed) {
		pizza.setDeleted(trashed);
		
		List<SpecialOffer> specialOffers = pizza.getSpecialOffers();
		
		for(SpecialOffer so : specialOffers) {
			so.setDeleted(trashed);
			specialOfferServ.save(so);
		}
		
		pizzaServ.save(pizza);
	}
	
	@Autowired
	PizzaServ pizzaServ;
	
	@Autowired
	SpecialOfferServ specialOfferServ;
	
	@Autowired
	IngredientServ ingredientServ;
	
	/*
	 * 
	 * A Method that manages the index page by showing all the available Pizza elements in a table
	 */
	@GetMapping("/pizzas") 
	public List<Pizza> index(){
		return pizzaServ.findAllAvailablePizzasWithRel();
	}
	
	/*
	 * 
	 * A Method that manages the index page after a POST call for the filtering of the Pizza elements according to the string given in the form
	 */
	@PostMapping("/pizzas") 
	public List<Pizza> index(@RequestParam String name){
		return pizzaServ.filterByNameForAvailablePizzas(name);
	}
	
	/*
	 * 
	 * A Method that manages the show page by showing the single Pizza element details
	 */
	@GetMapping("/pizzas/{id}")
	public ResponseEntity<Pizza> show(@PathVariable("id") Integer id) {
		Optional<Pizza> optPizza = pizzaServ.findByIdWithRel(id);
		
		if (optPizza.isPresent()) {
			Pizza pizza = optPizza.get();
			return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
		}else {
	    	return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
    	}
	}
	
	/*
	 * 
	 * A Method that manages the store of a Pizza element in the database
	 */
	@PostMapping("/pizzas/store")
	public ResponseEntity<Pizza> store(
			@RequestBody Pizza pizza) {
		pizzaServ.save(pizza);
		
		return new ResponseEntity<Pizza>(HttpStatus.OK);	
	}
	
	/*
	 * 
	 * A Method that manages the update of a Pizza element in the database
	 */
	@PutMapping("/pizzas/update")
	public ResponseEntity<Pizza> update(
			@RequestBody Pizza pizza
		) {
		pizzaServ.save(pizza);
		
		return new ResponseEntity<Pizza>(HttpStatus.OK);	
	}
	
	/*
	 * 
	 * A Method that manages the soft-delete of a Pizza element
	 */
	@PostMapping("/pizzas/soft-delete/{id}")
	public ResponseEntity<Pizza> softDelete(@PathVariable("id") int id) {
		Optional<Pizza> optPizza = pizzaServ.findByIdWithRel(id);
		Pizza pizza = optPizza.get();
		changeTheDeletedValue(pizza, true);
		
		return new ResponseEntity<Pizza>(HttpStatus.OK);	
	}
	
	/*
	 * 
	 * A Method that manages the soft-delete of all Pizza elements
	 */
	@PostMapping("/pizzas/soft-delete-all")
	public ResponseEntity<Pizza> softDeleteAll() {
		List<Pizza> pizzas = pizzaServ.findAllAvailablePizzasWithRel();
		
		pizzas.stream().forEach(p -> changeTheDeletedValue(p, true));
		
		return new ResponseEntity<Pizza>(HttpStatus.OK);
	}
	
	/*
	 * 
	 * A Method that manages the refresh of a Pizza element
	 */
	@PostMapping("/pizzas/refresh/{id}")
	public ResponseEntity<Pizza> refresh(@PathVariable("id") int id) {
		Optional<Pizza> optPizza = pizzaServ.findById(id);
		Pizza pizza = optPizza.get();
		changeTheDeletedValue(pizza, false);
		
		return new ResponseEntity<Pizza>(HttpStatus.OK);
	}
	
	/*
	 * 
	 * A Method that manages the refresh of all Pizza elements
	 */
	@PostMapping("/pizzas/refresh-all")
	public ResponseEntity<Pizza> refreshAll() {
		List<Pizza> pizzas = pizzaServ.findAllTrashedPizzasWithRel();
		
		pizzas.stream().forEach(pizza -> changeTheDeletedValue(pizza, false));
		
		return new ResponseEntity<Pizza>(HttpStatus.OK);
	}
	
	/*
	 * 
	 * A Method that manages the delete of a Pizza element
	 */
	@DeleteMapping("/pizzas/{id}")
	public ResponseEntity<Pizza> delete(@PathVariable("id") int id) {
		Optional<Pizza> optPizza = pizzaServ.findByIdWithRel(id);
		Pizza pizza = optPizza.get();
		pizzaServ.delete(pizza);
		return new ResponseEntity<Pizza>(HttpStatus.OK);
	}
	
	/*
	 * 
	 * A Method that manages the delete of all Pizza elements
	 */
	@DeleteMapping("/pizzas")
	public ResponseEntity<Pizza> deleteAll() {
		List<Pizza> pizzas = pizzaServ.findAllTrashedPizzasWithRel();
		pizzaServ.deleteAll(pizzas);
		return new ResponseEntity<Pizza>(HttpStatus.OK);
	}
}
