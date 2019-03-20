package Application.entities.relations.logs_user;

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

import Application.entities.EventEntity;
import Application.entities.LogsDataEntity;
import Application.entities.LogsDataEntity.UserActions;
import Application.entities.UserEntity;
import Application.entities.randomgeneration.RandomEntities;
import Application.repo.LogsDataRepository;

/**
 * Relation: OneToMany User is the primary entity. Event must have a user as its
 * owner.
 * 
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class LogsUserTest {

	private final LogsDataEntity LOG_A = new LogsDataEntity();
	private final LogsDataEntity LOG_B = new LogsDataEntity();
	private final UserEntity RAN = RandomEntities.randomUserEntity();
	private final EventEntity TESTING = new EventEntity();
	private final LocalDate TDATE = LocalDate.of(2190, 1, 1);
	private final LocalTime TTIME = LocalTime.of(23, 59);
	private final String TNAME = "TESTING";

	/*
	 * UserActions EVENT_STATUS_CHANGE, USER_EDITION_EMAIL, USER_EDITION_ADDRESS,
	 * USER_EDITION_NAME, USER_LOGIN, USER_PROFILE_VIEW, USER_REGISTRATION,
	 * EVENT_SUBSCRIBE, EVENT_UNSUBSCRIBE, EVENT_EDITION, EVENT_VIEW, EVENT_CANCEL,
	 * EVENT_COMMENT, USER_COMMENT
	 */

	@Autowired
	LogsDataRepository logsRepo;

	@Before
	public void buildEntities() {

		TESTING.setDate(TDATE);
		TESTING.setTime(TTIME);
		TESTING.setNameOfEvent(TNAME);

		LOG_A.setEventDescription(TESTING.toEventUniqueDescription());
		LOG_A.setAction(UserActions.EVENT_STATUS_CHANGE);
		LOG_A.setDate(TDATE);
		LOG_A.setTime(TTIME);
		LOG_A.setTime(TTIME);

		LOG_B.setEventDescription(TESTING.toEventUniqueDescription());
		LOG_B.setAction(UserActions.EVENT_STATUS_CHANGE);
		LOG_B.setDate(TDATE);
		LOG_B.setTime(TTIME);
		LOG_B.setTime(TTIME);
	}

	@Test
	public void givenLogSaveAndRetrievedIsEqual() {
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