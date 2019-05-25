package application.integration;

import static org.junit.Assert.assertEquals;

import application.utils.Converter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import application.dto.UserDTO;
import application.entities.UserEntity;
import application.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LoginWebRequestTest {

	private UserEntity ALYSSA;
	private UserDTO alyssaDTO = new UserDTO();
	

    @Autowired
    UserRepository userRepo;
    @LocalServerPort
    private int port;
    private RestTemplate restTemplate = new RestTemplate();

    @Before
    public void buildEntities() {
    	alyssaDTO.setUserName("Alyssa");
    	alyssaDTO.setEncryptedPassword("1337");
    	alyssaDTO.setConfirmedPassword("1337");
    	ALYSSA = Converter.entityFromDTO(alyssaDTO);
        restTemplate.setRequestFactory(new HttpComponentsAsyncClientHttpRequestFactory());
    }

    @Test
    public void shouldBeSaved() throws Exception {
//        userRepo.save(ALYSSA);
//        assertEquals(userRepo.findById(ALYSSA.getId()), ALYSSA);
    }

  
    
    //TODO: login test please
}
