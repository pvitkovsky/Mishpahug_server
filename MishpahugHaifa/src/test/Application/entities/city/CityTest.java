package Application.entities.city;

import Application.entities.CityEntity;
import Application.entities.CountryEntity;
import Application.repo.CityRepository;
import Application.repo.CountryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class CityTest {
    private CountryEntity countryEntity;
    private List<CityEntity> testCity;
    private String[] dataForFindByNameTest = {"Kfar", "Beit", "El", "el"};
    //private CityEntity cityEntity;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    CountryRepository countryRepository;
    @Before
    public void load(){
        addCountry();
        addCity();
    }

    @Test
    public void loadData(){
        System.out.println(cityRepository.findAll().size());
    }
    @Test
    public void findByName(){
        System.out.println(cityRepository.getByFullName("Beit Nir"));
        System.out.println(cityRepository.getByFullName("Beitfff Nir"));
        for (int i = 0; i < dataForFindByNameTest.length; i++)
        {
            testCity = cityRepository.getByName(dataForFindByNameTest[i]);
            System.out.println(dataForFindByNameTest[i] + " included on " + testCity.size() + " records");
        }
    }

    @Test
    public void update(){
        Integer index = 33;
        CityEntity cityEntity = cityRepository.getOne(index);
        System.out.println(cityEntity);
        cityEntity.setName("testtest");
        cityRepository.save(cityEntity);
        System.out.println(cityRepository.getOne(index));
    }

    @Test
    public void remove(){
        Integer index = 3;
        System.out.println(cityRepository.findAll().size());
        cityRepository.deleteById(index);
        System.out.println(cityRepository.findAll().size());
    }

    public void addCountry() {
        countryEntity = new CountryEntity();
        countryEntity.setName("Israel");
        System.out.println(countryEntity);
        countryRepository.save(countryEntity);
        System.out.println(countryEntity);
    }

    public void addCity() {
        //FileReader fr= new FileReader("d:\\DropBox_Java\\Dropbox\\Mishpahug_server\\data.csv");
        String detail;
        try {
            BufferedReader empdtil = new BufferedReader(new FileReader("d:\\DropBox_Java\\Dropbox\\Mishpahug_server\\data.csv"));
            while ((detail = empdtil.readLine()) != null) {
                CityEntity cityEntity = new CityEntity();
                cityEntity.setCountryEntity(countryEntity);
                cityEntity.setName(detail);
                cityRepository.save(cityEntity);
                System.out.println(cityEntity);
            }
            empdtil.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());


        }
    }
}
