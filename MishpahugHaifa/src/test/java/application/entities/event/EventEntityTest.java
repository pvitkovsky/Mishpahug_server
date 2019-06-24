package application.entities.event;

import application.models.event.EventEntity;
import application.models.event.EventRepository;
import application.models.user.UserEntity;
import application.models.user.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Relation: OneToMany User is the primary entity. Event must have a user as its
 * owner.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class EventEntityTest {

	private final UserEntity ALYSSA = new UserEntity("Alyssa", "p_hacker@sicp.edu");
	private final LocalDate TDATE = LocalDate.now().plusYears(20);
	private final LocalTime TTIME = LocalTime.of(23, 59);
	private EventEntity TESTING; 
	private final Map<String, String> TUPDATE = new HashMap<>();

	@Autowired
	EventRepository eventRepo;

	@Autowired
	UserRepository userRepo;
	
	@Before
	public void buildEntities() {
		userRepo.save(ALYSSA);
		TESTING = new EventEntity(ALYSSA, TDATE, TTIME);
		eventRepo.save(TESTING); // TODO: where is cascade?!
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void givenDuplicateEventsSaveAndGetException() {
		
		EventEntity TESTINGDUPLICATE = new EventEntity(ALYSSA, TDATE, TTIME);
		eventRepo.save(TESTINGDUPLICATE);

	}
	
	@Test()
	public void givenEventSaveAndRead() {
		
		assertEquals(eventRepo.getOne(TESTING.getId()), TESTING);	
		assertEquals(eventRepo.count(), 1);	
	
	}

	@Test(expected = IllegalArgumentException.class)
	public void savedEventChangeStatusWithIllegalStringThrows() {

		TESTING.changeStatus("foo");

	}

	/**
	 * Business key should be immutable...
	 */
	@Test
	public void onRenameEvent() { //TODO: fix me pls; there's a hashcode mutability issue
		
		String newName = "Better_Name";
		TESTING.setNameOfEvent(newName);
		
		assertEquals(TESTING.getNameOfEvent(), newName);
	}
	
	@Test
	public void savedEventChangeStatusWithLegalStringWorks() {

		assertTrue(TESTING.isDue());
		
		TESTING.changeStatus("DEACTIVATED");
		assertTrue(TESTING.isDeactivated());
		
                TESTING.changeStatus("ACTIVE");
		assertTrue(TESTING.isDue());

                TESTING.changeStatus("CANCELED");		
		assertTrue(TESTING.isCanceled());

                TESTING.changeStatus("PENDINGFORDELETION");		
		assertTrue(TESTING.isPendingForDeletion());
		
		eventRepo.delete(TESTING); 
		assertEquals(eventRepo.count(), 0);
	}	
	
	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void onEventDeleteWithoutQueueThrows() {
	
		eventRepo.delete(TESTING); 
		eventRepo.flush();
		
	}
	
	@Test
	public void onEventDeleteWithQueueWorks() {
		
		assertTrue(eventRepo.existsById(TESTING.getId()));	
		assertEquals(eventRepo.count(), 1);
		
		TESTING.putIntoDeletionQueue();
		eventRepo.delete(TESTING); 

		assertFalse(eventRepo.existsById(TESTING.getId()));	
		assertEquals(eventRepo.count(), 0);
		
	}

}
