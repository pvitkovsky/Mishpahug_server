package application.relations.user_event;

import application.entities.EventEntity;
import application.entities.SubscriptionEntity;
import application.entities.UserEntity;
import application.repositories.EventRepository;
import application.repositories.SubscriptionRepository;
import application.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class UserEventGuestTest {

	private final UserEntity ALYSSA = new UserEntity("Alyssa", "p_hacker@sicp.edu");
	private final UserEntity BEN = new UserEntity("Ben", "bitdiddle@sicp.edu");
	private final LocalDate TDATE = LocalDate.of(2190, 1, 1);
	private final LocalTime TTIME = LocalTime.of(23, 59);
	private EventEntity GUESTING;
	private SubscriptionEntity AGUESTING;

	@Autowired
	UserRepository userRepo;

	@Autowired
	EventRepository eventRepo;

	@Autowired
	SubscriptionRepository subscriptionRepo;

	@Before
	public void buildEntities() {
		userRepo.save(BEN);
		userRepo.save(ALYSSA);
		GUESTING = new EventEntity(BEN, TDATE, TTIME);
		eventRepo.save(GUESTING); 
		AGUESTING = new SubscriptionEntity(ALYSSA, GUESTING); 
		subscriptionRepo.save(AGUESTING);
	}

	@Test(expected = IllegalArgumentException.class)
	public void onUnexistentSubscriptionUnsubscriptionThrow() {

		AGUESTING.nullifyForRemoval();

	}
	
	@Test
	public void onSubscriptionSaveReadUserAndEvent() {
		
		assertTrue(subscriptionRepo.existsById(AGUESTING.getId()));

		UserEntity savedA = userRepo.findById(ALYSSA.getId()).get();
		EventEntity savedE = eventRepo.findById(GUESTING.getId()).get();

		UserEntity savedAfromRelation = AGUESTING.getGuest();
		EventEntity savedEfromRelation = AGUESTING.getEvent();
		assertTrue(savedA.equals(ALYSSA));
		assertTrue(savedAfromRelation.equals(ALYSSA));
		assertTrue(savedA.equals(savedAfromRelation));
		assertTrue(savedE.equals(GUESTING));
		assertTrue(savedEfromRelation.equals(GUESTING));
		assertTrue(savedE.equals(savedEfromRelation));

	}
	
	@Test
	public void findEventBySubs() {

		List<EventEntity> events = subscriptionRepo.getEventsForGuest(ALYSSA);
		assertEquals(events.size(), 1);
		assertTrue(events.contains(GUESTING));

	}

	@Test
	public void findUserBySubs() {

		List<UserEntity> guests = subscriptionRepo.getGuestsForEvent(GUESTING);
		assertEquals(guests.size(), 1);
		assertTrue(guests.contains(ALYSSA));
	}
	
	
	/**
	 * Deleting user and testing that its subscriptions are unsubscribed automatically;
	 */
	@Test
	public void onGuestDeleteEventRemains() {

		ALYSSA.putIntoDeletionQueue();
		AGUESTING.putIntoDeletionQueue();
		subscriptionRepo.delete(AGUESTING); //TODO: not automatic
		userRepo.delete(ALYSSA);

		assertTrue(eventRepo.existsById(GUESTING.getId()));
		assertFalse(userRepo.existsById(ALYSSA.getId()));
		assertFalse(subscriptionRepo.existsById(AGUESTING.getId()));
		
	}
	
	/**
	 * Deleting event and testing that its subscriptions are unsubscribed automatically;
	 * 
	 */
	@Test
	public void onEventDeleteGuestRemains() {
	
		GUESTING.putIntoDeletionQueue();
		AGUESTING.putIntoDeletionQueue();
		subscriptionRepo.delete(AGUESTING); //TODO: not automatic
		eventRepo.delete(GUESTING);
		
		assertTrue(userRepo.existsById(ALYSSA.getId()));
		assertTrue(userRepo.existsById(BEN.getId()));

		assertEquals(eventRepo.count(), 0);
		assertEquals(subscriptionRepo.count(), 0);
	}
}