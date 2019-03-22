
package application.entities.user;

import application.entities.AddressEntity;
import application.repositories.AddressRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import application.entities.UserEntity;
import application.repositories.UserRepository;

/**
 * Relation: OneToMany User is the primary entity. Event must have a user as its
 * owner.
 * 
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class UserEntityTest {


	private UserEntity ALYSSA = new UserEntity();
	private final UserEntity ALYSSADUPLICATE = new UserEntity();
	private final AddressEntity addressEntity = new AddressEntity();
	
	@Autowired
	UserRepository userRepo;
	@Autowired
	AddressRepository addressRepository;

	@Before
	public void buildEntities() {
		addressEntity.setStreet("Chuguev");
		addressEntity.setApartment(33);
		addressEntity.setBuilding(3);
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void givenDuplicateUsersSaveAndGetException() {
		ALYSSA.setLastName("lhlkhl");
		ALYSSA.setFirstName("Alise");
		ALYSSA.setUserName("aliseS");
		ALYSSA.setEMail("aliseS@gmail.com");
		ALYSSA.setEncrytedPassword("ghluikgluglujgog");
		userRepo.save(ALYSSA);
		userRepo.save(ALYSSADUPLICATE);
	}
	@Test
	public void getByName() {
		ALYSSA.setLastName("lhlkhl");
		ALYSSA.setFirstName("Alise");
		ALYSSA.setUserName("aliseS");
		ALYSSA.setEMail("aliseS@gmail.com");
		userRepo.save(ALYSSA);
		System.out.println (userRepo.findByUserName("aliseS"));
	}

}