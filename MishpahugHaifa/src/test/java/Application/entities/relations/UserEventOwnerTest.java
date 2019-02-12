package Application.entities.relations;

import static org.junit.Assert.assertFalse;
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

import Application.entities.EventItem;
import Application.entities.UserItem;
import Application.repo.EventRepository;
import Application.repo.UserRepository;

/**
 * Relation: OneToMany User is the primary entity. Event must have a user as its
 * owner.
 * 
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class UserEventOwnerTest {
	
	private final UserItem ALYSSA = new UserItem();
	private final UserItem BEN = new UserItem();
	private final EventItem TESTING = new EventItem();
	
	@PersistenceContext // https://www.javabullets.com/access-entitymanager-spring-data-jpa/
	private EntityManager em;

	@Autowired
	UserRepository userRepo;

	@Autowired
	EventRepository eventRepo;

	@Before
	public void clear() {
		userRepo.deleteAll();
		eventRepo.deleteAll();
	}

	@Before
	public void buildEntities() {
		ALYSSA.setFirstName("Alyssa");
		TESTING.setUserItemOwner(ALYSSA);

	}

	@Before
	public void buildManualItem() {  // TODO: next version without explicit add
		ALYSSA.getEventItemsOwner().add(TESTING);
	}


	@Before
	public void buildEntities2() {
		BEN.setFirstName("Ben");
	}

	@Test
	public void givenEntitiesReadRelations() {
		assertTrue(TESTING.getUserItemOwner().equals(ALYSSA));
	}

	@Test
	public void onUserSaveReadEvent() {
		
		userRepo.save(ALYSSA);
		assertTrue(em.find(UserItem.class, ALYSSA.getId()) != null);
		
		UserItem savedA = userRepo.findById(ALYSSA.getId()).get();
		EventItem savedE = eventRepo.findAll().get(0);

		UserItem savedAfromEvent = savedE.getUserItemOwner();
		assertTrue(savedAfromEvent.equals(ALYSSA));
		System.out.println(savedAfromEvent);
		
		savedE = savedA.getEventItemsOwner().iterator().next();
		assertTrue(savedE.equals(TESTING));
		System.out.println(savedE);
	}

	/**
	 * If event side is write-only, then user lists must be updated with changes of event owner; 
	 */
	@Test
	public void onUserSaveChangeEvent() {

		userRepo.save(ALYSSA);
		
		EventItem savedE = userRepo.findById(ALYSSA.getId()).get().getEventItemsOwner().iterator().next();
		savedE.setUserItemOwner(BEN);
		ALYSSA.getEventItemsOwner().remove(savedE);
		BEN.getEventItemsOwner().add(savedE);
		
		userRepo.save(BEN);
		eventRepo.save(savedE);
		
		UserItem savedA = userRepo.findById(ALYSSA.getId()).get();
		UserItem savedB = userRepo.findById(BEN.getId()).get();
		assertFalse(savedA.getEventItemsOwner().contains(TESTING));
		assertTrue(savedB.getEventItemsOwner().contains(TESTING));

	}

}
