package com.example.petclinicExercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.petclinicExercise.entity.PetType;

@Repository
public interface PetTypeRepository extends JpaRepository<PetType, Integer> {

}
