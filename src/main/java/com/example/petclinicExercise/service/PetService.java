package com.example.petclinicExercise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.example.petclinicExercise.entity.Owner;
import com.example.petclinicExercise.entity.Pet;
import com.example.petclinicExercise.entity.PetType;
import com.example.petclinicExercise.repository.PetRepository;

@Service
public class PetService {
	
	@Autowired
	PetRepository petRepo;
	
	@Autowired
	PetTypeService petTypeService;
	
	@Autowired
	OwnerService ownerService;
	
	@Autowired
	VisitService visitService;

	public List<Pet> getAllPets() {
		return petRepo.findAll();
	}

	public void createPet(Pet pet) {
		petRepo.save(pet);
	}

	public List<Pet> getByOwner(Owner owner) {
		return petRepo.findByOwner(owner);
	}

	public String createOrUpdatePetForm(Integer id, Model model) {
		Owner owner = ownerService.getById(id);
		Pet pet = new Pet();
		List<PetType> listPetTypes = petTypeService.getAllPetTypes();
		model.addAttribute("listPetTypes", listPetTypes);
		model.addAttribute("pet", pet);
		model.addAttribute("owner", owner);
		return "pets/createOrUpdatePetForm";
	}

	public String createNewPetReturnOwnerInfoPage(Integer id, Pet pet) {
		Pet newPet = new Pet();
		Owner owner = ownerService.getById(id);
		newPet.setName(pet.getName());
		newPet.setBirthDate(pet.getBirthDate());
		newPet.setType(pet.getType());
		newPet.setOwner(owner);
		List<Pet> pets = getByOwner(owner);
		pets.add(newPet);
		createPet(newPet);
		owner.setPets(pets);
		ownerService.createOwner(owner);
		return "redirect:/owners/" + newPet.getOwner().getId();
	}

	public Pet getById(Integer petId) {
		return petRepo.findById(petId).get();
	}

	public String updatePetForm(Integer petId, Integer ownerId, Model model) {
		Owner owner = ownerService.getById(ownerId);
		model.addAttribute("owner", owner);
		Pet pet = getById(petId);
		model.addAttribute("pet", pet);
		List<PetType> listPetTypes = petTypeService.getAllPetTypes();
		model.addAttribute("listPetTypes", listPetTypes);
		return "pets/updatePetForm";
	}

	public String updatePetReturnOwnerInfoPage(Integer ownerId, Pet pet) {
		createPet(pet);
		return "redirect:/owners/" + pet.getOwner().getId();
	}

	public String deletePetConfirmationForm(Integer petId, Model model) {
		Pet pet = getById(petId);
		model.addAttribute("pet", pet);
		return "pets/deletePetConfirmationForm";
	}


	@Transactional
	public String deletePetReturnOwnerDetails(Integer petId, Integer ownerId) {
		visitService.deleteByPetId(petId);
		petRepo.deleteById(petId);
		return "redirect:/owners/" + ownerId;
	}

}
