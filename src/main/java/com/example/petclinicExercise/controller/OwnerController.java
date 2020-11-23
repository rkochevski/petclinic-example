package com.example.petclinicExercise.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.petclinicExercise.entity.Owner;
import com.example.petclinicExercise.entity.Pet;
import com.example.petclinicExercise.entity.Vet;
import com.example.petclinicExercise.service.OwnerService;
import com.example.petclinicExercise.service.PetService;

@Controller
@RequestMapping("/owners")
public class OwnerController {
	
	@Autowired
	OwnerService ownerService;
	
	@Autowired
	PetService petService;
	
	// Get findOrers.html
	@GetMapping("/find")
	public String findOwnersPage(Model model) {
		Owner owner = new Owner();
		model.addAttribute("owner", owner);
		return "owners/findOwners";
	}
	
	// Get createOrUpdateOwnerForm.html
	@GetMapping("/new")
	public String createOrUpdateOwnerForm(Model model) {
		Owner owner = new Owner();
		model.addAttribute("owner", owner);
		return "owners/createOrUpdateOwnerForm";
	}
	
	// Create Owner
	@PostMapping("/create")
	public String createOwner(@ModelAttribute("owner") Owner owner) {
		ownerService.createOwner(owner);
		return "redirect:/owners/" + owner.getId();
	}
	
	// Get Owner by Last Name
	@GetMapping("/ownersList")
	public String ownersListForm(Model model) {
		List<Owner> results = ownerService.getAllOwners();
		model.addAttribute("ownersList", results);
		return "owners/ownersList";
	}
	
	// Get Owner by Last Name
	@GetMapping("/findByLastName")
	public String findByLastnamePage(Model model, Owner owner) {
		
		if (owner.getLastName() == null) {
			owner.setLastName("");
		}
		
		List<Owner> results = ownerService.getByLastName(owner.getLastName());
		
		if (results.isEmpty()) {
			return "owners/findOwners";
		}
		else if (results.size() == 1) {
			owner = results.iterator().next();
			return "redirect:/owners/" + owner.getId();
		}
		else {
			model.addAttribute("ownersList", results);
			return "owners/ownersList";
		}
	}
	
	// Get ownerDetails.html
	@GetMapping("/{id}")
	public String showOwnerDetailsPage(@PathVariable("id") Integer id, Model model) {
		Owner owner = ownerService.getById(id);
		List<Pet> petsList = petService.getByOwner(owner);
		model.addAttribute(owner);
		model.addAttribute("petsList", petsList);
		return "owners/ownerDetails";
	}
	
	// Get deleteOwnerConfirmationForm.html
	@GetMapping("/{id}/delete")
	public String deleteOwnerForm(@PathVariable("id") Integer id, Model model) {
		Owner owner = ownerService.getById(id);
		model.addAttribute(owner);
		return "owners/deleteOwnerConfirmationForm";
	}
	
	// Delete Owner
	@PostMapping("/delete/{id}")
	public String deleteOwner(@PathVariable("id") Integer id) {
		ownerService.deleteOwnerById(id);
		return "redirect:/owners/ownersList";
	}

}
