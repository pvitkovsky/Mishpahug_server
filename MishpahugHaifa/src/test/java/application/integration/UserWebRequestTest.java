package application.integration;

import application.entities.UserEntity;
import application.repositories.UserRepository;
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

import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserWebRequestTest {

    private final UserEntity ALYSSA = new UserEntity();
    @Autowired
    UserRepository userRepo;
    @LocalServerPort
    private int port;
    private RestTemplate restTemplate = new RestTemplate();

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
                new ParameterizedTypeReference<Collection<UserEntity>>() {
                }).getBody();
        assertTrue(users.contains(ALYSSA));
        assertTrue(users.size() > 1);
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
    }
}
