package application.models;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import application.entities.UserEntity;
import application.models.user.IUserModel;
import application.models.user.UserModel;
import application.repositories.UserRepository;
import application.utils.converter.IUpdates;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UserModelTest {

    private final UserEntity ALYSSA = new UserEntity();
    private final UserEntity ALYSSADUPLICATE = new UserEntity();
    @MockBean
    UserRepository userRepo;
	@MockBean
	IUpdates updates;
    @Autowired
    private IUserModel userModel;

    @Before
    public void buildEntities() {
        ALYSSA.setEMail("p_hacker@sicp.edu");
        ALYSSADUPLICATE.setEMail("p_hacker@sicp.edu");
    }

    @Test
    public void add() { //TODO: proper named methods please

        Mockito.when(userRepo.existsById(ALYSSA.getId())).thenReturn(false).thenReturn(true);
        Mockito.when(userRepo.save(ALYSSA)).thenReturn(ALYSSA);
        Mockito.when(userRepo.getOne(ALYSSA.getId())).thenReturn(ALYSSA);


        ///assertEquals(userModel.add(ALYSSA), ALYSSA);
        //assertEquals(userModel.getById(ALYSSA.getId()), ALYSSA);
        //assertEquals(userModel.add(ALYSSA), ALYSSA);
    }

    @TestConfiguration
    static class MPHServiceConfig {
        @Bean
        public IUserModel userModel() {
            return new UserModel();
        }
    }

}
