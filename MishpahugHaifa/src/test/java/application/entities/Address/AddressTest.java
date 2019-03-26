package application.entities.Address;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import application.entities.*;
import application.repositories.*;
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
    public void loadData(){

    }

    @Test
    public void displayAddresses(){
        List<AddressEntity> data = addressRepository.findAll();
        for (AddressEntity x : data) {
            System.out.println(x);
        }
    }



    @Test
    public void filterUserTest(){
        HashMap<String, String> filterForUsers = new HashMap<>();
        filterForUsers.put("gender","male");
  
        List<UserEntity> res = userRepository.searchByFilter(filterForUsers);
        System.out.println("Begin userfilter test");
        System.out.println(res);
        System.out.println("End userfilter test");
        System.out.println("Begin user test");
        System.out.println(userRepository.findByGender(genderRepository.getByName("male")));
        System.out.println("End user test");
        System.out.println(userRepository.findByUserName(names.get(3)));
        HashMap<String, String> updateForUser = new HashMap<>();
        updateForUser.put("lastname","Dusia");

        HashMap<String, String> filterForUpdateUsers = new HashMap<>();
        filterForUpdateUsers.put("username", "Vasiyaaaaaaaaaaaaa");
        System.out.println(userRepository.update(userRepository.getOne(3),filterForUpdateUsers));
    }


}
