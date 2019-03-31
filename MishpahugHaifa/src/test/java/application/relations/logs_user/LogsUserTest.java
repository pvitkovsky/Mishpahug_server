
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;

import application.entities.EventEntity;
import application.entities.LogsDataEntity;
import application.entities.LogsOnEvent;
import application.entities.LogsOnEvent.ActionsOnEvent;
import application.entities.LogsOnUser;
import application.entities.UserEntity;
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

	private final LogsOnEvent LOG_MA = new LogsOnEvent();
	private final LogsOnEvent LOG_MB = new LogsOnEvent();
	private final LogsOnUser LOG_NM = new LogsOnUser();
	private final LogsOnUser LOG_MN = new LogsOnUser();
	private final UserEntity RAN_M = RandomEntities.randomUserEntity(); // BUILDER creates NPE in userEntityOwner's hashSet
	private final UserEntity RAN_N = RandomEntities.randomUserEntity();
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
		
		RAN_M.setEMail("therandom@sicp.edu");
	
		//Can we add to the set and then set value fields later?
		
		TESTING.setDate(TDATE);
		TESTING.setTime(TTIME);
		TESTING.setNameOfEvent(TNAME);
		
		RAN_M.makeOwner(TESTING);
	
		LOG_MA.setUserActor(RAN_M);
		LOG_MA.setEventTarget(TESTING);
		LOG_MA.setAction(ActionsOnEvent.EVENT_STATUS_CHANGE);
		LOG_MA.setDate(TDATE);
		LOG_MA.setTime(TTIME);

		LOG_MB.setUserActor(RAN_M);
		LOG_MB.setEventTarget(TESTING);
		LOG_MB.setAction(ActionsOnEvent.EVENT_STATUS_CHANGE);
		LOG_MB.setDate(TDATE);
		LOG_MB.setTime(TTIME);

		LOG_NM.setUserActor(RAN_M);
		LOG_NM.setUserTarget(RAN_N);
		LOG_NM.setAction(LogsOnUser.ActionsOnUser.USER_EDITION_ADDRESS);
		LOG_NM.setDate(TDATE);
		LOG_NM.setTime(TTIME);

		LOG_MN.setUserActor(RAN_M);
		LOG_MN.setUserTarget(RAN_N);
		LOG_MN.setAction(LogsOnUser.ActionsOnUser.USER_COMMENT);
		LOG_MN.setDate(TDATE);
		LOG_MN.setTime(TTIME);
	}

	@Test
	public void givenLogSaveAndRetrievedIsEqual() {
		
		userRepo.save(RAN_M);
		userRepo.save(RAN_N);
		logsRepo.save(LOG_MA);
		logsRepo.save(LOG_MB);
		logsRepo.save(LOG_NM);
		logsRepo.save(LOG_MN);

		LogsDataEntity savedLA = logsRepo.getOne(LOG_MA.getId());
		assertEquals(savedLA, LOG_MA);
		assertNotEquals(savedLA, LOG_MB);

		LogsDataEntity savedLB = logsRepo.getOne(LOG_MB.getId());
		assertNotEquals(savedLB, LOG_MA);
		assertEquals(savedLB, LOG_MB);

		LogsDataEntity savedULA = logsRepo.getOne(LOG_NM.getId());
		assertEquals(savedULA, LOG_NM);
		assertNotEquals(savedULA, LOG_MN);

		LogsDataEntity savedULB = logsRepo.getOne(LOG_MN.getId());
		assertNotEquals(savedULB, LOG_NM);
		assertEquals(savedULB, LOG_MN);
	}

}