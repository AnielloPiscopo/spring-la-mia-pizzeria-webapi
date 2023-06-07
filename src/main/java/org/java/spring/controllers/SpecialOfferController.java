package org.java.spring.controllers;

import java.util.List;
import java.util.Optional;

import org.java.spring.pojo.Pizza;
import org.java.spring.pojo.SpecialOffer;
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

import jakarta.validation.Valid;

@Controller
@RequestMapping("/special-offers")
public class SpecialOfferController {
	private static String pageTitle;
	
	/**
	 * 
	 * Return the HTML file to view in the Page(index/trash) and with the model add to this one a list of SpecialOffer elements and the title for the page
	 * @return template
	 */
	private String getSpecialOffers(List<SpecialOffer> specialOffers , String title , String template , Model model) {
		model.addAttribute("specialOffers" , specialOffers);
		model.addAttribute("title" , title);
		return template;
	}
	
	/**
	 * 
	 * Return a redirect to a specific web page and with the model add to this one a SpecialOffer element , a list of eventual errors , a text for the title of the page , a text for the title of the button of the form
	 * @return templateToRedirect
	 */
	private String saveInDb(SpecialOffer specialOffer , List<Pizza> pizzas , BindingResult br , String templateToEdit , String templateToRedirect , String title , String btnText , Model model) {
		if(br.hasErrors()) {
			model.addAttribute("errors" , br);
			modifyOrCreateSpecialOffer(specialOffer, pizzas , title, btnText, templateToRedirect, model);
			return templateToEdit;
		}
		
		serv.save(specialOffer);
		return templateToRedirect;
	}
	
	/**
	 * 
	 * Return the HTML file to view in the Page(create/edit) and with the model add to this one a SpecialOffer element, the title for the page , a text for the the button of the form
	 * @return template
	 */
	private String modifyOrCreateSpecialOffer(SpecialOffer specialOffer , List<Pizza> pizzas , String title , String btnText , String template , Model model) {
		model.addAttribute("btnText" , btnText);
		model.addAttribute("specialOffer", specialOffer);
		model.addAttribute("pizzas" , pizzas);
		model.addAttribute("title" , title);
		return template;
	}
	
	/**
	 * 
	 * Change the boolean deleted value of a SpecialOffer element 
	 */
	private void changeTheDeletedValue(SpecialOffer specialOffer , boolean trashed) {
		specialOffer.setDeleted(trashed);
		serv.save(specialOffer);
	}
	
	@Autowired
	private SpecialOfferServ serv;
	
	@Autowired
	private PizzaServ pizzaServ;
	
	/*
	 * 
	 * A Method that manages the index page by showing all the available SpecialOffer elements in a table
	 */
	@GetMapping("/")
	public String index(Model model ) {
		List<SpecialOffer> specialOffers = serv.findAllAvailableSpecialOffers();
		return getSpecialOffers(specialOffers , "Lista offerte speciali" , "view/special-offer/index" , model);
	}
	
	/*
	 * 
	 * A Method that manages the show page by showing the single SpecialOffer element details
	 */
	@GetMapping("/{id}")
	public String show(Model model , @PathVariable("id") int id) {
		Optional<SpecialOffer> optSpecialOffer = serv.findById(id);
		SpecialOffer specialOffer = optSpecialOffer.get();
		pageTitle = "Offerta speciale: " + specialOffer.getTitle();
		model.addAttribute("specialOffer" , specialOffer);
		model.addAttribute("title" , pageTitle);
		return "view/special-offer/show";
	}
	
	/*
	 * 
	 * A Method that manages the create page by showing the necessary form to create a new SpecialOffer element
	 */
	@GetMapping("/create")
	public String create(Model model) {
		List<Pizza> pizzas = pizzaServ.findAllAvailablePizzas();
		return modifyOrCreateSpecialOffer(new SpecialOffer() , pizzas , "Creazione offerta speciale" , "Aggiungi alla lista l'offerta" , "view/special-offer/create" , model);
	}
	
	/*
	 * 
	 * A Method that manages the store of a SpecialOffer element in the database
	 */
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("special-offer") SpecialOffer specialOffer , BindingResult br , Model model) {
		List<Pizza> pizzas = pizzaServ.findAllAvailablePizzas();
		return saveInDb(specialOffer, pizzas, br , "view/special-offer/create" ,  "redirect:/special-offers/" , "Creazione offerta" , "Aggiungi alla lista l'offerta" , model);
	}
	
	/*
	 * 
	 * A Method that manages the edit page by showing the necessary form to edit the SpecialOffer element
	 */
	@GetMapping("/edit/{id}")
	public String edit(Model model , @PathVariable("id") int id) {
		Optional<SpecialOffer> optSpecialOffer = serv.findById(id);
		SpecialOffer specialOffer = optSpecialOffer.get();
		pageTitle = "Modifica l'offerta : " + specialOffer.getTitle();
		List<Pizza> pizzas = pizzaServ.findAllAvailablePizzas();
		return modifyOrCreateSpecialOffer(specialOffer , pizzas , pageTitle , "Modifica elemento" , "view/special-offer/edit" , model);
	}
	
	/*
	 * 
	 * A Method that manages the update of a SpecialOffer element in the database
	 */
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("special-offer") SpecialOffer specialOffer , BindingResult br , Model model) {
		pageTitle = "Modifica l'offerta: " + specialOffer.getTitle();
		List<Pizza> pizzas = pizzaServ.findAllAvailablePizzas();
		return saveInDb(specialOffer, pizzas , br , "view/special-offer/edit" , "redirect:/special-offers/" + specialOffer.getId() , pageTitle , "Modifica elemento" , model);
	}
	
	/*
	 * 
	 * A Method that manages the trash page by showing all the trashed SpecialOffer elements in a table
	 */
	@GetMapping("/trash")
	public String trash(Model model ) {
		List<SpecialOffer> specialOffers = serv.findAllTrashedSpecialOffers();
		return getSpecialOffers(specialOffers , "Lista offerte cestinate" , "view/special-offer/trash" , model);
	}
	
	/*
	 * 
	 * A Method that manages the soft-delete of a SpecialOffer element
	 */
	@PostMapping("/soft-delete/{id}")
	public String softDelete(@PathVariable("id") int id) {
		Optional<SpecialOffer> optSpecialOffer = serv.findById(id);
		SpecialOffer specialOffer = optSpecialOffer.get();
		changeTheDeletedValue(specialOffer, true);
		return "redirect:/special-offers/";
	}
	
	/*
	 * 
	 * A Method that manages the soft-delete of all SpecialOffer elements
	 */
	@PostMapping("/soft-delete-all")
	public String softDeleteAll() {
		List<SpecialOffer> specialOffers = serv.findAllAvailableSpecialOffers();
//		for(SpecialOffer so : specialOffers) {
//			changeTheDeletedValue(so,true);
//		}
		specialOffers.stream().forEach(so->changeTheDeletedValue(so, true));
		return "redirect:/special-offers/";
	}
	
	/*
	 * 
	 * A Method that manages the refresh of a SpecialOffer element
	 */
	@PostMapping("/refresh/{id}")
	public String refresh(@PathVariable("id") int id) {
		Optional<SpecialOffer> optSpecialOffer = serv.findById(id);
		SpecialOffer specialOffer = optSpecialOffer.get();
		changeTheDeletedValue(specialOffer, false);
		return "redirect:/special-offers/trash";
	}
	
	/*
	 * 
	 * A Method that manages the refresh of all SpecialOffer elements
	 */
	@PostMapping("/refresh-all")
	public String refreshAll() {
		List<SpecialOffer> specialOffers = serv.findAllTrashedSpecialOffers();
//		for(SpecialOffer so : specialOffers) {
	//		changeTheDeletedValue(so,false);
	//	}
		specialOffers.stream().forEach(so->changeTheDeletedValue(so, false));
		return "redirect:/special-offers/trash";
	}
	
	/*
	 * 
	 * A Method that manages the delete of a SpecialOffer element
	 */
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		Optional<SpecialOffer> optSpecialOffer = serv.findById(id);
		SpecialOffer specialOffer = optSpecialOffer.get();
		serv.delete(specialOffer);
		return "redirect:/special-offers/trash";
	}
	
	/*
	 * 
	 * A Method that manages the delete of all SpecialOffer elements
	 */
	@PostMapping("/delete-all")
	public String deleteAll() {
		List<SpecialOffer> specialOffers = serv.findAllTrashedSpecialOffers();
		serv.deleteAll(specialOffers);
		return "redirect:/special-offers/trash";
	}
}
