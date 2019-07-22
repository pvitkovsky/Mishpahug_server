package application.entities.user;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
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

import application.models.user.UserEntity;
import application.repositories.UserRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class UserEntityTest {

	private UserEntity ALYSSA = new UserEntity("Alyssa", "p_hacker@sicp.edu");
	private final UserEntity ALYSSADUPLICATE = new UserEntity("Alyssa", "p_hacker@sicp.edu");

	@Autowired
	UserRepository userRepo;

	@Before
	public void buildEntities() {
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
		userRepo.save(new UserEntity("A", "a@dot.com"));
		userRepo.save(new UserEntity("A", "b@dot.com"));
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void givenDuplicateUsersByEmailSaveAndGetException() {
		userRepo.save(new UserEntity("A", "a@dot.com"));
		userRepo.save(new UserEntity("B", "a@dot.com"));
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