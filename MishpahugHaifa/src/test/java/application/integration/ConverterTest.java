package application.integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import application.dto.UserDTO;
import application.models.user.UserEntity;
import application.repositories.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class ConverterTest {

	private UserEntity ALYSSA;
	private UserDTO alyssaDTO = new UserDTO();

	@Autowired
	ApplicationContext context;

	@Autowired
	UserRepository userRepo;

//    TODO: test config doesn't see IConverter bean

//    @Autowired
//    @Qualifier("userConverter")
//    IConverter<UserEntity, UserDTO> converter;

	@Before
	public void buildEntities() {
		alyssaDTO.setUserName("Alyssa");
		alyssaDTO.setEncryptedPassword("1337");
		alyssaDTO.setConfirmedPassword("1337");
//    	ALYSSA = converter.entityFromDTO(alyssaDTO);

	}

	@Test
	public void shouldBeSaved() throws Exception {

		/*
		 * Somehow the test config doesn't see the IConverter beans
		 */
		for (String name : context.getBeanDefinitionNames()) {
			//System.out.println(name);
		}
//        userRepo.save(ALYSSA);
//        assertEquals(userRepo.findById(ALYSSA.getId()), ALYSSA);
	}

}
