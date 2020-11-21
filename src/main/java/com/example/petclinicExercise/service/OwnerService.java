package com.example.petclinicExercise.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.petclinicExercise.entity.Owner;
import com.example.petclinicExercise.repository.OwnerRepository;

@Service
public class OwnerService {
	
	@Autowired
	OwnerRepository ownerRepo;
	
	// Get Owner by lastName
	public Collection<Owner> getByLastName(String lastName) {
		return ownerRepo.findByLastName(lastName);
	}
	
	// Get Owner by ID
	public Owner findById(Integer ownerId) {
		return ownerRepo.findById(ownerId).get();
	}

	// Create Owner
	public void createOwner(Owner owner) {
		ownerRepo.save(owner);
	}

}
