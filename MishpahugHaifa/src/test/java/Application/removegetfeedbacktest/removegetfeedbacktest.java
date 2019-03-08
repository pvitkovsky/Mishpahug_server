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
            //userRepository.save(userEntities[i]);
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
            //eventRepository.save(eventEntities[i]);
        }

    }

    @Test
    public void addTest(){
        //To do
        for (int i = 0; i < namesOfEvents.length; i++){
            FeedBackEntity feedBackEntity = new FeedBackEntity();
            feedBackEntity.setDateTime(LocalDateTime.of(2010,
                    1,
                    (i + 1) *2,
                    20,
                    00));

            feedBackEntity.setEventItem(eventEntities[i]);
            feedBackEntity.setUserItem(userEntities[i]);
            feedBackEntity.setComment(userEntities[i].getNickname() + "@"
                    + eventEntities[i].getNameOfEvent());
            userEntities[i + eventEntities.length].addFeedBack(feedBackEntity);
            feedBackRepository.save(feedBackEntity);

            userRepository.save(userEntities[i]);
            eventRepository.save(eventEntities[i]);

        }
        for (int i = 0; i < namesOfEvents.length; i++){
            FeedBackEntity feedBackEntity = new FeedBackEntity();
            feedBackEntity.setDateTime(LocalDateTime.of(2010,
                    1,
                    (i + 1) *2,
                    20,
                    00));
            feedBackEntity.setEventItem(eventEntities[i]);
            feedBackEntity.setUserItem(userEntities[i + eventEntities.length]);
            feedBackEntity.setComment(userEntities[i + eventEntities.length].getNickname() + "@"
                    + eventEntities[i].getNameOfEvent());
            userRepository.save(userEntities[i + eventEntities.length]);
            feedBackRepository.save(feedBackEntity);
            userEntities[i + eventEntities.length].addFeedBack(feedBackEntity);
            eventRepository.save(eventEntities[i]);
            userRepository.save(userEntities[i + eventEntities.length]);

        }
        Integer z = userRepository.findAll().size();
        for (int i = 0; i<z;i++){
            System.out.println("@ " + userRepository.findAll().get(i).getFeedBacks());
        }
        z = eventRepository.findAll().size();
        for (int i = 0; i<z;i++){
            System.out.println("@ " + eventRepository.findAll().get(i).getFeedbacks());
        }

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
