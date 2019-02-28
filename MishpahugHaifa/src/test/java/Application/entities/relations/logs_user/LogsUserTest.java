package Application.entities.relations.logs_user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

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
	private final UserEntity RANA = RandomEntities.randomUserEntity();
	private final UserEntity RANB = RandomEntities.randomUserEntity();
	private final EventEntity TESTING = new EventEntity();
	private Random r = new Random();
	private final LocalDate TDATEA = LocalDate.of(2000 + r.nextInt(1024)%30, r.nextInt(1024)%12 + 1, r.nextInt(1024)%31);
	private final LocalTime TTIMEA = LocalTime.of(r.nextInt(1024)%24, r.nextInt(1024)%60);
	private final LocalDate TDATEB = LocalDate.of(2000 + r.nextInt(1024)%30, r.nextInt(1024)%12 + 1, r.nextInt(1024)%31);
	private final LocalTime TTIMEB = LocalTime.of(r.nextInt(1024)%24, r.nextInt(1024)%60);
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

		TESTING.setDate(TDATEA);
		TESTING.setTime(TTIMEA);
		TESTING.setNameOfEvent(TNAME);

		LOG_A.setUserNickName(RANA.getNickname());
		LOG_A.setEventDescription(TESTING.toEventUniqueDescription());
		LOG_A.setAction(UserActions.EVENT_EDITION);
		LOG_A.setDate(TDATEA);
		LOG_A.setTime(TTIMEA);
		LOG_A.setTime(TTIMEA);

		LOG_B.setUserNickName(RANB.getNickname());
		LOG_B.setEventDescription(TESTING.toEventUniqueDescription());
		LOG_B.setAction(UserActions.EVENT_STATUS_CHANGE);
		LOG_B.setDate(TDATEB);
		LOG_B.setTime(TTIMEB);
		LOG_B.setTime(TTIMEB);
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