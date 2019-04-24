package com.javaegitimleri.petclinic;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTests {

	@Test
	public void generateEncodedPassword() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println("{bcrypt}"+ encoder.encode("secret"));
		System.out.println("{bcrypt}"+ encoder.encode("secret"));
		System.out.println("{bcrypt}"+ encoder.encode("secret"));
	}
}
