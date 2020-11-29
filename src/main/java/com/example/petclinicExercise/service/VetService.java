package com.example.petclinicExercise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.petclinicExercise.entity.Specialty;
import com.example.petclinicExercise.entity.Vet;
import com.example.petclinicExercise.repository.VetRepository;

@Service
public class VetService {
	
	@Autowired
	VetRepository vetRepo;
	
	@Autowired
	SpecialtyService specialtyService;

	// Get a list of all Veterinarians
	public List<Vet> getAllVets() {
		return vetRepo.findAll();
	}

	// Save Veterinarian
	public void saveVet(Vet vet) {
		vetRepo.save(vet);
	}

	// Find Veterinarian by id
	public Vet findById(Integer vetId) {
		return vetRepo.findById(vetId).get();
	}

	// Update Veterinarian
	public void updateVet(Vet vet, Integer vetId) {
		Vet updateVet = findById(vetId);
		updateVet.setFirstName(vet.getFirstName());
		updateVet.setLastName(vet.getLastName());
		Specialty spec = vet.getSpecialties().get(0);
		updateVet.getSpecialties().add(spec);
		saveVet(updateVet);
	}

	// Delete Veterinarian by id
	public void deleteVetById(Integer vetId) {
		vetRepo.deleteById(vetId);
	}

	// Display vetsList page
	public String vetsListPage(Model model) {
		List<Vet> vets = getAllVets();
		model.addAttribute("vets", vets);
		List<Specialty> listSpecialties = specialtyService.getAllSpecialties();
		model.addAttribute("listSpecialties", listSpecialties);
		return "vets/vetsList";
	}

	// Display createVetForm
	public String createVetForm(Model model) {
		Vet vet = new Vet();
		model.addAttribute("vet", vet);
		List<Specialty> listSpecialties = specialtyService.getAllSpecialties();
		model.addAttribute("listSpecialties", listSpecialties);
		return "vets/createVetForm";
	}

	// Create Veterinarian and display vetsList page
	public String createVetReturnVetsListPage(Vet vet) {
		saveVet(vet);
		return "redirect:vetsList";
	}

	// Display vetDetails page
	public String vetDetailsPage(Integer vetId, Model model) {
		Vet vet = findById(vetId);
		model.addAttribute(vet);
		return "vets/vetDetails";
	}

	// Display vetUpdateForm
	public String vetUpdateForm(Integer vetId, Model model) {
		Vet vet = findById(vetId);
		model.addAttribute(vet);
		List<Specialty> listSpecialties = specialtyService.getAllSpecialties();
		model.addAttribute("listSpecialties", listSpecialties);
		return "vets/vetUpdateForm";
	}

	// Update Veterinarian and display vetsList page
	public String updateVetReturnVetsListPage(Integer vetId, Vet vet) {
		updateVet(vet, vetId);
		return "redirect:/vets/vetsList";
	}

	// Display deleteVetConfirmationForm
	public String deleteVetConfirmationForm(Integer vetId, Model model) {
		Vet vet = findById(vetId);
		model.addAttribute(vet);
		return "vets/deleteVetConfirmationForm";
	}

	// Delete Veterinarian and display vetsList page
	public String deleteVetReturnVetsListPage(Integer vetId) {
		deleteVetById(vetId);
		return "redirect:/vets/vetsList";
	}

}
