package org.java.spring.repo;

import java.util.List;

import org.java.spring.pojo.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepo extends JpaRepository<Ingredient, Integer> {
    public List<Ingredient> findByDeletedFalse();
	
	public List<Ingredient> findByDeletedTrue();
}
