package Application.removegetfeedbacktest;

import Application.entities.EventEntity;
import Application.entities.FeedBackEntity;
import Application.entities.UserEntity;
import Application.repo.EventRepository;
import Application.repo.FeedBackRepository;
import Application.repo.UserRepository;
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
import java.util.Date;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class removegetfeedbacktest {

    private static final String[] names = {"David",
            "Ivan", "Alex", "Dasha", "Pasha",
            "Masha", "Ura", "Tanja", "Sarah",
            "Tom", "Anna", "Felix", "Jone",
            "Marina", "Mark", "Haim", "Phillip"};
    private static final String[] namesOfEvents = {"Purim",
            "Shabat", "Posh-Ashana", "Hanuka", "Tu-Bi-Shvat",
            "Lag-Ba-Omer", "Nar-Mitsva"};
    private UserEntity[] userEntities = new UserEntity[names.length];
    private EventEntity[] eventEntities = new EventEntity[namesOfEvents.length];

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FeedBackRepository feedBackRepository;


    @Before
    public void buildEntities() {
        //To do
        // generator for users
        for (int i = 0; i < names.length; i++){
            userEntities[i] = new UserEntity();
            userEntities[i].setNickname(names[i]);
            userEntities[i].setEMail(names[i] + "@gmail.com");
            userEntities[i].setFirstName(names[i]);
            userEntities[i].setPhoneNumber("44444" + i);
            userEntities[i].setLastName("Ivanov");
            userRepository.save(userEntities[i]);
        }
        //generator for events
        for (int i = 0; i < namesOfEvents.length; i++){
            eventEntities[i] = new EventEntity();
            eventEntities[i].setNameOfEvent(namesOfEvents[i]);
            eventEntities[i].setDate(LocalDate.of(2019, 1, (i + 1) * 2));
            eventEntities[i].setTime(LocalTime.of(11,00));
            eventEntities[i].setUserEntityOwner(userEntities[i]);
            eventEntities[i].subscribe(userEntities[namesOfEvents.length + i]);
            eventEntities[i].setStatus(EventEntity.EventStatus.CREATED);
            eventRepository.save(eventEntities[i]);
        }

    }

    @Test
    public void addTest(){
        //To do
        for (int i = 0; i < namesOfEvents.length; i++){
            FeedBackEntity feedBackEntity = new FeedBackEntity();
            feedBackEntity.setDateTime(LocalDateTime.of(2020,
                    2,
                    (i + 1) *2,
                    10,
                    00));

            //feedBackEntity.setUserItem(userEntities[i + eventEntities.length]);
            feedBackEntity.setComment(userEntities[i].getNickname() + "@"
                    + eventEntities[i].getNameOfEvent());
            feedBackEntity.setData( userEntities[i], eventEntities[i]);
            userRepository.save(userEntities[i]);
            eventRepository.save(eventEntities[i]);
            feedBackRepository.save(feedBackEntity);
        }
        for (int i = 0; i < namesOfEvents.length; i++){
            FeedBackEntity feedBackEntity = new FeedBackEntity();
            feedBackEntity.setDateTime(LocalDateTime.of(2010,
                    1,
                    (i + 1) *2,
                    20,
                    00));
            feedBackEntity.setEventItem(eventEntities[i]);
            //feedBackEntity.setUserItem(userEntities[i]);
            feedBackEntity.setUserItem(userEntities[i + eventEntities.length]);
            feedBackEntity.setComment(userEntities[i].getNickname() + "@"
                    + eventEntities[i].getNameOfEvent());
            userEntities[i].getFeedBacks().put(i + eventEntities.length, feedBackEntity);
            userRepository.save(userEntities[i + eventEntities.length]);
            feedBackRepository.save(feedBackEntity);
            userRepository.save(userEntities[i + eventEntities.length]);
            eventRepository.save(eventEntities[i]);
        }
        System.out.println("@ " + userRepository.getOne(userEntities[2].getId()).getFeedBacks().size());
        //System.out.println("@ " + eventRepository.findAll().size());
    }

    @Test
    public void getTest(){
        //To do
    }

    @Test
    public void removeTest(){
        //To do
    }

}
