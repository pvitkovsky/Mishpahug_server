package application.entities.user;

import application.entities.UserEntity;
import application.entities.properties.AddressEntity;
import application.repositories.AddressRepository;
import application.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class UserEntityTest {

	private UserEntity ALYSSA = new UserEntity("Alyssa", "p_hacker@sicp.edu");
	private final UserEntity ALYSSADUPLICATE = new UserEntity("Alyssa", "p_hacker@sicp.edu");
	private final AddressEntity AADDRESS = new AddressEntity();
	private final Map<String, String> AUPDATE = new HashMap<>();
	

	@Autowired
	UserRepository userRepo;
	@Autowired
	AddressRepository addressRepository;

	@Before
	public void buildEntities() {
		AADDRESS.setStreet("Chuguev");
		AADDRESS.setApartment(33);
		AADDRESS.setBuilding(3);
		
		userRepo.save(ALYSSA);
	}

	@Test(expected = ConstraintViolationException.class)
	public void givenUserWithoutUserNameSaveAndGetException() {
		userRepo.save(new UserEntity());
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void givenDuplicateUsersSaveAndGetException() {	
		userRepo.save(ALYSSADUPLICATE);
	}

	@Test(expected = EntityNotFoundException.class)
	public void getNonExistentEntityAndGetException() {	
		List<Integer> validIds = userRepo.findAll().stream().map(v -> v.getId()).collect(Collectors.toList());
		validIds.sort((n, m) -> - n + m); // descending order
		Integer invalidId = validIds.get(0) + 1;
		userRepo.getOne(invalidId).deactivate(); // need to mutate or produce to trigger lazy load; syso println would work too;
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void givenDuplicateUsersByNameSaveAndGetException() {
		userRepo.save(UserEntity.builder().userName("A").eMail("a@dot.com").build());
		userRepo.save(UserEntity.builder().userName("A").eMail("b@dot.com").build());
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void givenDuplicateUsersByEmailSaveAndGetException() {
		userRepo.save(UserEntity.builder().userName("A").eMail("a@dot.com").build());
		userRepo.save(UserEntity.builder().userName("B").eMail("a@dot.com").build());
	}
	
	@Test
	public void getByName() {
	
		ALYSSA.setLastName("lhlkhl");
		ALYSSA.setFirstName("Alise");
		ALYSSA.setEncrytedPassword("ghluikgluglujgog");
		
		assertEquals(userRepo.findByUserName("Alyssa"), ALYSSA);
	}
	



	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void onUserDeleteWithoutQueueThrows() {

		userRepo.delete(ALYSSA);
		userRepo.flush();
		
	}
	
	@Test
	public void onUserDeleteWithQueueWorks() {

		ALYSSA.putIntoDeletionQueue();
		userRepo.delete(ALYSSA);
		
		assertEquals(userRepo.count(), 0);
		
	}
}