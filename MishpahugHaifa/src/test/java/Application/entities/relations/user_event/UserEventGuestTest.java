package Application.entities.relations.user_event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

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
		eventGuestRepo.save(AGUESTING);
		
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
	
//TODO: redo please; 
//	/**
//	 * Shows that you have to clear user of events before deleting; 
//	 */
//	@Test
//	public void onUserDeleteEventRemains() {
//		
//		BEN.makeOwner(GUESTING);
//		userRepo.save(BEN);	
//		userRepo.save(ALYSSA);	 //TODO: maintain saving in model, UserEntity doesn't persist from subscription;
//		
//		GUESTING.subscribe(ALYSSA);
//		GUESTING.unSubscribe(ALYSSA); //TODO: maintain unsubscription in model; 
//		userRepo.delete(ALYSSA);
//		
//		assertTrue(eventRepo.existsById(GUESTING.getId()));
//		assertFalse(userRepo.existsById(ALYSSA.getId()));
//		assertFalse(GUESTING.getUserItemsGuestsOfEvents().contains(ALYSSA));
//	}
//	
//	@Test
//	public void onEventDeleteUserRemains() {

//		BEN.makeOwner(GUESTING);
//		userRepo.save(BEN);
//		userRepo.save(ALYSSA); //TODO: maintain saving in model, UserEntity doesn't persist from subscription;
//		GUESTING.subscribe(ALYSSA);
//		
//		BEN.removeOwnedEvent(GUESTING); 
//
//		assertFalse(eventRepo.existsById(GUESTING.getId()));
//		assertTrue(userRepo.existsById(ALYSSA.getId()));
//		assertFalse(ALYSSA.getEventEntityGuest().contains(GUESTING));

//	}




}