package com.javaegitimleri.petclinic.dao;

import java.util.List;

import com.javaegitimleri.petclinic.model.Owner;

public interface OwnerRepository {

	List<Owner> findAll();
	Owner findById(Long id);
	List<Owner> findByLastname(String lastName);
	List<Owner> findByFirstname(String firstName);
	void create(Owner owner);
	Owner update(Owner owner);
	void delete(Long id);
}
