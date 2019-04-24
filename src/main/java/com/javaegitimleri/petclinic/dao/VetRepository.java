package com.javaegitimleri.petclinic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaegitimleri.petclinic.model.Vet;

public interface VetRepository extends JpaRepository<Vet, Long>{

	
}
