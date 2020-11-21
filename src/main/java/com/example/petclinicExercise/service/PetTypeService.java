package com.example.petclinicExercise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.petclinicExercise.entity.PetType;
import com.example.petclinicExercise.repository.PetTypeRepository;

@Service
public class PetTypeService {
	
	@Autowired
	PetTypeRepository petTypeRepo;

	public List<PetType> getAllPetTypes() {
		return petTypeRepo.findAll();
	}

}
