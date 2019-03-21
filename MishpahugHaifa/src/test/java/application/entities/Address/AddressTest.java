package application.entities.Address;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
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
import application.entities.CityEntity;
import application.entities.CountryEntity;
import application.entities.UserEntity;
import application.repo.AddressRepository;
import application.repo.CityRepository;
import application.repo.CountryRepository;
import application.repo.UserRepository;

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
    List<String> streets = new ArrayList<>();
    List<String> names = new ArrayList<>();

    public void addCity() {
        String detail;
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("cities.csv");
            // https://stackoverflow.com/q/15749192
            CountryEntity countryEntity = new CountryEntity();
            countryEntity.setName("Israel");
            BufferedReader empdtil = new BufferedReader(new InputStreamReader(is));
            while ((detail = empdtil.readLine()) != null) {
                CityEntity cityEntity = new CityEntity();
                cityEntity.setName(detail);
                countryEntity.addCity(cityEntity);
                countryRepository.save(countryEntity); //city is cascaded;
            }
            empdtil.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addAddress(){
        String detail;
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("streets.csv");
            // https://stackoverflow.com/q/15749192
            BufferedReader empdtil = new BufferedReader(new InputStreamReader(is));
            while ((detail = empdtil.readLine()) != null) {
                streets.add(detail);
            }
            empdtil.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addNames(){
        String detail;
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("names.csv");
            // https://stackoverflow.com/q/15749192
            BufferedReader empdtil = new BufferedReader(new InputStreamReader(is));
            while ((detail = empdtil.readLine()) != null) {
                names.add(detail);
            }
            empdtil.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void generatorOfAddresses(){
        List<CityEntity> cities = cityRepository.findAll();
        for (CityEntity z : cities) {
            for (String x : streets) {
                AddressEntity addressEntity = new AddressEntity();
                //addressEntity.setCityEntity(z);
                z.addAddress(addressEntity);
                addressEntity.setStreet(x);
                addressEntity.setBuilding(4);
                addressEntity.setApartment(11);
                addressRepository.save(addressEntity);
            }
        }
    }

    @Before
    public void loadData(){
        addCity();
        addAddress();
        generatorOfAddresses();
        addNames();
        addUsers();
    }

    @Test
    public void displayAddresses(){
        List<AddressEntity> data = addressRepository.findAll();
        for (AddressEntity x : data) {
            System.out.println(x);
        }
    }

    public void addUsers(){
        List<AddressEntity> data = addressRepository.findAll();
        /*for (AddressEntity x : data) {
            for (String z : names) {
                UserEntity userEntity = new UserEntity();
                userEntity.setUserName(z);
                userEntity.setAddressEntity(x);
                //userEntity.setEMail(x+x.getId().toString()+"@tut.by");
                userRepository.save(userEntity);
            }
        }*/
        for (int i=0; i<names.size();i++){
            UserEntity userEntity = new UserEntity();
            userEntity.setUserName("657" + names.get(i) + "1212");
            userEntity.setFirstName(names.get(i));
            data.get(i).addUser(userEntity);
            userEntity.setEMail(names.get(i)+i+"@tut.by");
            userRepository.save(userEntity);
        }
    }

    @Test
    public void filterUserTest(){
        HashMap<String, String> filterForUsers = new HashMap<>();
        filterForUsers.put("username",names.get(3));
  
        List<UserEntity> res = userRepository.searchByFilter(filterForUsers);
        System.out.println("Begin userfilter test");
        System.out.println(res);
        System.out.println("End userfilter test");
        System.out.println(userRepository.findByUserName(names.get(3)));
        HashMap<String, String> updateForUser = new HashMap<>();
        updateForUser.put("lastname","Dusia");

        HashMap<String, String> filterForUpdateUsers = new HashMap<>();
        filterForUpdateUsers.put("username", "Vasiyaaaaaaaaaaaaa");
        System.out.println(userRepository.update(3,filterForUpdateUsers));
    }


}
