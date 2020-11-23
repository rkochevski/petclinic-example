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
	
	@GetMapping("/owners/{id}/pets/new")
	public String createOrUpdatePetForm(@PathVariable("id") Integer id, Model model) {
		Owner owner = ownerService.getById(id);
		Pet pet = new Pet();
		List<PetType> listPetTypes = petTypeService.getAllPetTypes();
		model.addAttribute("listPetTypes", listPetTypes);
		model.addAttribute("pet", pet);
		model.addAttribute("owner", owner);
		return "pets/createOrUpdatePetForm";
	}
	
	@PostMapping("/owners/{id}/pet/add")
	public String createPet(@PathVariable("id") Integer id, @ModelAttribute("pet") Pet pet) {
		Pet newPet = new Pet();
		Owner owner = ownerService.getById(id);
		newPet.setName(pet.getName());
		newPet.setBirthDate(pet.getBirthDate());
		newPet.setType(pet.getType());
		newPet.setOwner(owner);
		List<Pet> pets = petService.getByOwner(owner);
		pets.add(newPet);
		petService.createPet(newPet);
		owner.setPets(pets);
		ownerService.createOwner(owner);
		return "redirect:/owners/" + newPet.getOwner().getId();
	}

}
