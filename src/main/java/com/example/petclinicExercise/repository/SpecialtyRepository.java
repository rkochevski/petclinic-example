package com.example.petclinicExercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.petclinicExercise.entity.Specialty;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Integer> {

}
