package application.relations.user_event;

import application.entities.EventEntity;
import application.entities.SubscriptionEntity;
import application.entities.UserEntity;
import application.repositories.SubscriptionRepository;
import application.repositories.EventRepository;
import application.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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

	private final UserEntity ALYSSA = new UserEntity();
	private final UserEntity BEN = new UserEntity();
	private final LocalDate TDATE = LocalDate.of(2190, 1, 1);
	private final LocalTime TTIME = LocalTime.of(23, 59);
	private final EventEntity GUESTING = new EventEntity(TDATE, TTIME);
	private SubscriptionEntity AGUESTING;

	@Autowired
	UserRepository userRepo;

	@Autowired
	EventRepository eventRepo;

	@Autowired
	SubscriptionRepository eventGuestRepo;

	@Before
	public void buildEntities() {
		ALYSSA.setEMail("p_hacker@sicp.edu");
		BEN.setEMail("bitdiddle@sicp.edu");
		userRepo.save(BEN);
		userRepo.save(ALYSSA);
		GUESTING.setUserEntityOwner(BEN);
		eventRepo.save(GUESTING); //TODO: why cascade stopped working?
		AGUESTING = new SubscriptionEntity(ALYSSA, GUESTING);
		System.out.println(GUESTING);
	}

	@Test(expected = IllegalArgumentException.class)
	public void onUnexistentSubscriptionUnsubscriptionThrow() {

		AGUESTING.nullifyForRemoval();

	}
	
	@Test
	public void onSubscriptionSaveReadUserAndEvent() {
		
		assertTrue(eventGuestRepo.existsById(AGUESTING.getId()));
		assertTrue(GUESTING.getSubscriptions().contains(AGUESTING));

		UserEntity savedA = userRepo.findById(ALYSSA.getId()).get();
		EventEntity savedE = eventRepo.findById(GUESTING.getId()).get();
		SubscriptionEntity savedSubcsrUser = savedA.getSubscriptions().iterator().next();
		SubscriptionEntity savedSubcsrEvent = savedE.getSubscriptions().iterator().next();
		assertEquals(AGUESTING, savedSubcsrUser);
		assertEquals(AGUESTING, savedSubcsrEvent);
		assertEquals(savedSubcsrUser, savedSubcsrEvent);

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

		List<EventEntity> events = eventGuestRepo.getEventsForGuest(ALYSSA);
		//List<EventEntity> events = eventGuestRepo.findByUserGuest(ALYSSA); //TODO: converter
		assertEquals(events.size(), 1);
		assertTrue(events.contains(GUESTING));

	}

	@Test
	public void findUserBySubs() {

		List<UserEntity> guests = eventGuestRepo.getGuestsForEvent(GUESTING);
		//List<UserEntity> guests = eventGuestRepo.findByEvent(GUESTING); //TODO: converter
		assertEquals(guests.size(), 1);
		assertTrue(guests.contains(ALYSSA));
	}
	
	
	@Test
	public void onGuestDeleteEventRemains() {

		ALYSSA.putIntoDeletionQueue();
		userRepo.delete(ALYSSA);

		assertTrue(eventRepo.existsById(GUESTING.getId()));
		assertFalse(userRepo.existsById(ALYSSA.getId()));
		assertFalse(eventGuestRepo.existsById(AGUESTING.getId()));
		assertFalse(GUESTING.getSubscriptions().contains(AGUESTING));
		assertEquals(GUESTING.getSubscriptions().size(), 0);
		
	}
	
	/**
	 * Deleting event and testing that its subscribers are unsubscribed automatically;
	 * 
	 */
	@Test
	public void onEventDeleteGuestRemains() {
	
		GUESTING.putIntoDeletionQueue();
		eventRepo.delete(GUESTING);

		assertTrue(userRepo.existsById(ALYSSA.getId()));
		assertTrue(userRepo.existsById(BEN.getId()));

		assertEquals(eventRepo.count(), 0);
		assertEquals(eventGuestRepo.count(), 0);
		assertEquals(BEN.getEventEntityOwner().size(), 0);
		assertEquals(ALYSSA.getSubscriptions().size(), 0);
	}
	
	
}