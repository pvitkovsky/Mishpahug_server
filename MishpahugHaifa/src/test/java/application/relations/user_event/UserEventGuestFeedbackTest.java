package application.relations.user_event;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import application.entities.EventEntity;
import application.entities.SubscriptionEntity;
import application.entities.UserEntity;
import application.entities.values.FeedBackValue;
import application.repositories.EventRepository;
import application.repositories.SubscriptionRepository;
import application.repositories.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class UserEventGuestFeedbackTest {
	
	private final UserEntity ALYSSA = new UserEntity("Alyssa", "p_hacker@sicp.edu");
	private final UserEntity BEN = new UserEntity("Ben", "bitdiddle@sicp.edu");
	private final LocalDate TDATE = LocalDate.of(2190, 1, 1);
	private final LocalTime TTIME = LocalTime.of(23, 59);
	private EventEntity GUESTING;
	private SubscriptionEntity AGUESTING; 
	private final FeedBackValue ABFEEDBACK = new FeedBackValue();
	
	@Autowired
	UserRepository userRepo;

	@Autowired
	EventRepository eventRepo;

	@Autowired
	SubscriptionRepository eventGuestRepo;
	
	@Before
	public void buildEntities() {
		userRepo.save(BEN);
		userRepo.save(ALYSSA); 
		GUESTING = new EventEntity(BEN, TDATE, TTIME);
		eventRepo.save(GUESTING);	
		AGUESTING = new SubscriptionEntity(ALYSSA, GUESTING);	
		ABFEEDBACK.setComment("Nice event");
		ABFEEDBACK.setDateTime(LocalDateTime.now());
		ABFEEDBACK.setRating(5);
	}

	@Test
	public void onSubsctiptionSaveReadFeedback() {
		
		AGUESTING.setFeedback(ABFEEDBACK);
		eventGuestRepo.save(AGUESTING);
	
		EventEntity savedE = eventRepo.findById(GUESTING.getId()).get();
		SubscriptionEntity savedSubcsrEvent = savedE.getSubscriptions().iterator().next();
		FeedBackValue savedFB = savedSubcsrEvent.getFeedback();
		assertEquals(savedFB, ABFEEDBACK);

	}
	
//	@Test
//	public void addFeedbackWithoutSubscription() {
//		//TODO: argument that can't add feedback without subscription;
//	}


}