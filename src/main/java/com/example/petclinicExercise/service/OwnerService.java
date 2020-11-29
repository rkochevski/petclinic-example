package com.example.petclinicExercise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.petclinicExercise.entity.Owner;
import com.example.petclinicExercise.entity.Pet;
import com.example.petclinicExercise.entity.Visit;
import com.example.petclinicExercise.repository.OwnerRepository;

@Service
public class OwnerService {
	
	@Autowired
	OwnerRepository ownerRepo;
	
	@Autowired
	PetService petService;
	
	@Autowired
	VisitService visitService;
	
	// Get Owner by lastName
	public List<Owner> getByLastName(String lastName) {
		return ownerRepo.findByLastName(lastName);
	}
	
	// Get Owner by ID
	public Owner getById(Integer ownerId) {
		return ownerRepo.findById(ownerId).get();
	}

	// Create Owner
	public void createOwner(Owner owner) {
		ownerRepo.save(owner);
	}

	// Delete Owner by ID
	public void deleteOwnerById(Integer id) {
		ownerRepo.deleteById(id);
	}

	// Get a list of all Owners
	public List<Owner> getAllOwners() {
		return ownerRepo.findAll();
	}

	// Update owner and display to ownerDetails
	public String updateOwner(Owner owner) {
		List<Pet> pets = petService.getByOwner(owner);
		owner.setPets(pets);
		createOwner(owner);
		return "redirect:/owners/" + owner.getId();
	}

	// Find Owner by Last Name and display ownerDetails
	public String searchOwnersByLastName(Model model, Owner owner) {
		
		if (owner.getLastName() == null) {
			owner.setLastName("");
		}
		
		List<Owner> results = getByLastName(owner.getLastName());
		
		if (results.isEmpty()) {
			List<Owner> allOwners = getAllOwners();
			model.addAttribute("ownersList", allOwners);
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

	// Display findOwners page
	public String findOwnersPage(Model model) {
		Owner owner = new Owner();
		model.addAttribute("owner", owner);
		return "owners/findOwners";
	}

	// Display createOwner page
	public String createOwnerForm(Model model) {
		Owner owner = new Owner();
		model.addAttribute("owner", owner);
		return "owners/createOwnerForm";
	}

	// Create new Owner and display ownerDetails page
	public String createNewOwner(Owner owner) {
		createOwner(owner);
		return "redirect:/owners/" + owner.getId();
	}

	// Get list of all Owners and open the ownerList page
	public String ownersListPage(Model model) {
		List<Owner> results = getAllOwners();
		model.addAttribute("ownersList", results);
		return "owners/ownersList";
	}

	// Display the ownerDetails page
	public String ownerDetailsPage(Integer id, Model model) {
		Owner owner = getById(id);
		List<Pet> petsList = petService.getByOwner(owner);
		for (Pet pet : petsList) {
			List<Visit> visits =visitService.getByPetId(pet.getId());
			pet.setVisits(visits);
		}
		model.addAttribute(owner);
		model.addAttribute("petsList", petsList);
		return "owners/ownerDetails";
	}

	// Display deleteOwnerConfirmationForm
	public String deleteOwnerConfirmationForm(Integer id, Model model) {
		Owner owner = getById(id);
		model.addAttribute(owner);
		return "owners/deleteOwnerConfirmationForm";
	}

	// Delete Owner and display ownersList page
	public String deleteOwnerReturnOwnersList(Integer id) {
		deleteOwnerById(id);
		return "redirect:/owners/ownersList";
	}

	// Display updateOwnerForm
	public String updateOwnerForm(Integer id, Model model) {
		Owner owner = getById(id);
		model.addAttribute(owner);
		return "owners/updateOwnerForm";
	}

}
