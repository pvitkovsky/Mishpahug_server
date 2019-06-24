package application.relations.user_event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

import application.models.event.EventEntity;
import application.models.event.EventOwner;
import application.models.event.EventRepository;
import application.models.relation.SubscriptionEntity;
import application.models.relation.SubscriptionRepository;
import application.models.user.UserEntity;
import application.models.user.UserRepository;

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

	private final UserEntity ALYSSA = new UserEntity("Alyssa", "p_hacker@sicp.edu");
	private final UserEntity BEN = new UserEntity("Ben", "bitdiddle@sicp.edu");
	private final LocalDate TDATE = LocalDate.of(2190, 1, 1);
	private final LocalTime TTIME = LocalTime.of(23, 59);
	private EventEntity ABTEST; 
	private EventEntity BATEST;
	private SubscriptionEntity ABSUB;
	private SubscriptionEntity BASUB;


	@PersistenceContext // https://www.javabullets.com/access-entitymanager-spring-data-jpa/
	private EntityManager em;	
	
	@Autowired
	UserRepository userRepo;

	@Autowired
	EventRepository eventRepo;
	
	@Autowired
	SubscriptionRepository subscriptionRepo;


	@Before
	public void buildEntities() {
		userRepo.save(ALYSSA);
		userRepo.save(BEN);
		ABTEST = new EventEntity(BEN.getId(), TDATE, TTIME);
		BATEST = new EventEntity(ALYSSA.getId(), TDATE, TTIME);
		eventRepo.save(ABTEST);  //TODO: where is cascade?!
		eventRepo.save(BATEST);
		ABSUB = new SubscriptionEntity(ABTEST.getId(), ALYSSA.getId());
		BASUB = new SubscriptionEntity(BATEST.getId(), BEN.getId());
		subscriptionRepo.save(ABSUB);
		subscriptionRepo.save(BASUB);
	}

	/**
	 * Checking that Event.setUserEntityOwner creates bidirectional link; 
	 * Checking cascade save; 
	 * Checking equals in User and Event; 
	 */
	@Test
	public void onUserSaveReadEvent() {
		
		assertTrue(userRepo.existsById(ALYSSA.getId()));
		assertTrue(eventRepo.existsById(BATEST.getId()));
		
		UserEntity savedA = userRepo.findById(ALYSSA.getId()).get();
		EventEntity savedE = eventRepo.findById(BATEST.getId()).get();
		Integer savedAiDfromEvent = savedE.getUserEntityOwner().getId();
		
		assertTrue(savedA.equals(ALYSSA));
		assertTrue(savedAiDfromEvent.equals(ALYSSA.getId()));
		assertTrue(savedA.getId().equals(savedAiDfromEvent));

		assertTrue(savedE.equals(BATEST));
	}

	/**
	 * Checking that toString works in the bidirectional relation;
	 */
	@Test
	public void toStringBiDir() {

		UserEntity savedA = userRepo.findById(ALYSSA.getId()).get();
		EventEntity savedE = eventRepo.findById(BATEST.getId()).get();
		
		System.out.println(savedA); //TODO: toString w/o println pls
		System.out.println(savedE);

	}
	
//	/**
//	 * This tests User.transferEvent;
//	 * Transfer Event not in the API, but useful as a tool for testing how relation works;
//	 */
//	@Test
//	public void onTransferEvent() { //TODO: fix me please, remove method wants to delete the event but it's not pending for deletion
//
//		BATEST.setUserEntityOwner(ALYSSA);
//		userRepo.save(ALYSSA);
//
//		ALYSSA.transferOwnedEvent(BATEST, BEN);
//		userRepo.save(ALYSSA);
//		userRepo.save(BEN);
//
//		UserEntity savedA = userRepo.findById(ALYSSA.getId()).get();
//		UserEntity savedB = userRepo.findById(BEN.getId()).get();
//
//		assertTrue(savedA.getEventEntityOwner().size() == 0);
//		assertTrue(savedB.getEventEntityOwner().size() == 1);
//		assertFalse(savedA.getEventEntityOwner().contains(BATEST));
//		assertTrue(savedB.getEventEntityOwner().contains(BATEST));
//		assertTrue(eventRepo.existsById(BATEST.getId()));
//		assertEquals(eventRepo.findById(BATEST.getId()).get().getUserEntityOwner(), BEN);
//
//	}
//	
//	@Test(expected = IllegalArgumentException.class)
//	public void removeEventOfAnotherOwner() {
//		
//		BATEST.setUserEntityOwner(ALYSSA);
//		BEN.transferOwnedEvent(BATEST, ALYSSA);
//		
//	}

	/**
	 * Deleting user and testing its unsubscribeAll() to ensure that he's unsubcribed from everywhere and 
	 * all his/her owned events clear themselves of tuests;
	 */
	@Test
	public void onUserDeleteEventNotRemains() {

		assertTrue(subscriptionRepo.existsById(ABSUB.getId()));
		assertTrue(subscriptionRepo.existsById(BASUB.getId()));

		
		List<EventEntity> aEvents = eventRepo.getByEventOwner_Id(ALYSSA.getId()); //TODO: not automatic removal of owned evemts, and of two sides of subscriptions 
		aEvents.forEach(event ->  { 													 //can't use removebyEventId
			List<SubscriptionEntity> subs = subscriptionRepo.findById_eventId(event.getId());
			subs.forEach(SubscriptionEntity::putIntoDeletionQueue);
			subscriptionRepo.deleteAll(subs);
		});
		aEvents.forEach(EventEntity::putIntoDeletionQueue);
		eventRepo.deleteAll(aEvents);
		
		List<SubscriptionEntity> visits = subscriptionRepo.findById_guestId(ALYSSA.getId()); //can't use removebyGuestId
		visits.forEach(SubscriptionEntity::putIntoDeletionQueue);
		subscriptionRepo.deleteAll(visits);

		ALYSSA.putIntoDeletionQueue();
		userRepo.delete(ALYSSA);
		
				
				
		assertEquals(userRepo.count(), 1);
		assertEquals(eventRepo.count(), 1);
		
		assertFalse(userRepo.existsById(ALYSSA.getId()));
		assertFalse(eventRepo.existsById(BATEST.getId()));
		assertFalse(subscriptionRepo.existsById(BASUB.getId()));
	
		assertTrue(userRepo.existsById(BEN.getId()));
		assertTrue(eventRepo.existsById(ABTEST.getId()));
		assertFalse(subscriptionRepo.existsById(ABSUB.getId()));
		//No Alyssa in the system...
		

	}


}