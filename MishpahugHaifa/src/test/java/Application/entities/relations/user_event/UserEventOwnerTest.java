package Application.entities.relations.user_event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

import Application.entities.EventEntity;
import Application.entities.UserEntity;
import Application.repo.EventRepository;
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
public class UserEventOwnerTest {

	private final UserEntity ALYSSA = new UserEntity();
	private final UserEntity BEN = new UserEntity();
	private final EventEntity TESTING = new EventEntity();

	@PersistenceContext // https://www.javabullets.com/access-entitymanager-spring-data-jpa/
	private EntityManager em;

	@Autowired
	UserRepository userRepo;

	@Autowired
	EventRepository eventRepo;

	@Before
	public void buildEntities() {
		ALYSSA.setNickname("Alyssa");
		BEN.setNickname("Ben");
	}

	/**
	 * Checking that the UserEntity persists his events automatically; Checking that
	 * toString works in the bidirectional relation;
	 */
	@Test
	public void onUserSaveReadEvent() {

		TESTING.setUserEntityOwner(ALYSSA);
		userRepo.save(ALYSSA);
		eventRepo.save(TESTING);
		assertTrue(em.find(UserEntity.class, ALYSSA.getId()) != null);

		UserEntity savedA = userRepo.findById(ALYSSA.getId()).get();
		EventEntity savedE = eventRepo.findAll().get(0);

		UserEntity savedAfromEvent = savedE.getUserEntityOwner();
		assertTrue(savedAfromEvent.equals(ALYSSA));
		System.out.println(savedAfromEvent);

		savedE = savedA.getEventEntityOwner().iterator().next();
		assertTrue(savedE.equals(TESTING));
		System.out.println(savedE);
	}

	/**
	 * Checking that the events are deleted after the UserEntity
	 */
	@Test
	public void onUserAndEventSaveDeleteUser() {

		TESTING.setUserEntityOwner(ALYSSA);

		userRepo.save(ALYSSA);
		eventRepo.save(TESTING);
		assertTrue(eventRepo.existsById(TESTING.getId()));

		userRepo.delete(ALYSSA);
		assertFalse(eventRepo.existsById(TESTING.getId()));

	}

	/**
	 * Changing the owner of the event and testing the results; Testing
	 * UserEntitys.equals();
	 */
	@Test
	public void onUserSaveChangeEvent() {

		TESTING.setUserEntityOwner(ALYSSA);
		userRepo.save(ALYSSA);
		eventRepo.save(TESTING);

		EventEntity savedE = userRepo.findById(ALYSSA.getId()).get().getEventEntityOwner().iterator().next();
		assertTrue(TESTING.equals(savedE));

		ALYSSA.transferEvent(savedE, BEN);
		userRepo.save(ALYSSA);
		userRepo.save(BEN);
		eventRepo.save(TESTING);

		UserEntity savedA = userRepo.findById(ALYSSA.getId()).get();
		UserEntity savedB = userRepo.findById(BEN.getId()).get();
		assertFalse(savedA.getEventEntityOwner().contains(TESTING));
		assertTrue(savedB.getEventEntityOwner().contains(TESTING));
		assertTrue(eventRepo.existsById(TESTING.getId()));
		assertEquals(eventRepo.findById(TESTING.getId()).get().getUserEntityOwner(), BEN);

	}

	/**
	 * This test demonstrates that UserEntitys allow us to add EventEntitys without
	 * checking if two UserEntitys share the same EventEntity;
	 */
	@Test
	public void twoOwnersSameEvent() {

		TESTING.setUserEntityOwner(ALYSSA);
		userRepo.save(ALYSSA);
		eventRepo.save(TESTING);

		ALYSSA.transferEvent(TESTING, BEN);
		userRepo.save(ALYSSA);
		userRepo.save(BEN);
		eventRepo.save(TESTING);

		UserEntity savedA = userRepo.findById(ALYSSA.getId()).get();
		UserEntity savedB = userRepo.findById(BEN.getId()).get();

		assertTrue(savedA.getEventEntityOwner().size() == 0);
		assertTrue(savedB.getEventEntityOwner().size() == 1);

	}

	@Test
	public void saveEventReadItInUserList() {

		TESTING.setUserEntityOwner(ALYSSA);
		userRepo.save(ALYSSA);
		eventRepo.save(TESTING);

		UserEntity savedA = userRepo.findById(ALYSSA.getId()).get();
		assertTrue(savedA.getEventEntityOwner().size() == 1);

	}

	@Test
	public void saveDuplicateEvent() {

		TESTING.setUserEntityOwner(ALYSSA);
		ALYSSA.addEvent(TESTING);
		ALYSSA.addEvent(TESTING);
		ALYSSA.addEvent(TESTING);

		userRepo.save(ALYSSA);
		eventRepo.save(TESTING);

		UserEntity savedA = userRepo.findById(ALYSSA.getId()).get();
		assertTrue(savedA.getEventEntityOwner().size() == 1);

	}

	@Test(expected = IllegalArgumentException.class)
	public void addEventOfAnotherOwner() {
		TESTING.setUserEntityOwner(ALYSSA);
		BEN.addEvent(TESTING);
	}

	@Test(expected = IllegalArgumentException.class)
	public void removeEventOfAnotherOwner() {
		TESTING.setUserEntityOwner(ALYSSA);
		BEN.transferEvent(TESTING, ALYSSA);
	}

}