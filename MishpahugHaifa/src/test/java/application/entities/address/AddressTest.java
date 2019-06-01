package application.entities.address;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import application.entities.AddressEntity;
import application.repositories.AddressRepository;
import application.repositories.CityRepository;
import application.repositories.CountryRepository;
import application.repositories.GenderRepository;
import application.repositories.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class AddressTest {

    @Autowired
    CityRepository cityRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GenderRepository genderRepository;
    List<String> streets = new ArrayList<>();
    List<String> names = new ArrayList<>();


    @Before
    public void loadData() {

    }

    @Test
    public void displayAddresses() {
        List<AddressEntity> data = addressRepository.findAll();
        for (AddressEntity x : data) {
            System.out.println(x);
        }

    }

}
