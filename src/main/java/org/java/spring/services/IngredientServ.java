package org.java.spring.services;

import java.util.List;
import java.util.Optional;

import org.java.spring.pojo.Ingredient;
import org.java.spring.repo.IngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientServ {
	@Autowired
	private IngredientRepo repo;
	
	/**
	 * 
	 * Return all Ingredient elements in the database
	 * @return ingredients
	 */
	public List<Ingredient> findAll() {
		return repo.findAll();
	}

	/**
	 * 
	 * Return all Ingredient elements in the database with the boolean "deleted" set to false
	 * @return ingredients
	 */
	public List<Ingredient> findAllAvailableIngredients() {
		return repo.findByDeletedFalse();
	}
	
	/**
	 * 
	 * Return all Ingredient elements in the database with the boolean "deleted" set to true
	 * @return ingredients
	 */
	public List<Ingredient> findAllTrashedIngredients() {
		return repo.findByDeletedTrue();
	}

	/**
	 * 
	 * Return a Ingredient element with an identical id to the given ones
	 * @return specialOfferOpt
	 */
	public Optional<Ingredient> findById(int id) {
		return repo.findById(id);
	}
	
	/**
	 * 
	 * Save an element in the Ingredient table of the database
	 */
	public Ingredient save(Ingredient ingredient) {
		return repo.save(ingredient);
	}
	
	/**
	 * 
	 * Delete an element from the Ingredient table of the database
	 */
	public void delete(Ingredient ingredient) {
		repo.delete(ingredient);
	}
	
	/**
	 * 
	 * Delete all elements from the Ingredient table of the database
	 */
	public void deleteAll(List<Ingredient> ingredients) {
		repo.deleteAll(ingredients);
	}
}
