package com.example.petclinicExercise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.petclinicExercise.entity.Vet;

@Controller
@RequestMapping
public class VetController {
	
	@GetMapping("/vets/vetsList")
	public String vetsListPage(Model model) {
		Vet vet = new Vet();
		model.addAttribute("vet", vet);
		return "vets/vetsList";
	}

}
