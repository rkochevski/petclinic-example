package com.example.petclinicExercise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.petclinicExercise.entity.Specialty;
import com.example.petclinicExercise.entity.Vet;
import com.example.petclinicExercise.repository.VetRepository;

@Service
public class VetService {
	
	@Autowired
	VetRepository vetRepo;

	public List<Vet> getAllVets() {
		return vetRepo.findAll();
	}

	public void saveVet(Vet vet) {
		vetRepo.save(vet);
	}

	public Vet findById(Integer vetId) {
		return vetRepo.findById(vetId).get();
	}

	public void updateVet(Vet vet, Integer vetId) {
		Vet updateVet = findById(vetId);
		updateVet.setFirstName(vet.getFirstName());
		updateVet.setLastName(vet.getLastName());
		Specialty spec = vet.getSpecialties().get(0);
		updateVet.getSpecialties().add(spec);
		saveVet(updateVet);
	}

	public void deleteVetById(Integer vetId) {
		vetRepo.deleteById(vetId);
	}

}
