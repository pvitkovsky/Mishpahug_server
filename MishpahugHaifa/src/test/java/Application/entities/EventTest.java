package Application.entities;

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

import Application.repo.EventRepository;

/**
 * Testing creation and deletion of entities to ensure relations work as
 * intended;
 * 
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class EventTest {

	private static final EventItem TESTING = new EventItem();
	
	@Autowired
	EventRepository eventRepo;


	@Before
	public void clear() {
		eventRepo.deleteAll();
	}
	
	@Before 
	public void buildEvent() {
		
		TESTING.setNameOfEvent("Testing of new software");
		
	}

	

	/**
	 * Testing add, with explicit saving
	 */
	@Test
	public void addEvent() {
		
		EventItem createdEvent = TESTING;

		eventRepo.save(createdEvent);
		eventRepo.flush();

		EventItem persistedEvent = eventRepo.findById(createdEvent.getId()).get();
		System.out.println(persistedEvent);
		assertTrue(persistedEvent.equals(createdEvent));
	}
}
