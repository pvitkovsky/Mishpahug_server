package application.integration;

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
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    public void getByIdShouldReturnUser() throws Exception {
       
    	
    	UserEntity AlyssaHTTP = this.restTemplate.exchange("http://localhost:" + port + "/user/" + ALYSSA.getId(), HttpMethod.GET,
                new HttpEntity<String>(headers),
                new ParameterizedTypeReference<UserEntity>() {
                }).getBody();
        assertEquals(AlyssaHTTP, ALYSSA);
        

    }

//TODO: Stable data with working user index pulled from the database; 
//    @Test
//    public void testEventListByGuest(){
//        Collection<EventDTO> events = this.restTemplate.exchange("http://localhost:" + port + "/user/6/subscribes", HttpMethod.GET,
//                new HttpEntity<String>(headers),
//                new ParameterizedTypeReference<Collection<EventDTO>>() {
//                }).getBody();
//        System.out.println("" + events);
//        assertTrue(events.size() >= 1);
//    }
//
//    @Test
//    public void testEventListByOwner(){
//        Collection<EventDTO> events = this.restTemplate.exchange("http://localhost:8080/user/12/events", HttpMethod.GET,
//                new HttpEntity<String>(headers),
//                new ParameterizedTypeReference<Collection<EventDTO>>() {
//                }).getBody();
//        System.out.println("" + events);
//        assertTrue(events.size() >= 1);
//    }


    @Test
    public void updateAllShouldReturnUpdatedUser() throws Exception { //TODO: fix me pls
       
    	String updatedFirstName = "Alyssa_Updated";
    	Map<String,String> updateMap = new HashMap<>();
    	updateMap.put("firstName", updatedFirstName);
        HttpEntity<Map<String,String>> updateRequest = new HttpEntity<>(updateMap, headers);
        UserDTO updated = this.restTemplate.exchange("http://localhost:" + port + "/user/" + ALYSSA.getId(), HttpMethod.PUT,
        		updateRequest,
                new ParameterizedTypeReference<UserDTO>() {
                }).getBody();
        assertEquals(updated.getFirstName(),updatedFirstName);
        
    }
    

//    @Test
//    public void testFiltring() { //TODO: stable data w/o randoms, asserts
//        Collection<UserEntity> users = this.restTemplate.exchange("http://localhost:" + port + "/user/?dateOfBirth=1900-01-01&dateOfBirth=2100-01-01", HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<Collection<UserEntity>>() {
//                }).getBody();
//        // assertTrue(users.size() > 0); //TODO: fixed data;
//        users = this.restTemplate.exchange("http://localhost:" + port + "/user/?lastName=man", HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<Collection<UserEntity>>() {
//                }).getBody(); 
//        // assertTrue(users.size() > 0); //TODO: fixed data;
//        users = this.restTemplate.exchange("http://localhost:" + port + "/user/?lastName=man&dateOfBirth=1900-01-01&dateOfBirth=2100-01-01", HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<Collection<UserEntity>>() {
//                }).getBody();
//        //assertTrue(users.size() > 0); //TODO: fixed data;
//    }


}
