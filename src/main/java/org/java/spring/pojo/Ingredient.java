package org.java.spring.pojo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Ingredient {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToMany(mappedBy = "ingredients")
	private List<Pizza> pizzas;
	
	@NotBlank
	@Size(min = 3 , max = 100)
	private String name;
	
	private boolean deleted = false;
	
	public Ingredient() {}

	public Ingredient(String name) {
		setName(name);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public List<Pizza> getPizzas() {
		return pizzas;
	}
	
	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}
	
	public void addPizza(Pizza pizza) {
		this.pizzas.add(pizza);
	}
	
	public void removePizza(Pizza pizza) {
		this.pizzas.remove(pizza);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	private String getInfo() {
		return "Id: " + getId() + ";"
			+ "\n" + "Nome: " + getName() + ";";
	}
	
	@Override
	public String toString() {
		return getInfo();
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Ingredient)) return false;
		
		Ingredient iObj = (Ingredient) obj;
		
		return getId() == iObj.getId();
	}
	
	@Override
	public int hashCode() {	
		return getId();
	}
}
