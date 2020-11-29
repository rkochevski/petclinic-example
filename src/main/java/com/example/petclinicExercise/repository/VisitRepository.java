package com.example.petclinicExercise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.petclinicExercise.entity.Visit;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Integer> {

	List<Visit> findByPetId(Integer petId);

	void deleteByPetId(Integer petId);

}
