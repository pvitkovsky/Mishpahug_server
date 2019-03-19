package Application.relations.user_event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import Application.entities.EventEntity;
import Application.entities.EventGuestRelation;
import Application.entities.UserEntity;
import Application.repo.EventGuestRepository;
import Application.repo.EventRepository;
import Application.repo.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class UserEventGuestTest {

	private final UserEntity ALYSSA = new UserEntity();
	private final UserEntity BEN = new UserEntity();
	private final EventEntity GUESTING = new EventEntity();
	private final LocalDate TDATE = LocalDate.of(2190, 1, 1);
	private final LocalTime TTIME = LocalTime.of(23, 59);
	private final String TNAME = "TESTING";
	private final EventGuestRelation AGUESTING = new EventGuestRelation();

	@Autowired
	UserRepository userRepo;

	@Autowired
	EventRepository eventRepo;

	@Autowired
	EventGuestRepository eventGuestRepo;

	@Before
	public void buildEntities() {
		ALYSSA.setNickname("Alyssa");
		BEN.setNickname("Ben");
		GUESTING.setDate(TDATE);
		GUESTING.setTime(TTIME);
		GUESTING.setNameOfEvent(TNAME);
	}

	@Test
	public void onSubscriptionSaveReadUserAndEvent() {

		BEN.makeOwner(GUESTING);
		userRepo.save(BEN);
		userRepo.save(ALYSSA);
		AGUESTING.subscribe(ALYSSA, GUESTING);

		assertTrue(userRepo.existsById(ALYSSA.getId()));
		assertTrue(eventRepo.existsById(GUESTING.getId()));
		assertEquals(userRepo.count(), 2);
		assertEquals(eventRepo.count(), 1);

		UserEntity savedA = userRepo.findById(ALYSSA.getId()).get();
		EventEntity savedE = eventRepo.findById(GUESTING.getId()).get();
		EventGuestRelation savedSubcsrUser = savedA.getSubscriptions().iterator().next();
		EventGuestRelation savedSubcsrEvent = savedE.getSubscriptions().iterator().next();
		assertEquals(AGUESTING, savedSubcsrUser);
		assertEquals(AGUESTING, savedSubcsrEvent);
		assertEquals(savedSubcsrUser, savedSubcsrEvent);

		UserEntity savedAfromRelation = AGUESTING.getUserGuest();
		EventEntity savedEfromRelation = AGUESTING.getEvent();
		assertTrue(savedA.equals(ALYSSA));
		assertTrue(savedAfromRelation.equals(ALYSSA));
		assertTrue(savedA.equals(savedAfromRelation));
		assertTrue(savedE.equals(GUESTING));
		assertTrue(savedEfromRelation.equals(GUESTING));
		assertTrue(savedE.equals(savedEfromRelation));

	}

	@Test(expected = IllegalArgumentException.class)
	public void onMultipleSubscriptionsThrow() {

		BEN.makeOwner(GUESTING);
		userRepo.save(BEN);
		userRepo.save(ALYSSA);
		AGUESTING.subscribe(ALYSSA, GUESTING);
		AGUESTING.subscribe(ALYSSA, GUESTING);
		AGUESTING.subscribe(ALYSSA, GUESTING);

	}

	@Test(expected = IllegalArgumentException.class)
	public void onUnexistentSubscriptionUnsubscriptionThrow() {

		BEN.makeOwner(GUESTING);
		userRepo.save(BEN);
		userRepo.save(ALYSSA);
		AGUESTING.unsubscribe(ALYSSA, GUESTING);

	}

	@Test
	public void onUserDeleteEventRemains() {

		BEN.makeOwner(GUESTING);
		userRepo.save(BEN);
		userRepo.save(ALYSSA);
		AGUESTING.subscribe(ALYSSA, GUESTING);
		assertTrue(GUESTING.getUserItemsGuestsOfEvents().contains(AGUESTING));

		AGUESTING.unsubscribe(ALYSSA, GUESTING); // TODO: needs to unsub if wanting to delete -> model;
		assertFalse(GUESTING.getUserItemsGuestsOfEvents().contains(AGUESTING));
		userRepo.delete(ALYSSA);

		assertTrue(eventRepo.existsById(GUESTING.getId()));
		assertFalse(userRepo.existsById(ALYSSA.getId()));
		assertFalse(eventGuestRepo.existsById(AGUESTING.getId()));
		assertFalse(GUESTING.getUserItemsGuestsOfEvents().contains(AGUESTING));
		assertEquals(GUESTING.getSubscriptions().size(), 0);
	}

	@Test
	public void onEventDeleteUserRemains() {
		BEN.makeOwner(GUESTING);
		userRepo.save(BEN);
		userRepo.save(ALYSSA);
		AGUESTING.subscribe(ALYSSA, GUESTING);
		//
		BEN.removeOwnedEvent(GUESTING);
		AGUESTING.unsubscribe(ALYSSA, GUESTING); // TODO: needs to unsub after each remove;

		assertFalse(eventRepo.existsById(GUESTING.getId()));
		assertTrue(userRepo.existsById(ALYSSA.getId()));
		assertFalse(eventGuestRepo.existsById(AGUESTING.getId()));
		assertFalse(ALYSSA.getSubscriptions().contains(AGUESTING));
		assertEquals(ALYSSA.getSubscriptions().size(), 0);
	}

	@Test
	public void findEventBySubs() { // TODO: proper query/join for many to many access;
		BEN.makeOwner(GUESTING);
		userRepo.save(BEN);
		userRepo.save(ALYSSA);
		AGUESTING.subscribe(ALYSSA, GUESTING);

		Set<EventGuestRelation> subscriptions = ALYSSA.getSubscriptions();
		Set<EventEntity> events = subscriptions.stream().map(s -> s.getEvent()).collect(Collectors.toSet());
		assertEquals(events.size(), 1);
		assertTrue(events.contains(GUESTING));

	}

	@Test // TODO: same as above
	public void findUserBySubs() {

		BEN.makeOwner(GUESTING);
		userRepo.save(BEN);
		userRepo.save(ALYSSA);
		AGUESTING.subscribe(ALYSSA, GUESTING);

		Set<EventGuestRelation> subscriptions = GUESTING.getSubscriptions();
		Set<UserEntity> users = subscriptions.stream().map(s -> s.getUserGuest()).collect(Collectors.toSet());
		assertTrue(users.contains(ALYSSA));
	}

}