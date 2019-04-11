package application.entities.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

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

import application.entities.AddressEntity;
import application.entities.UserEntity;
import application.repositories.AddressRepository;
import application.repositories.UserRepository;


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
	public void savedUserChangeStatusWithIllegalStringThrows() {
		
		AUPDATE.put("status", "foo");
		userRepo.update(ALYSSA, AUPDATE);
	}

	@Test
	public void savedUserChangeStatusWithLegalStringWorks() {
		
		assertTrue(ALYSSA.isEnabled());
		
		AUPDATE.put("status", "DEACTIVATED");
		userRepo.update(ALYSSA, AUPDATE);
		assertFalse(ALYSSA.isEnabled());
		AUPDATE.clear();
	
		AUPDATE.put("status", "ACTIVE");
		userRepo.update(ALYSSA, AUPDATE);
		assertTrue(ALYSSA.isEnabled());
		AUPDATE.clear();
		
		AUPDATE.put("status", "PENDINGFORDELETION");
		userRepo.update(ALYSSA, AUPDATE);
		assertTrue(ALYSSA.isPendingForDeletion());
		AUPDATE.clear();
		
		userRepo.delete(ALYSSA);
		assertEquals(userRepo.count(), 0);
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