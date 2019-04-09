package application.relations.user_event;

import application.entities.EventEntity;
import application.entities.SubscriptionEntity;
import application.entities.UserEntity;
import application.entities.values.FeedBackValue;
import application.repositories.SubscriptionRepository;
import application.repositories.EventRepository;
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
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class UserEventGuestFeedbackTest {
	
	private final UserEntity ALYSSA = new UserEntity();
	private final UserEntity BEN = new UserEntity();
	private final LocalDate TDATE = LocalDate.of(2190, 1, 1);
	private final LocalTime TTIME = LocalTime.of(23, 59);
	private final EventEntity GUESTING = new EventEntity(TDATE, TTIME);
	private final SubscriptionEntity AGUESTING = new SubscriptionEntity();
	private final FeedBackValue ABFEEDBACK = new FeedBackValue();

	private final String TNAME = "TESTING";
	
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
		GUESTING.setNameOfEvent(TNAME);
		ABFEEDBACK.setComment("Nice event");
		ABFEEDBACK.setDateTime(LocalDateTime.now());
		ABFEEDBACK.setRating(5);
	}

	@Test
	public void onSubsctiptionSaveReadFeedback() {
		
		GUESTING.setUserEntityOwner(BEN);
		userRepo.save(BEN);
		userRepo.save(ALYSSA); 
		AGUESTING.subscribe(ALYSSA, GUESTING);
		AGUESTING.setFeedback(ABFEEDBACK);
		eventGuestRepo.save(AGUESTING);
	
		EventEntity savedE = eventRepo.findById(GUESTING.getId()).get();
		SubscriptionEntity savedSubcsrEvent = savedE.getSubscriptions().iterator().next();
		FeedBackValue savedFB = savedSubcsrEvent.getFeedback();
		assertEquals(savedFB, ABFEEDBACK);

	}
	
//
//	@Test
//	public void addFeedbackWithoutSubscription() {
//		
//		
//
//	}
	


}