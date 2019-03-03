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
 * Relation: 
 * OneToMany; User is the primary entity, that has a Set of Events.  
 * Event has only one User as its owner. Updates are casceded from User. 
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
	private EntityManager em;	@Autowired
	
	UserRepository userRepo;

	@Autowired
	EventRepository eventRepo;

	@Before
	public void buildEntities() {
		ALYSSA.setNickname("Alyssa");
		BEN.setNickname("Ben");
	}

	/**
	 * Checking that Event.setUserEntityOwner creates bidirectional link; 
	 * Checking cascade save; 
	 * Checking equals in User and Event; 
	 */
	@Test
	public void onUserSaveReadEvent() {

		TESTING.setUserEntityOwner(ALYSSA);
		userRepo.save(ALYSSA);
		assertTrue(userRepo.existsById(ALYSSA.getId()));
		assertTrue(eventRepo.existsById(TESTING.getId()));
		
		UserEntity savedA = userRepo.findById(ALYSSA.getId()).get();
		EventEntity savedE = eventRepo.findById(TESTING.getId()).get();
		EventEntity savedEfromUser = savedA.getEventEntityOwner().iterator().next();
		UserEntity savedAfromEvent = savedE.getUserEntityOwner();
		
		assertTrue(savedA.equals(ALYSSA));
		assertTrue(savedAfromEvent.equals(ALYSSA));
		assertTrue(savedA.equals(savedAfromEvent));

		assertTrue(savedE.equals(TESTING));
		assertTrue(savedEfromUser.equals(TESTING));
		assertTrue(savedE.equals(savedEfromUser));

	}

	/**
	 * Checking that the events are automatically deleted (cascade) after the UserEntity
	 */
	@Test
	public void onUserAndEventSaveDeleteUser() {

		TESTING.setUserEntityOwner(ALYSSA);

		userRepo.save(ALYSSA);
		assertTrue(eventRepo.existsById(TESTING.getId()));

		userRepo.delete(ALYSSA);
		assertFalse(eventRepo.existsById(TESTING.getId()));

	}
	
	/**
	 * Checking that toString works in the bidirectional relation;
	 */
	@Test
	public void toStringBiDir() {

		TESTING.setUserEntityOwner(ALYSSA);
		userRepo.save(ALYSSA);

		UserEntity savedA = userRepo.findById(ALYSSA.getId()).get();
		EventEntity savedE = eventRepo.findById(TESTING.getId()).get();
		
		System.out.println(savedA); //TODO: toString w/o println pls
		System.out.println(savedE);

	}
	
	@Test
	/**
	 * Can't add event to user more than 1 time; checks hashcode of Event; 
	 */
	public void saveDuplicateEvent() {

		TESTING.setUserEntityOwner(ALYSSA);
		ALYSSA.addEvent(TESTING);
		ALYSSA.addEvent(TESTING);
		ALYSSA.addEvent(TESTING);

		userRepo.save(ALYSSA);
		UserEntity savedA = userRepo.findById(ALYSSA.getId()).get();
		assertTrue(savedA.getEventEntityOwner().size() == 1);

	}

	/**
	 * This tests User.transferEvent;
	 * Transfer Event not in the API, but useful as a tool for testing how relation works;
	 */
	@Test
	public void onTransferEvent() {

		TESTING.setUserEntityOwner(ALYSSA);
		userRepo.save(ALYSSA);

		ALYSSA.transferEvent(TESTING, BEN);
		userRepo.save(ALYSSA);
		userRepo.save(BEN);

		UserEntity savedA = userRepo.findById(ALYSSA.getId()).get();
		UserEntity savedB = userRepo.findById(BEN.getId()).get();

		assertTrue(savedA.getEventEntityOwner().size() == 0);
		assertTrue(savedB.getEventEntityOwner().size() == 1);
		assertFalse(savedA.getEventEntityOwner().contains(TESTING));
		assertTrue(savedB.getEventEntityOwner().contains(TESTING));
		assertTrue(eventRepo.existsById(TESTING.getId()));
		assertEquals(eventRepo.findById(TESTING.getId()).get().getUserEntityOwner(), BEN);

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