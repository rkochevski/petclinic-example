package com.example.petclinicExercise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.petclinicExercise.entity.Owner;
import com.example.petclinicExercise.entity.Pet;

@Repository
public interface PetRepository  extends JpaRepository<Pet, Integer> {

	List<Pet> findByOwner(Owner owner);

}
