package org.java.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
	
	@GetMapping("/")
	public String index(Model model) {
		String pageTitle = "Home";
		model.addAttribute("title" , pageTitle);
		return "view/index";
	}
}
