package application.integration.controllers;

import application.dto.EventDTO;
import application.dto.LoginDTO;
import application.dto.LoginResponse;
import application.dto.UserDTO;
import application.entities.UserEntity;
import application.repositories.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EventWebRequestTest {

	private final UserEntity ALYSSA = new UserEntity("Alyssa", "p_hacker@sicp.edu");
	private final HttpHeaders headers = new HttpHeaders();
	private String token;

	@Autowired
	UserRepository userRepo;

	@LocalServerPort
	private int port;
	private RestTemplate restTemplate = new RestTemplate();

	@Before
	public void buildEntities() { // TODO: save token manually instead of doing login;

		System.out.println("Alyssa " + ALYSSA);
		ALYSSA.setEncrytedPassword(DigestUtils.md5Hex(ALYSSA.getUserName()));
		userRepo.save(ALYSSA);
		userRepo.flush();

		restTemplate.setRequestFactory(new HttpComponentsAsyncClientHttpRequestFactory());

		token = this.restTemplate.exchange("http://localhost:" + port + "/user/login", HttpMethod.POST,
				new HttpEntity<LoginDTO>(new LoginDTO("Alyssa", "Alyssa")),
				new ParameterizedTypeReference<LoginResponse>() {
				}).getBody().getToken();
		headers.add("Authorization", token);
		headers.setContentType(MediaType.APPLICATION_JSON);
	}

	@After
	public void tearDown() {
		ALYSSA.putIntoDeletionQueue();
		userRepo.delete(ALYSSA);
	}

	@Test
	public void testFiltring() { //TODO: stable data w/o randoms, asserts

//		Collection<EventDTO> events_general = this.restTemplate.exchange("http://localhost:" + port + "/event/",
//				HttpMethod.GET, new HttpEntity<String>(headers), new ParameterizedTypeReference<Collection<EventDTO>>() {
//				}).getBody();
//		events_general.forEach((data) -> System.out.println("event : " + data));

		Collection<EventDTO> events_by_owner = this.restTemplate.exchange("http://localhost:" + port + "/event/?userEntityOwner.userName=a",
				HttpMethod.GET,
				new HttpEntity<String>(headers),
				new ParameterizedTypeReference<Collection<EventDTO>>() {
				}).getBody();
		events_by_owner.forEach((data) -> System.out.println("event : " + data));
		
//		Collection<EventDTO> events_by_guest = this.restTemplate.exchange("http://localhost:" + port + "/event/?subscriptions.guest.userName=a",
//				HttpMethod.GET,
//				new HttpEntity<String>(headers),
//				new ParameterizedTypeReference<Collection<EventDTO>>() {
//				}).getBody();
//		events_by_guest.forEach((data) -> System.out.println("event : " + data));
		
	}

//	@Test
//	public void testGuestListByEvent(){
//		Collection<UserDTO> users = this.restTemplate.exchange("http://localhost:" + port + "/event/5/guests", HttpMethod.GET,
//				new HttpEntity<String>(headers),
//				new ParameterizedTypeReference<Collection<UserDTO>>() {
//				}).getBody();
//		assertTrue(users.size() >= 1);
//	}



}
