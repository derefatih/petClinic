package com.javaegitimleri.petclinic;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="petclinic")
public class PetClinicProperties {
	
	private boolean isDisplayOwnersWithPets = false;

	public boolean isDisplayOwnersWithPets() {
		return isDisplayOwnersWithPets;
	}

	public void setDisplayOwnersWithPets(boolean isDisplayOwnersWithPets) {
		this.isDisplayOwnersWithPets = isDisplayOwnersWithPets;
	}
	
	
	
	

}
