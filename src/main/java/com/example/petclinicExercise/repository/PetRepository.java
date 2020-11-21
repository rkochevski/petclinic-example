package com.example.petclinicExercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.petclinicExercise.entity.Pet;

@Repository
public interface PetRepository  extends JpaRepository<Pet, Integer> {

}
