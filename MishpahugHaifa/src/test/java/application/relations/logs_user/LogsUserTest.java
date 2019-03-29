
package application.relations.logs_user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import application.entities.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import application.entities.LogsOnEvent.ActionsOnEvent;
import application.entities.randomgeneration.RandomEntities;
import application.repositories.EventRepository;
import application.repositories.LogsDataRepository;
import application.repositories.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
//TODO: logs test;
public class LogsUserTest {

	private final String RNAME = "RAN";
	private final LogsOnEvent LOG_AA = new LogsOnEvent();
	private final LogsOnEvent LOG_AB = new LogsOnEvent();
	private final LogsOnUser LOG_UA = new LogsOnUser();
	private final LogsOnUser LOG_UB = new LogsOnUser();
	private final UserEntity RAN = RandomEntities.randomUserEntity(); // BUILDER creates NPE in userEntityOwner's hashSet
	private final UserEntity RAN_U = RandomEntities.randomUserEntity();
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
		

		LOG_AA.setUserActor(RAN);
		LOG_AA.setEventTarget(TESTING);
		LOG_AA.setAction(ActionsOnEvent.EVENT_STATUS_CHANGE);
		LOG_AA.setDate(TDATE);
		LOG_AA.setTime(TTIME);

		LOG_AB.setUserActor(RAN);
		LOG_AB.setEventTarget(TESTING);
		LOG_AB.setAction(ActionsOnEvent.EVENT_STATUS_CHANGE);
		LOG_AB.setDate(TDATE);
		LOG_AB.setTime(TTIME);

		LOG_UA.setUserActor(RAN);
		LOG_UA.setUserTarget(RAN_U);
		LOG_UA.setAction(LogsOnUser.ActionsOnUser.USER_EDITION_ADDRESS);
		LOG_UA.setDate(TDATE);
		LOG_UA.setTime(TTIME);

		LOG_UB.setUserActor(RAN);
		LOG_UB.setUserTarget(RAN_U);
		LOG_UB.setAction(LogsOnUser.ActionsOnUser.USER_COMMENT);
		LOG_UB.setDate(TDATE);
		LOG_UB.setTime(TTIME);
	}

	@Test
	public void givenLogSaveAndRetrievedIsEqual() {

		
		userRepo.save(RAN);
		userRepo.save(RAN_U);
		logsRepo.save(LOG_AA);
		logsRepo.save(LOG_AB);
		logsRepo.save(LOG_UA);
		logsRepo.save(LOG_UB);

		LogsDataEntity savedLA = logsRepo.getOne(LOG_AA.getId());
		assertEquals(savedLA, LOG_AA);
		assertNotEquals(savedLA, LOG_AB);

		LogsDataEntity savedLB = logsRepo.getOne(LOG_AB.getId());
		assertNotEquals(savedLB, LOG_AA);
		assertEquals(savedLB, LOG_AB);

		LogsDataEntity savedULA = logsRepo.getOne(LOG_UA.getId());
		assertEquals(savedULA, LOG_UA);
		assertNotEquals(savedULA, LOG_UB);

		LogsDataEntity savedULB = logsRepo.getOne(LOG_UB.getId());
		assertNotEquals(savedULB, LOG_UA);
		assertEquals(savedULB, LOG_UB);
	}

}