
package Application.entities.user;

import Application.entities.AddressEntity;
import Application.repo.AddressRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import Application.entities.UserEntity;
import Application.repo.UserRepository;

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


	private final UserEntity ALYSSA = new UserEntity();
	private final UserEntity ALYSSADUPLICATE = new UserEntity();
	private final AddressEntity addressEntity = new AddressEntity();
	private final String ANICKNAME = "P. Hacker";
	
	@Autowired
	UserRepository userRepo;
	@Autowired
	AddressRepository addressRepository;

	@Before
	public void buildEntities() {
		addressEntity.setStreet("Chuguev");
		addressEntity.setApartment(33);
		addressEntity.setBuilding(3);
		ALYSSA.setNickname(ANICKNAME);
		ALYSSADUPLICATE.setNickname(ANICKNAME);
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void givenDuplicateUsersSaveAndGetException() {
		userRepo.save(ALYSSA); 
		userRepo.save(ALYSSADUPLICATE);
	}

}