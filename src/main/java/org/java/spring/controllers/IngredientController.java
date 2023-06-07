package org.java.spring.controllers;

import java.util.List;
import java.util.Optional;

import org.java.spring.pojo.Ingredient;
import org.java.spring.pojo.Pizza;
import org.java.spring.services.IngredientServ;
import org.java.spring.services.PizzaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
	/**
	 * 
	 * Return the HTML file to view in the Page(index/trash) and with the model add to this one a list of Ingredient elements and the title for the page
	 * @return template
	 */
	private String getIngredients(List<Ingredient> ingredients , String title , String template , Model model) {
		model.addAttribute("ingredients" , ingredients);
		model.addAttribute("title" , title);
		return template;
	}
	
	/**
	 * 
	 * Return a redirect to a specific web page and with the model add to this one a Ingredient element , a list of eventual errors , a text for the title of the page , a text for the title of the button of the form
	 * @return templateToRedirect
	 */
	private String saveInDb(Ingredient ingredient , BindingResult br , String templateToEdit , String templateToRedirect , String title , String btnText , Model model) {
		if(br.hasErrors()) {
			model.addAttribute("errors" , br);
			modifyOrCreateIngredient(ingredient, title, btnText, templateToRedirect, model);
			return templateToEdit;
		}
		
		serv.save(ingredient);
		return templateToRedirect;
	}
	
	/**
	 * 
	 * Return the HTML file to view in the Page(create/edit) and with the model add to this one a Ingredient element, the title for the page , a text for the the button of the form
	 * @return template
	 */
	private String modifyOrCreateIngredient(Ingredient ingredient , String title , String btnText , String template , Model model) {
		model.addAttribute("btnText" , btnText);
		model.addAttribute("ingredient", ingredient);
		model.addAttribute("title" , title);
		return template;
	}
	
	/**
	 * 
	 * Change the boolean deleted value of a Ingredient element 
	 */
	private void changeTheDeletedValue(Ingredient ingredient , boolean trashed) {
		ingredient.setDeleted(trashed);
		serv.save(ingredient);
	}
	
	/**
	 * 
	 * Remove a Ingredient element from all its Pizza elements 
	 */
	private void removeIngredientFromPizzas(Ingredient i) {
		for(Pizza p : i.getPizzas()) {
			p.removeIngredient(i);
			pizzaServ.save(p);
		}
	}
	
	@Autowired
	private IngredientServ serv;
	
	@Autowired
	private PizzaServ pizzaServ;
	
	/*
	 * 
	 * A Method that manages the index page by showing all the available Ingredient elements in a table
	 */
	@GetMapping("/")
	public String index(Model model ) {
		List<Ingredient> ingredients = serv.findAllAvailableIngredients();
		return getIngredients(ingredients , "Lista ingredienti" , "view/ingredient/index" , model);
	}
	
	/*
	 * 
	 * A Method that manages the store of a Ingredient element in the database
	 */
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("ingredient") Ingredient ingredient , BindingResult br , Model model) {
		return saveInDb(ingredient, br , "view/ingredient/create" ,  "redirect:/ingredients/" , "Creazione offerta" , "Aggiungi alla lista l'offerta" , model);
	}
	
	/*
	 * 
	 * A Method that manages the trash page by showing all the trashed Ingredient elements in a table
	 */
	@GetMapping("/trash")
	public String trash(Model model ) {
		List<Ingredient> ingredients = serv.findAllTrashedIngredients();
		return getIngredients(ingredients , "Lista offerte cestinate" , "view/ingredient/trash" , model);
	}
	
	/*
	 * 
	 * A Method that manages the soft-delete of a Ingredient element
	 */
	@PostMapping("/soft-delete/{id}")
	public String softDelete(@PathVariable("id") int id) {
		Optional<Ingredient> optIngredient = serv.findById(id);
		Ingredient ingredient = optIngredient.get();
		changeTheDeletedValue(ingredient, true);
		return "redirect:/ingredients/";
	}
	
	/*
	 * 
	 * A Method that manages the soft-delete of all Ingredient elements
	 */
	@PostMapping("/soft-delete-all")
	public String softDeleteAll() {
		List<Ingredient> ingredients = serv.findAllAvailableIngredients();
//		for(Ingredient i : ingredients) {
//			changeTheDeletedValue(i,true);
//		}
		ingredients.stream().forEach(i->changeTheDeletedValue(i, true));
		return "redirect:/ingredients/";
	}
	
	/*
	 * 
	 * A Method that manages the refresh of a Ingredient element
	 */
	@PostMapping("/refresh/{id}")
	public String refresh(@PathVariable("id") int id) {
		Optional<Ingredient> optIngredient = serv.findById(id);
		Ingredient ingredient = optIngredient.get();
		changeTheDeletedValue(ingredient, false);
		return "redirect:/ingredients/trash";
	}
	
	/*
	 * 
	 * A Method that manages the refresh of all Ingredient elements
	 */
	@PostMapping("/refresh-all")
	public String refreshAll() {
		List<Ingredient> ingredients = serv.findAllTrashedIngredients();
//		for(Ingredient i : ingredients) {
	//		changeTheDeletedValue(i,false);
	//	}
		ingredients.stream().forEach(i->changeTheDeletedValue(i, false));
		return "redirect:/ingredients/trash";
	}
	
	/*
	 * 
	 * A Method that manages the delete of a Ingredient element
	 */
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		Optional<Ingredient> optIngredient = serv.findById(id);
		Ingredient ingredient = optIngredient.get();
		
		removeIngredientFromPizzas(ingredient);
		
		serv.delete(ingredient);
		return "redirect:/ingredients/trash";
	}
	
	/*
	 * 
	 * A Method that manages the delete of all Ingredient elements
	 */
	@PostMapping("/delete-all")
	public String deleteAll() {
		List<Ingredient> ingredients = serv.findAllTrashedIngredients();
		ingredients.forEach(i->removeIngredientFromPizzas(i));
		serv.deleteAll(ingredients);
		return "redirect:/ingredients/trash";
	}
}
