//TODO: update to new feedback design

//package application.removegetfeedbacktest;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.util.List;
//import java.util.Set;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import application.entities.EventEntity;
//import application.entities.FeedBackEntity;
//import application.entities.UserEntity;
//import application.repo.EventRepository;
//import application.repo.FeedBackRepository;
//import application.repo.UserRepository;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//@ActiveProfiles("test")
//@Transactional
//public class removegetfeedbacktest {
//
//    private static final String[] userNames = {"David",
//            "Ivan", "Alex", "Dasha", "Pasha",
//            "Masha", "Ura", "Tanja", "Sarah",
//            "Tom", "Anna", "Felix", "Jone",
//            "Marina", "Mark", "Haim", "Phillip"};
//    private static final String[] eventNames = {"Purim",
//            "Shabat", "Rosh-Ashana", "Hanuka", "Tu-Bi-Shvat",
//            "Lag-Ba-Omer", "Bar-Mitsva", "Women's day"};
//    private UserEntity[] userEntities = new UserEntity[userNames.length];
//    private EventEntity[] eventEntities = new EventEntity[eventNames.length];
//
//    @Autowired
//    EventRepository eventRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    FeedBackRepository feedBackRepository;
//
//
//    @Before
//    public void buildEntities() {
//        //To do
//        // generator for users
//        for (int i = 0; i < userNames.length; i++){
//            userEntities[i] = new UserEntity();
//            userEntities[i].setEMail(userNames[i] + "@gmail.com");
//            userEntities[i].setFirstName(userNames[i]);
//            userEntities[i].setPhoneNumber("44444" + i);
//            userEntities[i].setLastName("Ivanov");
//        }
//        //generator for events
//        for (int i = 0; i < eventNames.length; i++){
//            eventEntities[i] = new EventEntity();
//            eventEntities[i].setNameOfEvent(eventNames[i]);
//            eventEntities[i].setDate(LocalDate.of(2019, 1, (i + 1) * 2));
//            eventEntities[i].setTime(LocalTime.of(11,00));
//            userEntities[i].makeOwner(eventEntities[i]); 
//            // eventEntities[i].setUserEntityOwner(userEntities[i]); //should use makeOwner; 
//            eventEntities[i].subscribe(userEntities[eventNames.length - i - 1]); // subscribe cascade saves a user; therefore double users here;  
//            eventEntities[i].setStatus(EventEntity.EventStatus.CREATED);
//        }
//
//    }
//
//    @Test
//    public void addTest(){
//        //To do: 2 and more events for user; 2 and more feedbacks etc. 
//        for (int i = 0; i < eventNames.length; i++){
//        	System.out.println("saving " + i);
//        	userRepository.save(userEntities[i]);
//            eventRepository.save(eventEntities[i]);
//            FeedBackEntity feedBackEntity = new FeedBackEntity();
//            feedBackEntity.setDateTime(LocalDateTime.of(2010,
//                    1,
//                    (i + 1) *2,
//                    20,
//                    00));
//
//            feedBackEntity.setEventItem(eventEntities[i]);
//            feedBackEntity.setUserItem(userEntities[i]);
//            feedBackEntity.setComment("@"
//                    + eventEntities[i].getNameOfEvent());
//            feedBackEntity.setRating(5);
//            feedBackRepository.save(feedBackEntity);
//            userEntities[i].addFeedBack(feedBackEntity);
//            eventEntities[i].addFeedBack(feedBackEntity);
//            
//        }
//        
//        System.out.println("USER PRINTOUT");
//        System.out.println("==============================================");
//        List<UserEntity> dataU = userRepository.findAll();
//        Integer savedUsersCount = dataU.size();
//        for (int i = 0; i < savedUsersCount; i++){
//        	UserEntity savedU = dataU.get(i);
//        	System.out.println("Feedback of begin");
//        	savedU.getFeedbacks().values().forEach(System.out::println);
//            System.out.println("Feedback of end");
//            System.out.println("");
//
//        }
//
//        System.out.println("EVENT PRINTOUT");
//        System.out.println("==============================================");
//        List<EventEntity> dataE = eventRepository.findAll();
//        Integer savedEventsCount = dataE.size();
//        for (int i = 0; i < savedEventsCount; i++){
//        	EventEntity savedE = dataE.get(i);
//            System.out.println("Feedback of " + savedE.getNameOfEvent() + " begin");
//        	savedE.getFeedbacks().values().forEach(System.out::println);
//            System.out.println("Feedback of " + savedE.getNameOfEvent() + " end");
//            System.out.println("");
//        }
//
//        System.out.println("FULL PRINTOUT");
//        System.out.println("==============================================");
//        for (int i = 0; i<savedUsersCount;i++){
//        	UserEntity savedU = dataU.get(i);
//        	System.out.println("User " + savedU);
//        	System.out.println("User feedback collection size " + savedU.getFeedbacks().size());
//        	savedU.getFeedbacks().values().forEach(System.out::println);
//        	Set<EventEntity> savedEsfromU = savedU.getEventEntityOwner();
//        	savedEsfromU.forEach(System.out::println);
//        	savedEsfromU.forEach(e -> e.getFeedbacks().values().forEach(System.out::println));
//        }
//
//        System.out.println("@ " + eventRepository.findAll().size());
//    }
//
