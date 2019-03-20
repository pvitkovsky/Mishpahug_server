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
public class UserEventGuestTest {

	private final UserEntity ALYSSA = new UserEntity();
	private final UserEntity BEN = new UserEntity();
	private final EventEntity GUESTING = new EventEntity();

	@PersistenceContext // https://www.javabullets.com/access-entitymanager-spring-data-jpa/
	private EntityManager em;	@Autowired
	
	UserRepository userRepo;

	@Autowired
	EventRepository eventRepo;

	@Before
	public void buildEntities() {
	}

	/**
	 * Checking that Event.setUserEntityOwner creates bidirectional link; 
	 * Checking cascade save; 
	 * Checking equals in User and Event; 
	 */
	@Test
	public void onUserSaveReadEvent() {
		
		BEN.makeOwner(GUESTING);
		userRepo.save(BEN);
		userRepo.save(ALYSSA); //TODO: automatic cascade please;
	
		GUESTING.subscribe(ALYSSA);
		eventRepo.save(GUESTING);
		
		assertTrue(userRepo.existsById(ALYSSA.getId()));
		assertTrue(eventRepo.existsById(GUESTING.getId()));
		
		UserEntity savedA = userRepo.findById(ALYSSA.getId()).get();
		EventEntity savedE = eventRepo.findById(GUESTING.getId()).get();
		EventEntity savedEfromUser = savedA.getEventEntityGuest().iterator().next();
		UserEntity savedAfromEvent = savedE.getUserItemsGuestsOfEvents().iterator().next();
		
		assertTrue(savedA.equals(ALYSSA));
		assertTrue(savedAfromEvent.equals(ALYSSA));
		assertTrue(savedA.equals(savedAfromEvent));

		assertTrue(savedE.equals(GUESTING));
		assertTrue(savedEfromUser.equals(GUESTING));
		assertTrue(savedE.equals(savedEfromUser));

	}
	
	/**
	 * Shows that you have to clear user of events before deleting; 
	 */
	@Test
	public void onUserDeleteEventRemains() {
		
		BEN.makeOwner(GUESTING);
		userRepo.save(BEN);	
		userRepo.save(ALYSSA);	 //TODO: maintain saving in model, UserEntity doesn't persist from subscription;
		
		GUESTING.subscribe(ALYSSA);
		GUESTING.unSubscribe(ALYSSA); //TODO: maintain unsubscription in model; 
		userRepo.delete(ALYSSA);
		
		assertTrue(eventRepo.existsById(GUESTING.getId()));
		assertFalse(userRepo.existsById(ALYSSA.getId()));
		assertFalse(GUESTING.getUserItemsGuestsOfEvents().contains(ALYSSA));
	}
	
	@Test
	public void onEventDeleteUserRemains() {

		BEN.makeOwner(GUESTING);
		userRepo.save(BEN);
		userRepo.save(ALYSSA); //TODO: maintain saving in model, UserEntity doesn't persist from subscription;
		GUESTING.subscribe(ALYSSA);
		
		BEN.removeOwnedEvent(GUESTING); 

		assertFalse(eventRepo.existsById(GUESTING.getId()));
		assertTrue(userRepo.existsById(ALYSSA.getId()));
		assertFalse(ALYSSA.getEventEntityGuest().contains(GUESTING));

	}




}