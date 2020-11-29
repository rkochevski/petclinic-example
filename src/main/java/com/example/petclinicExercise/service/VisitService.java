package com.example.petclinicExercise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.petclinicExercise.entity.Pet;
import com.example.petclinicExercise.entity.Visit;
import com.example.petclinicExercise.repository.VisitRepository;

@Service
public class VisitService {
	
	@Autowired
	PetService petService;
	
	@Autowired
	VisitRepository visitRepo;

	public String CreateOrUpdateVisitForm(Integer petId, Model model) {
		Visit visit = new Visit();
		Pet pet = petService.getById(petId);
		List<Visit> visits = getByPetId(petId);
		pet.setVisits(visits);
		model.addAttribute("pet", pet);
		model.addAttribute("visit", visit);
		return "visits/createOrUpdateVisitForm";
	}

	public String addVisitReturnOwnerDetailsPage(Integer petId, Visit visit) {
		Visit newVisit = new Visit();
		Pet pet = petService.getById(petId);
		newVisit.setDate(visit.getDate());
		newVisit.setDescription(visit.getDescription());
		newVisit.setPetId(petId);
		List<Visit> visits = getByPetId(petId);
		visits.add(newVisit);
		createVisit(newVisit);
		pet.setVisits(visits);
		petService.createPet(pet);
		return "redirect:/owners/" + pet.getOwner().getId();
	}
	
	private void createVisit(Visit newVisit) {
		visitRepo.save(newVisit);
	}

	public List<Visit> getByPetId(Integer petId) {
		return visitRepo.findByPetId(petId);
	}

	public void deleteByPetId(Integer petId) {
		visitRepo.deleteByPetId(petId);
	}

}
