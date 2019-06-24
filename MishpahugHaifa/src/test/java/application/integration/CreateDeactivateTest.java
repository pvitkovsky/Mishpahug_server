package application.integration;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

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

import application.dto.LoginDTO;
import application.dto.LoginResponse;
import application.models.event.EventEntity;
import application.models.event.EventRepository;
import application.models.relation.SubscriptionEntity;
import application.models.relation.SubscriptionRepository;
import application.models.user.UserEntity;
import application.models.user.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CreateDeactivateTest {

	private final UserEntity ALYSSA = new UserEntity("Alyssa", "p_hacker-test@sicp.edu");
	private final UserEntity BEN = new UserEntity("Ben", "bitdiddle-test@sicp.edu");
	private EventEntity GUESTING;
	private SubscriptionEntity AGUESTING;
	private final HttpHeaders headers = new HttpHeaders();
	private String token;

	@Autowired
	UserRepository userRepo;
	@Autowired
	EventRepository eventRepo;
	@Autowired
	SubscriptionRepository subscriptionRepo;

	@LocalServerPort
	private int port;
	private RestTemplate restTemplate = new RestTemplate();

	@Before
	public void buildEntities() { // TODO: save token manually instead of doing login;

		ALYSSA.setEncrytedPassword(DigestUtils.md5Hex(ALYSSA.getUserName()));
		userRepo.save(ALYSSA);
		userRepo.save(BEN);
		GUESTING = new EventEntity(ALYSSA.getId(), LocalDate.now(), LocalTime.now().truncatedTo(ChronoUnit.SECONDS));
		eventRepo.save(GUESTING);
		AGUESTING = new SubscriptionEntity(GUESTING.getId(), BEN.getId());
		subscriptionRepo.save(AGUESTING);
		
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
		AGUESTING.putIntoDeletionQueue();
		subscriptionRepo.delete(AGUESTING);
		GUESTING.putIntoDeletionQueue();
		eventRepo.delete(GUESTING);
		ALYSSA.putIntoDeletionQueue();
		userRepo.delete(ALYSSA);
		BEN.putIntoDeletionQueue();
		userRepo.delete(BEN);
		
	
	}

	@Test
	public void testBuild() { 

		assertEquals(userRepo.findById(ALYSSA.getId()).get(),ALYSSA);
		assertEquals(userRepo.findById(BEN.getId()).get(),BEN);
		assertEquals(eventRepo.findById(ALYSSA.getId()).get(),GUESTING);
		assertEquals(subscriptionRepo.findById(AGUESTING.getId()).get(),AGUESTING);
 
	}
	
//	public void testDeactivation() { //TODO: postman works ok, but not this. why?
//
//        UserDTO updated = this.restTemplate.exchange("http://localhost:" + port + "/user/" + ALYSSA.getId(), HttpMethod.DELETE,
//        		null,
//                new ParameterizedTypeReference<UserDTO>() {
//                }).getBody();
//        System.out.println(updated);
//	}



}
