package Application.entities.relations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

	private static final UserItem ALYSSA = new UserItem();
	private static final UserItem BEN = new UserItem();
	private static final EventItem TESTING = new EventItem();

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
		BEN.setFirstName("Ben");
		TESTING.setUserItemOwner(ALYSSA);
		ALYSSA.getEventItemsOwner().add(TESTING); // TODO: next version with read

	}

	@Test
	public void givenEntitiesReadRelations() {
		assertTrue(TESTING.getUserItemOwner().equals(ALYSSA));
	}

	@Test
	public void onUserSaveReadEvent() {
		
		userRepo.save(ALYSSA);
		userRepo.flush();
		
		UserItem persistedUser = userRepo.findById(ALYSSA.getId()).get();
		EventItem persistedEvent = eventRepo.findAll().get(0);

		UserItem persistedUserOwner = persistedEvent.getUserItemOwner();
		assertTrue(persistedUserOwner.equals(ALYSSA));
		EventItem persistedEventFirstInUserList = persistedUser.getEventItemsOwner().get(0);
		assertTrue(persistedEventFirstInUserList.equals(TESTING));

	}
	
//	/**
//	 * If event side is write-only, then user lists must be updated with changes of event owner; 
//	 */
//	@Test
//	public void onUserSaveChangeEvent() {
//
//		userRepo.save(ALYSSA);
//		userRepo.flush();
//
//		EventItem persistedEventFirstInUserList = userRepo.findById(ALYSSA.getId()).get().getEventItemsOwner().get(0);
//		persistedEventFirstInUserList.setUserItemOwner(BEN);
//		BEN.getEventItemsOwner().add(persistedEventFirstInUserList);
//		
//		userRepo.save(BEN);
//		userRepo.flush();
//
//		eventRepo.save(persistedEventFirstInUserList);
//		eventRepo.flush();
//		
//		UserItem persistedUser = userRepo.findById(ALYSSA.getId()).get();
//		assertFalse(persistedUser.getEventItemsOwner().contains(TESTING));
//
//	}

}
