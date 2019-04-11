package application.entities.user;

import application.entities.AddressEntity;
import application.entities.UserEntity;
import application.repositories.AddressRepository;
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

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Relation: OneToMany User is the primary entity. Event must have a user as its
 * owner.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class UserEntityTest {


    private final UserEntity ALYSSADUPLICATE = new UserEntity();
    private final AddressEntity AADDRESS = new AddressEntity();
    private final Map<String, String> AUPDATE = new HashMap<>();
    @Autowired
    UserRepository userRepo;
    @Autowired
    AddressRepository addressRepository;
    private UserEntity ALYSSA = new UserEntity();

    @Before
    public void buildEntities() {
        ALYSSA.setEMail("aliseS@gmail.com");
        AADDRESS.setStreet("Chuguev");
        AADDRESS.setApartment(33);
        AADDRESS.setBuilding(3);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void givenDuplicateUsersSaveAndGetException() {

        userRepo.save(ALYSSA);
        userRepo.save(ALYSSADUPLICATE);
    }

    @Test
    public void getByName() {

        userRepo.save(ALYSSA);

        ALYSSA.setLastName("lhlkhl");
        ALYSSA.setFirstName("Alise");
        ALYSSA.setUserName("aliseS");
        ALYSSA.setEncrytedPassword("ghluikgluglujgog");

        assertEquals(userRepo.findByUserName("aliseS"), ALYSSA);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void savedUserChangeStatusWithIllegalStringThrows() {

        userRepo.save(ALYSSA);

        AUPDATE.put("status", "foo");
        userRepo.update(ALYSSA, AUPDATE);
    }

    @Test
    public void savedUserChangeStatusWithLegalStringWorks() {

        userRepo.save(ALYSSA);
        assertTrue(ALYSSA.isEnabled());

        AUPDATE.put("status", "DEACTIVATED");
        userRepo.update(ALYSSA, AUPDATE);
        assertFalse(ALYSSA.isEnabled());
        AUPDATE.clear();

        AUPDATE.put("status", "ACTIVE");
        userRepo.update(ALYSSA, AUPDATE);
        assertTrue(ALYSSA.isEnabled());
        AUPDATE.clear();

        AUPDATE.put("status", "PENDINGFORDELETION");
        userRepo.update(ALYSSA, AUPDATE);
        assertTrue(ALYSSA.isPendingForDeletion());
        AUPDATE.clear();

        userRepo.delete(ALYSSA);
        assertEquals(userRepo.count(), 0);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void onUserDeleteWithoutQueueThrows() {


        userRepo.save(ALYSSA);
        userRepo.delete(ALYSSA);
        userRepo.flush();

    }

    @Test
    public void onUserDeleteWithQueueWorks() {

        userRepo.save(ALYSSA);
        ALYSSA.putIntoDeletionQueue();
        userRepo.delete(ALYSSA);

        assertEquals(userRepo.count(), 0);

    }
}