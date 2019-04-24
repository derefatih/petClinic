package com.javaegitimleri.petclinic.web;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.javaegitimleri.petclinic.model.Owner;

import ch.qos.logback.core.boolex.Matcher;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT)
@ActiveProfiles("dev")
public class PetClinicRestControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Before
	public void setUp() {
		restTemplate = restTemplate.withBasicAuth("user2", "secret");
	}
	
	@Test
	public void testDeleteOwner() {
		//restTemplate.delete("http://localhost:8080/rest/owner/1");
		
		ResponseEntity<Void> responseEntity = restTemplate.exchange("http://localhost:8080/rest/owner/1", HttpMethod.DELETE,null,Void.class);
		
		try {
			restTemplate.getForEntity("http://localhost:8080/rest/owner/1", Owner.class);
			Assert.fail("should have not owner returned");
		} catch (HttpClientErrorException e) {
			MatcherAssert.assertThat(e.getStatusCode().value(), Matchers.equalTo(404));
		}
	
	}

	@Test
	public void testCreateOwner() {

		Owner owner = new Owner();
		owner.setFirstName("huzi");
		owner.setLastName("dereee");

		URI location = restTemplate.postForLocation("http://localhost:8080/rest/owner", owner);

		Owner owner2 = restTemplate.getForObject(location, Owner.class);
		assertThat(owner.getFirstName(), Matchers.equalTo(owner2.getFirstName()));
		assertThat(owner.getLastName(), Matchers.equalTo(owner2.getLastName()));

	}

	@Test
	public void testOwnerUpdate() {
		Owner owner = restTemplate.getForObject("http://localhost:8080/rest/owner/4", Owner.class);

		assertThat(owner.getFirstName(), Matchers.equalTo("ezgi"));

		owner.setFirstName("ezgiii");

		restTemplate.put("http://localhost:8080/rest/owner/4", owner);

		Owner owner2 = restTemplate.getForObject("http://localhost:8080/rest/owner/4", Owner.class);

		assertThat(owner2.getFirstName(), Matchers.equalTo("ezgiii"));

	}

	@Test
	public void testGetOwnerById() {
		ResponseEntity<Owner> response = restTemplate.getForEntity("http://localhost:8080/rest/owner/2", Owner.class);

		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
		MatcherAssert.assertThat(response.getBody().getFirstName(), Matchers.equalTo("Beşir"));

	}

	@Test
	public void testGetOwnersByLastName() {
		ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://localhost:8080/rest/owner?ln=dere",
				List.class);

		MatcherAssert.assertThat(responseEntity.getStatusCodeValue(), Matchers.equalTo(200));

		List<Map<String, String>> body = responseEntity.getBody();

		List<String> firstNames = body.stream().map(e -> e.get("firstName")).collect(Collectors.toList());

		MatcherAssert.assertThat(firstNames, Matchers.containsInAnyOrder("Fatih", "egemen", "ezgi"));

	}

	@Test
	public void testGetOwners() {

		ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://localhost:8080/rest/owners",
				List.class);

		assertThat(responseEntity.getStatusCodeValue(), Matchers.equalTo(200));

		List<Map<String, String>> body = responseEntity.getBody();
		int size = responseEntity.getBody().size();

		List<String> firstNames = body.stream().map(e -> e.get("firstName")).collect(Collectors.toList());

		Matchers.arrayContaining(firstNames, "Fatih");
		assertThat(firstNames, Matchers.containsInAnyOrder("Fatih", "egemen", "ezgi", "ezgi"));
		assertThat(size, equalTo(4));
	}

}
