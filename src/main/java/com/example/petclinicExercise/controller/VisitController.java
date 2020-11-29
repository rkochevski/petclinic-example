package com.example.petclinicExercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.petclinicExercise.entity.Visit;
import com.example.petclinicExercise.service.VisitService;

@Controller
public class VisitController {
	
	@Autowired
	VisitService visitService;
	
	@GetMapping("/owners/{ownerId}/pets/{petId}/visits/new")
	public String initNewVisitForm(@PathVariable("petId") Integer petId, Model model) {
		return visitService.createVisitForm(petId, model);
	}
	
	@PostMapping("/owners/{ownerId}/pets/{petId}/visits/add")
	public String addVisit(@PathVariable("petId") Integer petId, @ModelAttribute("visit") Visit visit) {
		return visitService.addVisitReturnOwnerDetailsPage(petId, visit);
	}

}
