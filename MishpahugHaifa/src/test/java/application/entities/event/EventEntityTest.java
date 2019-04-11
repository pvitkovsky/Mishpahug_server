package application.entities.event;

import application.entities.EventEntity;
import application.entities.UserEntity;
import application.repositories.EventRepository;
import application.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Relation: OneToMany User is the primary entity. Event must have a user as its
 * owner.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class EventEntityTest {

    private final UserEntity ALYSSA = new UserEntity();
    private final EventEntity TESTING = new EventEntity();
    private final EventEntity TESTINGDUPLICATE = new EventEntity();
    private final LocalDate TDATE = LocalDate.now().plusYears(20);
    private final LocalTime TTIME = LocalTime.of(23, 59);
    private final String TNAME = "TESTING";
    private final Map<String, String> TUPDATE = new HashMap<>();

    @Autowired
    EventRepository eventRepo;

    @Autowired
    UserRepository userRepo;

    @Before
    public void buildEntities() {

        ALYSSA.setEMail("p_hacker@sicp.edu");
        TESTING.setDate(TDATE);
        TESTING.setTime(TTIME);
        TESTING.setNameOfEvent(TNAME);
        TESTINGDUPLICATE.setDate(TDATE);
        TESTINGDUPLICATE.setTime(TTIME);
        TESTINGDUPLICATE.setNameOfEvent(TNAME);
        /*
         * maybe builder in EventEntity for business key / clone method;
         */
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void givenDuplicateEventsSaveAndGetException() {

        ALYSSA.makeOwner(TESTING);
        userRepo.save(ALYSSA);

        ALYSSA.makeOwner(TESTINGDUPLICATE);
        eventRepo.save(TESTINGDUPLICATE);

    }

    @Test()
    public void givenEventSaveAndRead() {

        ALYSSA.makeOwner(TESTING);
        userRepo.save(ALYSSA);

        assertEquals(eventRepo.getOne(TESTING.getId()), TESTING);

    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void savedEventChangeStatusWithIllegalStringThrows() {
        ALYSSA.makeOwner(TESTING);
        userRepo.save(ALYSSA);

        TUPDATE.put("status", "foo");
        eventRepo.update(TESTING, TUPDATE);
    }

    /**
     * Business key should be immutable...
     */
    @Test
    public void onRenameEvent() { //TODO: fix me pls; there's a hashcode mutability issue

        ALYSSA.makeOwner(TESTING);
        userRepo.save(ALYSSA);

        String newName = "Better_Name";
        TESTING.setNameOfEvent(newName);

        assertEquals(TESTING.getNameOfEvent(), newName);
        assertEquals(ALYSSA.getEventEntityOwner().size(), 1);
        assertTrue(ALYSSA.getEventEntityOwner().contains(TESTING));
    }

    @Test
    public void savedEventChangeStatusWithLegalStringWorks() {
        ALYSSA.makeOwner(TESTING);
        userRepo.save(ALYSSA);
        assertTrue(TESTING.isDue());

        TUPDATE.put("status", "DEACTIVATED");
        eventRepo.update(TESTING, TUPDATE);
        assertTrue(TESTING.isDeactivated());
        TUPDATE.clear();

        TUPDATE.put("status", "ACTIVE");
        eventRepo.update(TESTING, TUPDATE);
        assertTrue(TESTING.isDue());
        TUPDATE.clear();

        TUPDATE.put("status", "CANCELED");
        eventRepo.update(TESTING, TUPDATE);
        assertTrue(TESTING.isCanceled());
        TUPDATE.clear();

        TUPDATE.put("status", "PENDINGFORDELETION");
        eventRepo.update(TESTING, TUPDATE);
        assertTrue(TESTING.isPendingForDeletion());
        TUPDATE.clear();

        //eventRepo.delete(TESTING);
        assertEquals(eventRepo.count(), 0);
        assertEquals(ALYSSA.getEventEntityOwner().size(), 0);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void onEventDeleteWithoutQueueThrows() {

        ALYSSA.makeOwner(TESTING);
        userRepo.save(ALYSSA);

        //eventRepo.delete(TESTING);
        //eventRepo.flush();

    }

    @Test
    public void onEventDeleteWithQueueWorks() {

        ALYSSA.makeOwner(TESTING);
        userRepo.save(ALYSSA);

        TESTING.putIntoDeletionQueue();
        //eventRepo.delete(TESTING);
        eventRepo.flush();
        assertEquals(eventRepo.count(), 0);

    }

}