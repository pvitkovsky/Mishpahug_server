
package application.entities.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

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

import application.entities.EventEntity;
import application.entities.UserEntity;
import application.repositories.EventRepository;
import application.repositories.UserRepository;

/**
 * Relation: OneToMany User is the primary entity. Event must have a user as its
 * owner.
 * 
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class EventEntityTest {

	private final UserEntity ALYSSA = new UserEntity();
	private final LocalDate TDATE = LocalDate.now().plusYears(20);
	private final LocalTime TTIME = LocalTime.of(23, 59);
	private final EventEntity TESTING = new EventEntity(TDATE, TTIME);
	private final EventEntity TESTINGDUPLICATE = new EventEntity(TDATE, TTIME);
	
	private final Map<String, String> TUPDATE = new HashMap<>();

	@Autowired
	EventRepository eventRepo;

	@Autowired
	UserRepository userRepo;
	
	@Before
	public void buildEntities() {
		ALYSSA.setEMail("p_hacker@sicp.edu");
		userRepo.save(ALYSSA);
		TESTING.setUserEntityOwner(ALYSSA);
		eventRepo.save(TESTING); // TODO: where is cascade?!
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void givenDuplicateEventsSaveAndGetException() {
		
		TESTINGDUPLICATE.setUserEntityOwner(ALYSSA);
		eventRepo.save(TESTINGDUPLICATE);

	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void givenEventsWithoutOwnerSaveAndGetException() {
		
		EventEntity NOOWNERTEST = new EventEntity(TDATE, TTIME);
		eventRepo.save(NOOWNERTEST);

	}
	
	@Test()
	public void givenEventSaveAndRead() {
		
		assertEquals(eventRepo.getOne(TESTING.getId()), TESTING);	
	
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void savedEventChangeStatusWithIllegalStringThrows() {

		TUPDATE.put("status", "foo");
		eventRepo.update(TESTING, TUPDATE);
	}

	/**
	 * Business key should be immutable...
	 */
	@Test
	public void onRenameEvent() { //TODO: fix me pls; there's a hashcode mutability issue
		
		String newName = "Better_Name";
		TESTING.setNameOfEvent(newName);
		
		assertEquals(TESTING.getNameOfEvent(), newName);
		assertEquals(ALYSSA.getEventEntityOwner().size(), 1);
		assertTrue(ALYSSA.getEventEntityOwner().contains(TESTING));
	}
	
	@Test
	public void savedEventChangeStatusWithLegalStringWorks() {

		assertTrue(TESTING.isDue());
		
		TUPDATE.put("status", "DEACTIVATED");
		eventRepo.update(TESTING, TUPDATE);
		assertTrue(TESTING.isDeactivated());
		TUPDATE.clear();
		
		TUPDATE.put("status", "ACTIVE");
		eventRepo.update(TESTING, TUPDATE);
		assertTrue(TESTING.isDue());
		TUPDATE.clear();
		
		TUPDATE.put("status", "CANCELED");
		eventRepo.update(TESTING, TUPDATE);
		assertTrue(TESTING.isCanceled());
		TUPDATE.clear();
		
		TUPDATE.put("status", "PENDINGFORDELETION");
		eventRepo.update(TESTING, TUPDATE);
		assertTrue(TESTING.isPendingForDeletion());
		TUPDATE.clear();
		
		eventRepo.delete(TESTING); 
		assertEquals(eventRepo.count(), 0);
		assertEquals(ALYSSA.getEventEntityOwner().size(), 0);
	}	
	
	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void onEventDeleteWithoutQueueThrows() {
	
		eventRepo.delete(TESTING); 
		eventRepo.flush();
		
	}
	
	@Test
	public void onEventDeleteWithQueueWorks() {
		
		TESTING.putIntoDeletionQueue();
		eventRepo.delete(TESTING); 
		eventRepo.flush();
		assertEquals(eventRepo.count(), 0);
		
	}

}