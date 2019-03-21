
package application.entities.event;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import application.entities.EventEntity;
import application.entities.UserEntity;
import application.repo.EventRepository;
import application.repo.UserRepository;

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
	private final EventEntity TESTING = new EventEntity();
	private final EventEntity TESTINGDUPLICATE = new EventEntity();
	private final LocalDate TDATE = LocalDate.of(2190, 1, 1);
	private final LocalTime TTIME = LocalTime.of(23, 59);
	private final String TNAME = "TESTING";

	@Autowired
	EventRepository eventRepo;

	@Autowired
	UserRepository userRepo;
	
	@Before
	public void buildEntities() {
		

		TESTING.setDate(TDATE);
		TESTING.setTime(TTIME);
		TESTING.setNameOfEvent(TNAME);
		TESTINGDUPLICATE.setDate(TDATE);
		TESTINGDUPLICATE.setTime(TTIME);
		TESTINGDUPLICATE.setNameOfEvent(TNAME);
		/*
		 * maybe builder in EventEntity for business key / clone method; 
		 */
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void givenDuplicateEventsSaveAndGetException() {
		
		ALYSSA.makeOwner(TESTING);
		userRepo.save(ALYSSA);
		eventRepo.save(TESTING);
		
		ALYSSA.makeOwner(TESTINGDUPLICATE);
		eventRepo.save(TESTINGDUPLICATE);

	}
	
	@Test()
	public void givenEventSaveAndRead() {
		
		ALYSSA.makeOwner(TESTING);
		userRepo.save(ALYSSA);
		eventRepo.save(TESTING);
		assertEquals(eventRepo.getOne(TESTING.getId()), TESTING);	
	
	}

}