package com.javaegitimleri.petclinic.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaegitimleri.petclinic.model.Pet;

public interface PetRepository {

	Pet findById(Long id);
	List<Pet> findByOwner(Long ownerId);
	void create(Pet pet);
	Pet update(Pet pet);
	void delete(Long id);
	void deleteByOwnerId(Long ownerId);
}
