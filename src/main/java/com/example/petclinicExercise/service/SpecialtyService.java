package com.example.petclinicExercise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.petclinicExercise.entity.Specialty;
import com.example.petclinicExercise.repository.SpecialtyRepository;

@Service
public class SpecialtyService {
	
	@Autowired
	SpecialtyRepository specialtyRepo;

	public List<Specialty> getAllSpecialties() {
		return specialtyRepo.findAll();
	}

}
