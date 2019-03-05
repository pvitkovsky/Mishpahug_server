package Application.removegetfeedbacktest;

import Application.entities.UserEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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

    @Before
    public void buildEntities() {
        //To do
        // generator for users
        for (int i = 0; i < names.length; i++){
            UserEntity userEntity = new UserEntity();
            userEntity.setNickname(names[i]);
            userEntity.setEMail(names[i] + "@gmail.com");
            userEntity.setFirstName(names[i]);
            userEntity.setPhoneNumber("44444" + i);
            userEntity.setLastName("Ivanov");
        }
        //generator for events


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
