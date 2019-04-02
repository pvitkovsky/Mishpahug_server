package application.integration;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import application.entities.UserEntity;
import application.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserWebRequestTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	private final UserEntity ALYSSA = new UserEntity();
	
	@Autowired
	UserRepository userRepo;
	
	@Before
	public void buildEntities() {
		ALYSSA.setEMail("p_hacker@sicp.edu");	
	}
	
	
	@Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
		userRepo.save(ALYSSA); 
		//TODO: interceptor for reading JSON response, as there is an encrypted password
    	Collection<UserEntity> users = this.restTemplate.exchange("http://localhost:" + port + "/user/", HttpMethod.GET,
    			  null,
    			new ParameterizedTypeReference<Collection<UserEntity>>(){}).getBody();
    	assertTrue(users.contains(ALYSSA));
    }
}
