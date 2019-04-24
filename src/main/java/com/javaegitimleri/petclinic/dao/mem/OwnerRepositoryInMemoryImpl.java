package com.javaegitimleri.petclinic.dao.mem;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaegitimleri.petclinic.dao.OwnerRepository;
import com.javaegitimleri.petclinic.model.Owner;

@Repository
public class OwnerRepositoryInMemoryImpl implements OwnerRepository {
	
	private Map<Long,Owner> ownersMap = new HashMap<>();

	public OwnerRepositoryInMemoryImpl() {
		
		Owner owner1 = new Owner();
		owner1.setId(1L);
		owner1.setFirstName("Fatih");
		owner1.setLastName("dere");
		
		Owner owner2 = new Owner();
		owner2.setId(2L);
		owner2.setFirstName("ezgi");
		owner2.setLastName("dere");
		
		Owner owner3 = new Owner();
		owner3.setId(3L);
		owner3.setFirstName("egemen");
		owner3.setLastName("dere");
		
		Owner owner4 = new Owner();
		owner4.setId(4L);
		owner4.setFirstName("ezgi");
		owner4.setLastName("yıldız");
		
		ownersMap.put(owner1.getId(), owner1);
		ownersMap.put(owner2.getId(), owner2);
		ownersMap.put(owner3.getId(), owner3);
		ownersMap.put(owner4.getId(), owner4);
		
	}

	@Override
	public List<Owner> findAll() {
		
		return new ArrayList<>(ownersMap.values());
	}

	@Override
	public Owner findById(Long id) {
		return ownersMap.get(id);
	}

	@Override
	public List<Owner> findByLastname(String lastName) {
		return ownersMap.values().stream().filter(o->o.getLastName().equals(lastName)).collect(Collectors.toList());
	}

	@Override
	public void create(Owner owner) {
		owner.setId(new Date().getTime());
		ownersMap.put(owner.getId(), owner);

	}

	@Override
	public Owner update(Owner owner) {
		ownersMap.replace(owner.getId(), owner);
		return owner;
	}

	@Override
	public void delete(Long id) {
		ownersMap.remove(id);

	}

	@Override
	public List<Owner> findByFirstname(String firstName) {
		//return ownersMap.values().stream().filter(o->o.getFirstName().equals(firstName)).collect(Collectors.toList());
		return ownersMap.values().stream().filter(o->o.getFirstName().contains(firstName)).collect(Collectors.toList());
	}

}
