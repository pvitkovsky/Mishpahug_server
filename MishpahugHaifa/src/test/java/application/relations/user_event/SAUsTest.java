package application.relations.user_event;

import application.entities.EventEntity;
import application.entities.UserEntity;
import application.repositories.EventRepository;
import application.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class SAUsTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    EventRepository eventRepository;

    EventEntity eventEntity1;
    EventEntity eventEntity2;
    UserEntity userEntity1;
    UserEntity userEntity2;
    UserEntity userEntity3;
    UserEntity userEntity4;

//    @Before
//    public void load(){
//        eventEntity1 = new EventEntity();
//        eventEntity2 = new EventEntity();
//        userEntity1 = new UserEntity();
//        userEntity1.setEMail("vasia@gmail.com");
//        userEntity1.setFirstName("Vasia");
//        userEntity1.setLastName("Fedorov");
//        userEntity1.setPhoneNumber("3453453");
//        userEntity2 = new UserEntity();
//        userEntity2.setEMail("dadid@gmail.com");
//        userEntity2.setFirstName("dadid");
//        userEntity2.setLastName("Petrov");
//        userEntity2.setPhoneNumber("345345");
//
//        userEntity3 = new UserEntity();
//        userEntity3.setEMail("sasha@gmail.com");
//        userEntity3.setFirstName("Sasha");
//        userEntity3.setLastName("Guskin");
//        userEntity3.setPhoneNumber("354345345");
//        userEntity4 = new UserEntity();
//        userEntity4.setEMail("pasha@gmail.com");
//        userEntity4.setFirstName("Pasha");
//        userEntity4.setLastName("Popov");
//        userEntity4.setPhoneNumber("345345345");
//
//    }
//    @Test
//    public void savedata(){
////            eventEntity1.subscribe(userEntity3); //TODO: update subscriptions;
////            eventEntity2.subscribe(userEntity4);
//            userEntity2.makeOwner(eventEntity1);
//            userEntity1.makeOwner(eventEntity2);
//            userRepository.save(userEntity1);
//            userRepository.save(userEntity2);
//            userRepository.save(userEntity3);
//            userRepository.save(userEntity4);
//            eventRepository.save(eventEntity1);
//            eventRepository.save(eventEntity2);
//            System.out.println("Test Bebin");
//            System.out.println(eventEntity1);
//            System.out.println(eventEntity2);
//            System.out.println(eventEntity1.getSubscriptions());
//            System.out.println(eventEntity2.getSubscriptions());
//            System.out.println(userEntity1);
//            System.out.println(userEntity2);
//            System.out.println(userEntity3.getSubscriptions());
//            System.out.println(userEntity4.getSubscriptions());
//            System.out.println("Test End");
//        }
//



}
