package application.relations.user_event;

import application.entities.EventEntity;
import application.entities.EventGuestRelation;
import application.entities.UserEntity;
import application.entities.randomgeneration.RandomEntities;
import application.repositories.EventGuestRepository;
import application.repositories.EventRepository;
import application.repositories.UserRepository;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class UserEventGuestPerformanceTest {

	private static RandomString gen = new RandomString();
	private static Integer USERSIZE = 10; 
	private static Integer EVENTSIZE = 100; 

	private final UserEntity BEN = new UserEntity();
	private final LocalDate TDATE = LocalDate.of(2190, 1, 1);
	private final LocalTime TTIME = LocalTime.of(23, 59);

	@Autowired
	UserRepository userRepo;

	@Autowired
	EventRepository eventRepo;

	@Autowired
	EventGuestRepository eventGuestRepo;
	

	@Before
	public void bigSubscriptionSetup() {
		BEN.setEMail("bitdiddle@sicp.edu");
		Set<EventEntity> events = Stream.generate(EventEntity::new).map(e -> fillRandomEvent(e)).limit(USERSIZE).collect(Collectors.toSet());
		Set<UserEntity> users = Stream.generate(RandomEntities::randomUserEntity).limit(EVENTSIZE).collect(Collectors.toSet());
		events.forEach(BEN::makeOwner);
		userRepo.save(BEN);	
		userRepo.saveAll(users);
		for (UserEntity user : users) {
			for (EventEntity event : events) {
				EventGuestRelation subscription = new EventGuestRelation();
				subscription.subscribe(user,  event);
			}
		}
		
	}
	
	private EventEntity fillRandomEvent(EventEntity event) {
		event.setDate(TDATE);
		event.setTime(TTIME);
		event.setNameOfEvent(gen.nextString());
		return event;
	}

	/**
	 * Trying to test the limits of simultaneous subscriptions
	 */
	@Test
	public void findEventBySubsPerformance(){ 

		Map<UserEntity, Integer> results = new HashMap<>();
		List<UserEntity> users = userRepo.findAll();
		for (UserEntity user : users) {
			Set<EventGuestRelation> subscriptions = user.getSubscriptions();
			Set<EventEntity> events = subscriptions.stream().map(s -> s.getEvent()).collect(Collectors.toSet());
			results.put(user, events.size());
		}
		System.out.println(results);
		List<String> userNames = users.stream().map(u -> u.getUserName()).collect(Collectors.toList());
		System.out.println(userNames);
	}
	


}