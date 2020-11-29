package com.example.petclinicExercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.petclinicExercise.entity.Vet;
import com.example.petclinicExercise.service.SpecialtyService;
import com.example.petclinicExercise.service.VetService;

@Controller
@RequestMapping("/vets")
public class VetController {
	
	@Autowired
	VetService vetService;
	
	@Autowired
	SpecialtyService specialtyService;
	
	// Get vetsList.html
	@GetMapping("/vetsList")
	public String vetsListPage(Model model) {
		return vetService.vetsListPage(model);
	}
	
	// Get createVetForm.html
	@GetMapping("/new")
	public String createVetForm(Model model) {
		return vetService.createVetForm(model);
	}
	
	// Create Vet in db
	@PostMapping("/create")
	public String createVet(@ModelAttribute("vet") Vet vet) {
		return vetService.createVetReturnVetsListPage(vet);
	}
	
	// Get vetDetails.html
	@GetMapping("/{vetId}")
	public String showVetDetailsPage(@PathVariable("vetId") Integer vetId, Model model) {
		return vetService.vetDetailsPage(vetId, model);
	}
	
	// Get vetUpdateForm.html
	@GetMapping("/{vetId}/edit")
	public String showVetUpdatePage(@PathVariable("vetId") Integer vetId, Model model) {
		return vetService.vetUpdateForm(vetId, model);
	}
	
	// Update Vet
	@PostMapping("/{vetId}/update")
	public String updateVet(@PathVariable("vetId") Integer vetId, @ModelAttribute("vet") Vet vet) {
		return vetService.updateVetReturnVetsListPage(vetId, vet);
	}
	
	// Get deleteVetConfirmationForm.html
	@GetMapping("/{vetId}/delete")
	public String deleteVetForm(@PathVariable("vetId") Integer vetId, Model model) {
		return vetService.deleteVetConfirmationForm(vetId, model);
	}
	
	// Delete Vet
	@PostMapping("/delete/{vetId}")
	public String deleteVet(@PathVariable("vetId") Integer vetId) {
		return vetService.deleteVetReturnVetsListPage(vetId);
	}

}
