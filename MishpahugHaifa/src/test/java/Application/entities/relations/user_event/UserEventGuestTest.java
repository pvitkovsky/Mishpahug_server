//package Application.entities.relations.user_event;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import Application.entities.EventEntity;
//import Application.entities.UserEntity;
//import Application.repo.EventRepository;
//import Application.repo.UserRepository;
//
///**
// * Relation: 
// * OneToMany; User is the primary entity, that has a Set of Events.  
// * Event has only one User as its owner. Updates are casceded from User. 
// */
//@RunWith(SpringRunner.class)
//@DataJpaTest
//@ActiveProfiles("test")
//@Transactional
//public class UserEventGuestTest {
//
//	private final UserEntity ALYSSA = new UserEntity();
//	private final UserEntity BEN = new UserEntity();
//	private final EventEntity GUESTING = new EventEntity();
//
//	@PersistenceContext // https://www.javabullets.com/access-entitymanager-spring-data-jpa/
//	private EntityManager em;	@Autowired
//	
//	UserRepository userRepo;
//
//	@Autowired
//	EventRepository eventRepo;
//
//	@Before
//	public void buildEntities() {
//		ALYSSA.setNickname("Alyssa");
//		BEN.setNickname("Ben");
//	}
//
//	/**
//	 * Checking that Event.setUserEntityOwner creates bidirectional link; 
//	 * Checking cascade save; 
//	 * Checking equals in User and Event; 
//	 */
//	@Test
//	public void onUserSaveReadEvent() {
//
//
//		GUESTING.setUserEntityOwner(BEN);
//		userRepo.save(BEN);
//		
//		GUESTING.subscribe(ALYSSA);
//		userRepo.save(ALYSSA);
//		
//		assertTrue(userRepo.existsById(ALYSSA.getId()));
//		assertTrue(eventRepo.existsById(GUESTING.getId()));
//		
//		UserEntity savedA = userRepo.findById(ALYSSA.getId()).get();
//		EventEntity savedE = eventRepo.findById(GUESTING.getId()).get();
//		EventEntity savedEfromUser = savedA.getEventItemsGuest().iterator().next();
//		UserEntity savedAfromEvent = savedE.getUserItemsGuestsOfEvents().iterator().next();
//		
//		assertTrue(savedA.equals(ALYSSA));
//		assertTrue(savedAfromEvent.equals(ALYSSA));
//		assertTrue(savedA.equals(savedAfromEvent));
//
//		assertTrue(savedE.equals(GUESTING));
//		assertTrue(savedEfromUser.equals(GUESTING));
//		assertTrue(savedE.equals(savedEfromUser));
//
//	}
//	
//	@Test
//	public void onUserDeleteEventRemains() {
//
//
//		GUESTING.setUserEntityOwner(BEN);
//		userRepo.save(BEN);
//		
//		GUESTING.subscribe(ALYSSA);
//		userRepo.save(ALYSSA);
//		
//		assertTrue(eventRepo.existsById(GUESTING.getId()));
//		
//		GUESTING.unsubscribe(ALYSSA); // has to do this; 
//		userRepo.delete(ALYSSA);
//		
//		assertTrue(eventRepo.existsById(GUESTING.getId()));
//		assertFalse(userRepo.existsById(ALYSSA.getId()));
//
//	}
//	
//	@Test
//	public void onEventDeleteUserRemains() {
//
//
//		GUESTING.setUserEntityOwner(BEN);
//		userRepo.save(BEN);
//		
//		GUESTING.subscribe(ALYSSA);
//		userRepo.save(ALYSSA);
//		
//		assertTrue(eventRepo.existsById(GUESTING.getId()));
//		EventEntity savedE = eventRepo.findById(GUESTING.getId()).get();
//		System.out.println(savedE);
//		
//		GUESTING.unsubscribe(ALYSSA);
//		eventRepo.delete(GUESTING);
//
//		assertFalse(eventRepo.existsById(GUESTING.getId()));
//		assertTrue(userRepo.existsById(ALYSSA.getId()));
//
//
//	}
//
//
//
//
//}