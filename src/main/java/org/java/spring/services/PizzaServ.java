package org.java.spring.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.java.spring.pojo.Pizza;
import org.java.spring.repo.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PizzaServ {
	@Autowired
	private  PizzaRepo repo;
	
	/**
	 *  
	 * Return a list of Pizza elements with its relations
	 * @return pizzasWithRelations
	 */
	private List<Pizza> getPizzasWithRelations(List<Pizza> pizzas) {
//		List<Pizza> pizzasWithRelations = new ArrayList<>();
//		
//		for(Pizza pizza : pizzas){
//			List<SpecialOffer> soList = pizza.getSpecialOffers();
//			Hibernate.initialize(soList);
//			pizzasWithRelations.add(pizza);
//		}
		
		List<Pizza> pizzasWithRelations = pizzas.stream()
			    .peek(p -> Hibernate.initialize(p.getSpecialOffers()))
			    .peek(p->Hibernate.initialize(p.getIngredients()))
			    .collect(Collectors.toList());
		
		return pizzasWithRelations;
	};
	
	/**
	 * 
	 * Return a single Pizza Optional container with its relations
	 * @return pizzaOpt
	 */
	private Optional<Pizza> getPizzaWithRelantions(Optional<Pizza> pizzaOpt){
		Hibernate.initialize(pizzaOpt.get().getSpecialOffers());
		return pizzaOpt;
	}
	
	/**
	 * 
	 * Return all Pizza elements in the database
	 * @return pizzas
	 */
	public List<Pizza> findAll() {
			return repo.findAll();
	}
	
	/**
	 * 
	 * Return all Pizza elements in the database with the boolean "deleted" set to false
	 * @return pizzas
	 */
	public List<Pizza> findAllAvailablePizzas() {
		return repo.findByDeletedFalse();
	}
	
	/**
	 * 
	 * Return all Pizza elements in the database with the boolean "deleted" set to true
	 * @return pizzas
	 */
	public List<Pizza> findAllTrashedPizzas() {
		return repo.findByDeletedTrue();
	}
	
	/**
	 * 
	 * Return a list of Pizza elements by filtering according to the string given in input
	 * @return pizzas
	 */
	public List<Pizza> filterByName(String name) {
		return repo.findByNameContaining(name);
	}
	
	/**
	 * 
	 * Return a list of Pizza elements with the boolean "deleted" set to false by filtering according to the string given in input
	 * @return pizzas
	 */
	public List<Pizza> filterByNameForAvailablePizzas(String name) {
		return repo.findByNameContainingAndDeletedFalse(name);
	}
	
	/**
	 * 
	 * Return a list of Pizza elements with the boolean "deleted" set to true by filtering according to the string given in input
	 * @return pizzas
	 */
	public List<Pizza> filterByNameForTrashedPizzas(String name) {
		return repo.findByNameContainingAndDeletedTrue(name);
	}
	
	/**
	 * 
	 * Return a Pizza element with an identical id to the given ones
	 * @return pizzaOpt
	 */
	public Optional<Pizza> findById(int id) {
		return repo.findById(id);
	}
	
	/**
	 * 
	 * Return all Pizza elements in the database with all its relations
	 * @return pizzas
	 */
	@Transactional
	public List<Pizza> findAllWithRel(){
		List<Pizza> pizzas = repo.findAll();
		return getPizzasWithRelations(pizzas);
	}
	
	/**
	 * 
	 * Return all Pizza elements in the database with the boolean "deleted" set to false with all its relations
	 * @return pizzas
	 */
	@Transactional
	public List<Pizza> findAllAvailablePizzasWithRel() {
		List<Pizza> pizzas = repo.findByDeletedFalse();
		return getPizzasWithRelations(pizzas);
	}
	
	/**
	 * 
	 * Return all Pizza elements in the database with the boolean "deleted" set to true with all its relations
	 * @return pizzas
	 */
	@Transactional
	public List<Pizza> findAllTrashedPizzasWithRel() {
		List<Pizza> pizzas = repo.findByDeletedTrue();
		return getPizzasWithRelations(pizzas);
	}
	
	/**
	 * 
	 * Return a list of Pizza elements by filtering according to the string given in input with all its relations
	 * @return pizzas
	 */
	@Transactional
	public List<Pizza> filterByNameWithRel(String name) {
		List<Pizza> pizzas = repo.findByNameContaining(name);
		return getPizzasWithRelations(pizzas);
	}
	
	/**
	 * 
	 * Return a list of Pizza elements with the boolean "deleted" set to false by filtering according to the string given in input with all its relations
	 * @return pizzas
	 */
	@Transactional
	public List<Pizza> filterByNameForAvailablePizzasWithRel(String name) {
		List<Pizza> pizzas = repo.findByNameContainingAndDeletedFalse(name);
		return getPizzasWithRelations(pizzas);
	}
	
	/**
	 * 
	 * Return a list of Pizza elements with the boolean "deleted" set to true by filtering according to the string given in input with all its relations
	 * @return pizzas
	 */
	@Transactional
	public List<Pizza> filterByNameForTrashedPizzasWithRel(String name) {
		List<Pizza> pizzas = repo.findByNameContainingAndDeletedTrue(name);
		return getPizzasWithRelations(pizzas);
	}
	
	/**
	 * 
	 * Return a Pizza element with an identical id to the given ones with all its relations
	 * @return pizzaOpt
	 */
	@Transactional
	public Optional<Pizza> findByIdWithRel(int id) {
		Optional<Pizza> pizzaOpt = repo.findById(id);
		return getPizzaWithRelantions(pizzaOpt);
	}
	
	/**
	 * 
	 * Save an element in the Pizza table of the database
	 */
	public void save(Pizza pizza) {
		repo.save(pizza);
	}
	
	/**
	 * 
	 * Delete an element from the Pizza table of the database
	 */
	public void delete(Pizza pizza) {
		repo.delete(pizza);
	}
	
	/**
	 * 
	 * Delete all elements from the Pizza table of the database
	 */
	public void deleteAll(List<Pizza> pizzas) {
		repo.deleteAll(pizzas);
	}
}
