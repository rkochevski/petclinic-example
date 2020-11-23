package com.example.petclinicExercise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.petclinicExercise.entity.Owner;
import com.example.petclinicExercise.entity.Pet;
import com.example.petclinicExercise.entity.Specialty;
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
		List<Vet> vets = vetService.getAllVets();
		model.addAttribute("vets", vets);
		List<Specialty> listSpecialties = specialtyService.getAllSpecialties();
		model.addAttribute("listSpecialties", listSpecialties);
		return "vets/vetsList";
	}
	
	// Get createOrUpdateVetForm.html
	@GetMapping("/new")
	public String createOrUpdateVetForm(Model model) {
		Vet vet = new Vet();
		model.addAttribute("vet", vet);
		List<Specialty> listSpecialties = specialtyService.getAllSpecialties();
		model.addAttribute("listSpecialties", listSpecialties);
		return "vets/createOrUpdateVetForm";
	}
	
	// Create Vet in db
	@PostMapping("/create")
	public String createVet(@ModelAttribute("vet") Vet vet) {
		vetService.saveVet(vet);
		return "redirect:vetsList";
	}
	
	// Get vetDetails.html
	@GetMapping("/{vetId}")
	public String showVetDetailsPage(@PathVariable("vetId") Integer vetId, Model model) {
		Vet vet = vetService.findById(vetId);
		model.addAttribute(vet);
		return "vets/vetDetails";
	}
	
	// Get vetUpdateForm.html
	@GetMapping("/{vetId}/edit")
	public String showVetUpdatePage(@PathVariable("vetId") Integer vetId, Model model) {
		Vet vet = vetService.findById(vetId);
		model.addAttribute(vet);
		List<Specialty> listSpecialties = specialtyService.getAllSpecialties();
		model.addAttribute("listSpecialties", listSpecialties);
		return "vets/vetUpdateForm";
	}
	
	// Update Vet
	@PostMapping("/{vetId}/update")
	public String updateVet(@PathVariable("vetId") Integer vetId, @ModelAttribute("vet") Vet vet) {
		vetService.updateVet(vet, vetId);
		return "redirect:/vets/vetsList";
	}
	
	// Get deleteVetConfirmationForm.html
	@GetMapping("/{vetId}/delete")
	public String deleteVetForm(@PathVariable("vetId") Integer vetId, Model model) {
		Vet vet = vetService.findById(vetId);
		model.addAttribute(vet);
		return "vets/deleteVetConfirmationForm";
	}
	
	// Delete Vet
	@PostMapping("/delete/{vetId}")
	public String deleteVet(@PathVariable("vetId") Integer vetId) {
		vetService.deleteVetById(vetId);
		return "redirect:/vets/vetsList";
	}

}
