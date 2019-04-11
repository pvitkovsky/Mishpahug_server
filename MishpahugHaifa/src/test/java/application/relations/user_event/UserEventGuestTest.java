package application.relations.user_event;

import application.entities.EventEntity;
import application.entities.SubscriptionEntity;
import application.entities.UserEntity;
import application.repositories.EventRepository;
import application.repositories.SubscriptionRepository;
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
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class UserEventGuestTest {

    private final UserEntity ALYSSA = new UserEntity();
    private final UserEntity BEN = new UserEntity();
    private final EventEntity GUESTING = new EventEntity();
    private final SubscriptionEntity AGUESTING = new SubscriptionEntity();
    private final LocalDate TDATE = LocalDate.of(2190, 1, 1);
    private final LocalTime TTIME = LocalTime.of(23, 59);
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
        GUESTING.setDate(TDATE);
        GUESTING.setTime(TTIME);
        GUESTING.setNameOfEvent(TNAME);
    }


    @Test(expected = IllegalArgumentException.class)
    public void onMultipleSubscriptionsThrow() {

        BEN.makeOwner(GUESTING);
        userRepo.save(BEN);
        userRepo.save(ALYSSA);
        AGUESTING.subscribe(ALYSSA, GUESTING);
        AGUESTING.subscribe(ALYSSA, GUESTING);
        AGUESTING.subscribe(ALYSSA, GUESTING);

    }

    @Test(expected = IllegalArgumentException.class)
    public void onUnexistentSubscriptionUnsubscriptionThrow() {

        BEN.makeOwner(GUESTING);
        userRepo.save(BEN);
        userRepo.save(ALYSSA);
        AGUESTING.nullifyForRemoval();

    }

    @Test
    public void onSubscriptionSaveReadUserAndEvent() {

        BEN.makeOwner(GUESTING);
        userRepo.save(BEN);
        userRepo.save(ALYSSA);
        AGUESTING.subscribe(ALYSSA, GUESTING);

        assertTrue(userRepo.existsById(ALYSSA.getId()));
        assertTrue(eventRepo.existsById(GUESTING.getId()));
        assertEquals(userRepo.count(), 2);
        assertEquals(eventRepo.count(), 1);

        UserEntity savedA = userRepo.findById(ALYSSA.getId()).get();
        EventEntity savedE = eventRepo.findById(GUESTING.getId()).get();
        SubscriptionEntity savedSubcsrUser = savedA.getSubscriptions().iterator().next();
        SubscriptionEntity savedSubcsrEvent = savedE.getSubscriptions().iterator().next();
        assertEquals(AGUESTING, savedSubcsrUser);
        assertEquals(AGUESTING, savedSubcsrEvent);
        assertEquals(savedSubcsrUser, savedSubcsrEvent);

        UserEntity savedAfromRelation = AGUESTING.getUserGuest();
        EventEntity savedEfromRelation = AGUESTING.getEvent();
        assertTrue(savedA.equals(ALYSSA));
        assertTrue(savedAfromRelation.equals(ALYSSA));
        assertTrue(savedA.equals(savedAfromRelation));
        assertTrue(savedE.equals(GUESTING));
        assertTrue(savedEfromRelation.equals(GUESTING));
        assertTrue(savedE.equals(savedEfromRelation));

    }

    @Test
    public void findEventBySubs() {

        BEN.makeOwner(GUESTING);
        userRepo.save(BEN);
        userRepo.save(ALYSSA);
        AGUESTING.subscribe(ALYSSA, GUESTING);

        List<EventEntity> events = eventGuestRepo.getEventsForGuest(ALYSSA);
        //List<EventEntity> events = eventGuestRepo.findByUserGuest(ALYSSA); //TODO: converter
        assertEquals(events.size(), 1);
        assertTrue(events.contains(GUESTING));

    }

    @Test
    public void findUserBySubs() {

        BEN.makeOwner(GUESTING);
        userRepo.save(BEN);
        userRepo.save(ALYSSA);
        AGUESTING.subscribe(ALYSSA, GUESTING);

        List<UserEntity> guests = eventGuestRepo.getGuestsForEvent(GUESTING);
        //List<UserEntity> guests = eventGuestRepo.findByEvent(GUESTING); //TODO: converter
        assertEquals(guests.size(), 1);
        assertTrue(guests.contains(ALYSSA));
    }


    @Test
    public void onGuestDeleteEventRemains() {

        BEN.makeOwner(GUESTING);
        userRepo.save(BEN);
        userRepo.save(ALYSSA);
        AGUESTING.subscribe(ALYSSA, GUESTING);
        assertTrue(GUESTING.getSubscriptions().contains(AGUESTING));

        ALYSSA.putIntoDeletionQueue();
        userRepo.delete(ALYSSA);

        assertTrue(eventRepo.existsById(GUESTING.getId()));
        assertFalse(userRepo.existsById(ALYSSA.getId()));
        assertFalse(eventGuestRepo.existsById(AGUESTING.getId()));
        assertFalse(GUESTING.getSubscriptions().contains(AGUESTING));
        assertEquals(GUESTING.getSubscriptions().size(), 0);

    }

    /**
     * Deleting event and testing that its subscribers are unsubscribed automatically;
     */
    @Test
    public void onEventDeleteGuestRemains() {

        BEN.makeOwner(GUESTING);
        userRepo.save(BEN);
        userRepo.save(ALYSSA);
        AGUESTING.subscribe(ALYSSA, GUESTING);

        assertTrue(eventGuestRepo.existsById(AGUESTING.getId()));

        GUESTING.putIntoDeletionQueue();
        BEN.removeOwnedEvent(GUESTING);

        assertTrue(userRepo.existsById(ALYSSA.getId()));
        assertTrue(userRepo.existsById(BEN.getId()));

        assertEquals(eventRepo.count(), 0);
        assertEquals(eventGuestRepo.count(), 0);
        assertEquals(BEN.getEventEntityOwner().size(), 0);
        assertEquals(ALYSSA.getSubscriptions().size(), 0);
    }


}