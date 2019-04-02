package application.integration;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import application.entities.UserEntity;
import application.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserWebRequestTest {

	@LocalServerPort
	private int port;

	private RestTemplate restTemplate = new RestTemplate();
	
	private final UserEntity ALYSSA = new UserEntity();
	
	@Autowired
	UserRepository userRepo;
	
	@Before
	public void buildEntities() {
		ALYSSA.setEMail("p_hacker@sicp.edu");	
		restTemplate.setRequestFactory(new HttpComponentsAsyncClientHttpRequestFactory());
	}
	
	
	@Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
		userRepo.save(ALYSSA); 
    	Collection<UserEntity> users = this.restTemplate.exchange("http://localhost:" + port + "/user/", HttpMethod.GET,
    			  null,
    			new ParameterizedTypeReference<Collection<UserEntity>>(){}).getBody();
    	assertTrue(users.contains(ALYSSA));
    }
}
