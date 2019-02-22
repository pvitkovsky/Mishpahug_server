package Application.entities;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import Application.repo.UserRepository;

/**
 * Testing creation and deletion of entities to ensure relations work as
 * intended;
 * 
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class UserTest {

	private static final Application.entities.UserEntity ALYSSA = new UserEntity();
	
	@Autowired
	UserRepository userRepo;

	@Before
	public void clear() {
		userRepo.deleteAll();
	}
	
	@Before 
	public void buildUser() {
		ALYSSA.setFirstName("Alyssa");
	}

	

	/**
	 * Testing add, with explicit saving
	 */
	@Test
	public void addUser() {
		UserEntity createdUser = ALYSSA;
		
		userRepo.save(createdUser);
		userRepo.flush();

		UserEntity persistedUser = userRepo.findById(createdUser.getId()).get();

		assertTrue(persistedUser.equals(createdUser));
	}
}
