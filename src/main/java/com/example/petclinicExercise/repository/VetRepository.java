package com.example.petclinicExercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.petclinicExercise.entity.Vet;

@Repository
public interface VetRepository extends JpaRepository<Vet, Integer> {

}
