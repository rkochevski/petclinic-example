package com.example.petclinicExercise.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.petclinicExercise.entity.Owner;
import com.example.petclinicExercise.repository.OwnerRepository;

@Service
public class OwnerService {
	
	@Autowired
	OwnerRepository ownerRepo;
	
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

	public void updateOwner(Owner owner) {
		// TODO Auto-generated method stub
		
	}

}
