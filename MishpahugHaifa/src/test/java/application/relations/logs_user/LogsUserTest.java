
package application.relations.logs_user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import application.entities.EventEntity;
import application.entities.LogsDataEntity;
import application.entities.LogsOnEvent;
import application.entities.LogsOnEvent.ActionsOnEvent;
import application.entities.LogsOnUser;
import application.entities.UserEntity;
import application.entities.randomgeneration.RandomEntities;
import application.repo.EventRepository;
import application.repo.LogsDataRepository;
import application.repo.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
//TODO: logs test;
public class LogsUserTest {

	private final String RNAME = "RAN";
	private final LogsOnEvent LOG_A = new LogsOnEvent();
	private final LogsOnEvent LOG_B = new LogsOnEvent();
	private final UserEntity RAN = RandomEntities.randomUserEntity(); // BUILDER creates NPE in userEntityOwner's hashSet 
	private final EventEntity TESTING = new EventEntity();
	private final LocalDate TDATE = LocalDate.of(2190, 1, 1);
	private final LocalTime TTIME = LocalTime.of(23, 59);
	private final String TNAME = "TESTING";

	@Autowired
	UserRepository userRepo;
	@Autowired
	EventRepository eventRepo;
	@Autowired
	LogsDataRepository logsRepo;

	@Before
	public void buildEntities() {
		
		RAN.setEMail("therandom@sicp.edu");
		
		//Can we add to the set and then set value fields later?
		
		TESTING.setDate(TDATE);
		TESTING.setTime(TTIME);
		TESTING.setNameOfEvent(TNAME);
		
		RAN.makeOwner(TESTING);
		

		LOG_A.setUserActor(RAN);
		LOG_A.setEventTarget(TESTING);
		LOG_A.setAction(ActionsOnEvent.EVENT_STATUS_CHANGE);
		LOG_A.setDate(TDATE);
		LOG_A.setTime(TTIME);
		LOG_A.setTime(TTIME);

		LOG_B.setUserActor(RAN);
		LOG_B.setEventTarget(TESTING);
		LOG_B.setAction(ActionsOnEvent.EVENT_STATUS_CHANGE);
		LOG_B.setDate(TDATE);
		LOG_B.setTime(TTIME);
		LOG_B.setTime(TTIME);
	}

	@Test
	public void givenLogSaveAndRetrievedIsEqual() {

		
		userRepo.save(RAN);
		logsRepo.save(LOG_A);
		logsRepo.save(LOG_B);

		LogsDataEntity savedLA = logsRepo.getOne(LOG_A.getId());
		assertEquals(savedLA, LOG_A);
		assertNotEquals(savedLA, LOG_B);

		LogsDataEntity savedLB = logsRepo.getOne(LOG_B.getId());
		assertNotEquals(savedLB, LOG_A);
		assertEquals(savedLB, LOG_B);
	}

}