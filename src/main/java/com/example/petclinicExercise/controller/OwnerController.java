package com.example.petclinicExercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.petclinicExercise.entity.Owner;
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
		return ownerService.findOwnersPage(model);
	}
	
	// Get createOwnerForm.html
	@GetMapping("/new")
	public String createOwnerForm(Model model) {
		return ownerService.createOwnerForm(model);
	}
	
	// Create Owner
	@PostMapping("/create")
	public String createOwner(@ModelAttribute("owner") Owner owner) {
		return ownerService.createNewOwner(owner);
	}
	
	// Get ownersList.html
	@GetMapping("/ownersList")
	public String ownersListPage(Model model) {
		return ownerService.ownersListPage(model);
	}
	
	// Get Owner by Last Name
	@GetMapping("/findByLastName")
	public String findByLastnamePage(Model model, Owner owner) {
		return ownerService.searchOwnersByLastName(model, owner);
	}
	
	// Get ownerDetails.html
	@GetMapping("/{id}")
	public String showOwnerDetailsPage(@PathVariable("id") Integer id, Model model) {
		return ownerService.ownerDetailsPage(id, model);
	}
	
	// Get deleteOwnerConfirmationForm.html
	@GetMapping("/{id}/delete")
	public String deleteOwnerForm(@PathVariable("id") Integer id, Model model) {
		return ownerService.deleteOwnerConfirmationForm(id, model);
	}
	
	// Delete Owner
	@PostMapping("/delete/{id}")
	public String deleteOwner(@PathVariable("id") Integer id) {
		return ownerService.deleteOwnerReturnOwnersList(id);
	}
	
	// Get updateOwnerForm.html
	@GetMapping("/{id}/edit")
	public String updateOwnerForm(@PathVariable("id") Integer id, Model model) {
		return ownerService.updateOwnerForm(id, model);
	}
	
	// Update Owner
	@PostMapping("/update")
	public String updateOwner(@ModelAttribute("owner") Owner owner) {
		return ownerService.updateOwner(owner);
	}

}
