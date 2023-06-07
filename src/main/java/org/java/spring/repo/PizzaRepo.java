package org.java.spring.repo;

import java.util.List;

import org.java.spring.pojo.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepo extends JpaRepository<Pizza, Integer> {
	public List<Pizza> findByNameContaining(String name);
	
	public List<Pizza> findByDeletedFalse();
	
	public List<Pizza> findByDeletedTrue();
	
	public List<Pizza> findByNameContainingAndDeletedFalse(String name);
	
	public List<Pizza> findByNameContainingAndDeletedTrue(String name);
}
