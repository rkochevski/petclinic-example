package com.example.petclinicExercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.petclinicExercise.entity.Pet;
import com.example.petclinicExercise.service.OwnerService;
import com.example.petclinicExercise.service.PetService;
import com.example.petclinicExercise.service.PetTypeService;

@Controller
public class PetController {
	
	@Autowired
	PetService petService;
	
	@Autowired
	PetTypeService petTypeService;
	
	@Autowired
	OwnerService ownerService;
	
	// Get createPetForm.html
	@GetMapping("/owners/{id}/pets/new")
	public String createPetForm(@PathVariable("id") Integer id, Model model) {
		return petService.createPetForm(id, model);
	}
	
	// Create new Pet
	@PostMapping("/owners/{id}/pet/add")
	public String createPet(@PathVariable("id") Integer id, @ModelAttribute("pet") Pet pet) {
		return petService.createNewPetReturnOwnerInfoPage(id, pet);
	}
	
	// Get updatePetFOrm.html
	@GetMapping("/owners/{ownerId}/pets/{petId}/edit")
	public String updatePetForm(@PathVariable("petId") Integer petId, @PathVariable("ownerId") Integer ownerId, Model model) {
		return petService.updatePetForm(petId, ownerId, model);
	}
	
	// Update Pet
	@PostMapping("/owners/{ownerId}/pet/update")
	public String updatePet(@PathVariable("ownerId") Integer ownerId, @ModelAttribute("pet") Pet pet) {
		return petService.updatePetReturnOwnerInfoPage(ownerId, pet);
	}
	
	// Get deletePetConfirmationForm.html
	@GetMapping("/owners/{ownerId}/pet/{petId}/delete")
	public String deletePetConfirmationForm(@PathVariable("petId") Integer petId, Model model) {
		return petService.deletePetConfirmationForm(petId, model);
	}
	
	// Delete Pet
	@PostMapping("/owners/{ownerId}/pet/{petId}/delete")
	public String deletePet(@PathVariable("petId") Integer petId, @PathVariable("ownerId") Integer ownerId) {
		return petService.deletePetReturnOwnerDetails(petId, ownerId);
	}

}
