package org.java.spring.controllers;

import java.util.List;
import java.util.Optional;

import org.java.spring.pojo.Ingredient;
import org.java.spring.pojo.Pizza;
import org.java.spring.pojo.SpecialOffer;
import org.java.spring.services.IngredientServ;
import org.java.spring.services.PizzaServ;
import org.java.spring.services.SpecialOfferServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
	private static String pageTitle;
	
	/**
	 * 
	 * Return the HTML file to view in the Page(index/trash) and with the model add to this one a list of Pizza elements and the title for the page
	 * @return template
	 */
	private String getPizzas(List<Pizza> pizzas , String title , String template , Model model) {
		model.addAttribute("pizzas" , pizzas);
		model.addAttribute("title" , title);
		return template;
	}
	
	/**
	 * 
	 * Return a redirect to a specific web page and with the model add to this one a Pizza element , a list of eventual errors , a text for the title of the page , a text for the title of the button of the form
	 * @return templateToRedirect
	 */
	private String saveInDb(Pizza pizza , BindingResult br , String templateToEdit , String templateToRedirect , String title , String btnText , Model model) {
		if(br.hasErrors()) {
			model.addAttribute("errors" , br);
			modifyOrCreatePizza(pizza, title, btnText, templateToRedirect, model);
			return templateToEdit;
		}
		
		serv.save(pizza);
		return templateToRedirect;
	}
	
	/**
	 * 
	 * Return the HTML file to view in the Page(create/edit) and with the model add to this one a Pizza element, the title for the page , a text for the the button of the form
	 * @return template
	 */
	private String modifyOrCreatePizza(Pizza pizza , String title , String btnText , String template , Model model) {
		List<Ingredient> ingredients = ingredientServ.findAll();
		model.addAttribute("ingredients" , ingredients);
		model.addAttribute("btnText" , btnText);
		model.addAttribute("pizza", pizza);
		model.addAttribute("title" , title);
		return template;
	}
	
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
		
		serv.save(pizza);
	}
	
	@Autowired
	private PizzaServ serv;
	
	@Autowired
	private SpecialOfferServ specialOfferServ;
	
	@Autowired
	private IngredientServ ingredientServ;
	
	/*
	 * 
	 * A Method that manages the index page by showing all the available Pizza elements in a table
	 */
	@GetMapping("/")
	public String index(Model model ) {
		List<Pizza> pizzas = serv.findAllAvailablePizzasWithRel();
		return getPizzas(pizzas , "Lista pizze" , "view/pizza/index" , model);
	}
	
	/*
	 * 
	 * A Method that manages the index page after a POST call for the filtering of the Pizza elements according to the string given in the form
	 */
	@PostMapping("/")
	public String index(Model model , @RequestParam(name = "name") String name) {
		List<Pizza> pizzas = serv.filterByNameForAvailablePizzasWithRel(name);
		return getPizzas(pizzas , "Lista pizze" , "view/pizza/index" , model);
	}
	
	/*
	 * 
	 * A Method that manages the show page by showing the single Pizza element details
	 */
	@GetMapping("/{id}")
	public String show(Model model , @PathVariable("id") int id) {
		Optional<Pizza> optPizza = serv.findByIdWithRel(id);
		Pizza pizza = optPizza.get();
		pageTitle = "Pizza " + pizza.getName();
		model.addAttribute("pizza" , pizza);
		model.addAttribute("title" , pageTitle);
		return "view/pizza/show";
	}
	
	/*
	 * 
	 * A Method that manages the create page by showing the necessary form to create a new Pizza element
	 */
	@GetMapping("/create")
	public String create(Model model) {
		return modifyOrCreatePizza(new Pizza() , "Creazione pizza" , "Aggiungi alla lista la pizza" , "view/pizza/create" , model);
	}
	
	/*
	 * 
	 * A Method that manages the store of a Pizza element in the database
	 */
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("pizza") Pizza pizza , BindingResult br , Model model) {
		return saveInDb(pizza, br , "view/pizza/create" ,  "redirect:/pizzas/" , "Creazione pizza" , "Aggiungi alla lista la pizza" , model);
	}
	
	/*
	 * 
	 * A Method that manages the edit page by showing the necessary form to edit the Pizza element
	 */
	@GetMapping("/edit/{id}")
	public String edit(Model model , @PathVariable("id") int id) {
		Optional<Pizza> optPizza = serv.findByIdWithRel(id);
		Pizza pizza = optPizza.get();
		pageTitle = "Modifica la pizza: " + pizza.getName();
		return modifyOrCreatePizza(pizza , pageTitle , "Modifica elemento" , "view/pizza/edit" , model);
	}
	
	/*
	 * 
	 * A Method that manages the update of a Pizza element in the database
	 */
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("pizza") Pizza pizza , BindingResult br , Model model) {
		pageTitle = "Modifica la pizza: " + pizza.getName();
		return saveInDb(pizza, br , "view/pizza/edit" , "redirect:/pizzas/" + pizza.getId() , pageTitle , "Modifica elemento" , model);
	}
	
	/*
	 * 
	 * A Method that manages the trash page by showing all the trashed Pizza elements in a table
	 */
	@GetMapping("/trash")
	public String trash(Model model ) {
		List<Pizza> pizzas = serv.findAllTrashedPizzasWithRel();
		return getPizzas(pizzas , "Lista pizze cestinate" , "view/pizza/trash" , model);
	}
	
	/*
	 * 
	 * A Method that manages the trash page after a POST call for the filtering of the Pizza elements according to the string given in the form
	 */
	@PostMapping("/trash")
	public String trash(Model model , @RequestParam(name = "name") String name) {
		List<Pizza> pizzas = serv.filterByNameForTrashedPizzasWithRel(name);
		return getPizzas(pizzas , "Lista pizze cestinate" , "view/pizza/trash" , model);
	}
	
	/*
	 * 
	 * A Method that manages the soft-delete of a Pizza element
	 */
	@PostMapping("/soft-delete/{id}")
	public String softDelete(@PathVariable("id") int id) {
		Optional<Pizza> optPizza = serv.findById(id);
		Pizza pizza = optPizza.get();
		changeTheDeletedValue(pizza, true);
		return "redirect:/pizzas/";
	}
	
	/*
	 * 
	 * A Method that manages the soft-delete of all Pizza elements
	 */
	@PostMapping("/soft-delete-all")
	public String softDeleteAll() {
		List<Pizza> pizzas = serv.findAllAvailablePizzasWithRel();
//		for(Pizza pizza : pizzas) {
//			changeTheDeletedValue(pizza, true);
//		}
		pizzas.stream().forEach(pizza -> changeTheDeletedValue(pizza, true));
		return "redirect:/pizzas/";
	}
	
	/*
	 * 
	 * A Method that manages the refresh of a Pizza element
	 */
	@PostMapping("/refresh/{id}")
	public String refresh(@PathVariable("id") int id) {
		Optional<Pizza> optPizza = serv.findById(id);
		Pizza pizza = optPizza.get();
		changeTheDeletedValue(pizza, false);
		return "redirect:/pizzas/trash";
	}
	
	/*
	 * 
	 * A Method that manages the refresh of all Pizza elements
	 */
	@PostMapping("/refresh-all")
	public String refreshAll() {
		List<Pizza> pizzas = serv.findAllTrashedPizzasWithRel();
//		for(Pizza pizza : pizzas) {
//			changeTheDeletedValue(pizza, false);
//		}
		pizzas.stream().forEach(pizza -> changeTheDeletedValue(pizza, false));
		return "redirect:/pizzas/trash";
	}
	
	/*
	 * 
	 * A Method that manages the delete of a Pizza element
	 */
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		Optional<Pizza> optPizza = serv.findByIdWithRel(id);
		Pizza pizza = optPizza.get();
		serv.delete(pizza);
		return "redirect:/pizzas/trash";
	}
	
	/*
	 * 
	 * A Method that manages the delete of all Pizza elements
	 */
	@PostMapping("/delete-all")
	public String deleteAll() {
		List<Pizza> pizzas = serv.findAllTrashedPizzasWithRel();
		serv.deleteAll(pizzas);
		return "redirect:/pizzas/trash";
	}
}
