package com.example.petclinicExercise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.petclinicExercise.entity.Pet;
import com.example.petclinicExercise.repository.PetRepository;

@Service
public class PetService {
	
	@Autowired
	PetRepository petRepo;

	public List<Pet> getAllPets() {
		return petRepo.findAll();
	}

	public void createPet(Pet pet) {
		petRepo.save(pet);
	}

}
