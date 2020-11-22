package com.example.petclinicExercise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.petclinicExercise.entity.Owner;
import com.example.petclinicExercise.entity.Pet;
import com.example.petclinicExercise.entity.PetType;
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
	
	@GetMapping("/owners/{ownerId}/pets/new")
	public String createOrUpdatePetForm(@PathVariable("ownerId") Integer ownerId, Model model) {
		Owner owner = ownerService.findById(ownerId);
		Pet pet = new Pet();
		owner.getPets().add(pet);
		List<PetType> listPetTypes = petTypeService.getAllPetTypes();
		model.addAttribute("listPetTypes", listPetTypes);
		model.addAttribute("owner", owner);
		model.addAttribute("pet", pet);
		model.addAttribute("type", pet.getType());
		return "pets/createOrUpdatePetForm";
	}
	
	@PostMapping("/pet/add")
	public String createPet(@ModelAttribute("pet") Pet pet, @ModelAttribute("owner") Owner owner) {
		petService.createPet(pet);
		return "redirect:/owners/" + pet.getOwner().getId();
	}

}
