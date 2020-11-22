package com.example.petclinicExercise.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.petclinicExercise.entity.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

//	List<Owner> findByLastName(String lastName);
	
	@Query("SELECT o FROM Owner o JOIN o.pets WHERE o.lastName LIKE :lastName%")
	@Transactional(readOnly = true)
	Collection<Owner> findByLastName(@Param("lastName") String lastName);

}
