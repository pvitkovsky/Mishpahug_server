package application.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
import application.dto.UserDTO;
import application.entities.UserEntity;
import application.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserWebRequestTest {

    private final UserEntity ALYSSA = new UserEntity("Alyssa", "p_hacker@sicp.edu");
    private final HttpHeaders headers = new HttpHeaders();
    private String token;
    
    @Autowired
    UserRepository userRepo;
    
    @LocalServerPort
    private int port;
    private RestTemplate restTemplate = new RestTemplate();

    @Before
    public void buildEntities() { //TODO: save token manually instead of doing login;
    	
    	System.out.println("Alyssa " + ALYSSA);
    	ALYSSA.setEncrytedPassword(DigestUtils.md5Hex(ALYSSA.getUserName()));
    	userRepo.save(ALYSSA);
    	userRepo.flush();
    	
        restTemplate.setRequestFactory(new HttpComponentsAsyncClientHttpRequestFactory());
      
        token =  this.restTemplate.exchange("http://localhost:" + port + "/user/login", HttpMethod.POST,
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
    public void getAllShouldReturnAllUsers() throws Exception {
    	 userRepo.save(ALYSSA);
        
        Collection<UserEntity> users = this.restTemplate.exchange("http://localhost:" + port + "/user/", HttpMethod.GET,
                new HttpEntity<String>(headers),
                new ParameterizedTypeReference<Collection<UserEntity>>() {
                }).getBody();
        assertTrue(users.contains(ALYSSA));
        assertTrue(users.size() > 1);
    }
    
    @Test
    public void updateAllShouldReturnUpdatedUser() throws Exception {
       
    	String updatedFirstName = "Alyssa_Updated";
    	Map<String,String> updateMap = new HashMap<>();
    	updateMap.put("firstname", updatedFirstName);
        HttpEntity<Map<String,String>> updateRequest = new HttpEntity<>(updateMap, headers);
        UserDTO updated = this.restTemplate.exchange("http://localhost:" + port + "/user/" + ALYSSA.getId(), HttpMethod.PUT,
        		updateRequest,
                new ParameterizedTypeReference<UserDTO>() {
                }).getBody();
        assertEquals(updated.getFirstName(),updatedFirstName);
        
        //assertEquals(ALYSSA.getFirstName(), updatedFirstName);
        //TODO: test against real database record please;
    }
    

    @Test
    public void testFiltring() {
        System.out.println("Between dates filter >>>");
        Collection<UserEntity> users = this.restTemplate.exchange("http://localhost:" + port + "/user/?dateOfBirth=1980-01-01&dateOfBirth=2000-01-01", HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Collection<UserEntity>>() {
                }).getBody();
        users.forEach((data) -> System.out.println("user : " + data));
        assertTrue(users.size() > 1);
        System.out.println("lastname *man* filter >>>");
        users = this.restTemplate.exchange("http://localhost:" + port + "/user/?lastName=man", HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Collection<UserEntity>>() {
                }).getBody();
        users.forEach((data) -> System.out.println("user : " + data));
        assertTrue(users.size() > 0);
        System.out.println("lastname *man* and between dates filter >>>");
        users = this.restTemplate.exchange("http://localhost:" + port + "/user/?lastName=man&dateOfBirth=1980-01-01&dateOfBirth=2000-01-01", HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Collection<UserEntity>>() {
                }).getBody();
        users.forEach((data) -> System.out.println("user : " + data));
        assertTrue(users.size() > 0);
        System.out.println("marital status: divorced filter >>>");
        users = this.restTemplate.exchange("http://localhost:" + port + "/user/?maritalStatus.name=Divorced", HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Collection<UserEntity>>() {
                }).getBody();
        users.forEach((data) -> System.out.println("user : " + data));
        assertTrue(users.size() > 0);
    }
}
