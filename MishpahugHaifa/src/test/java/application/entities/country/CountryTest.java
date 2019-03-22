package application.entities.country;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import application.entities.CountryEntity;
import application.repositories.CountryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class CountryTest {
    private String[] dataForFindByNameTest = {"an", "ci", "ia", "rus", "lu"};
    private CountryEntity countryEntity;
    private List<CountryEntity> testCountry;
    @Autowired
    CountryRepository countryRepository;

    @Before
    public void load(){
        String detail;
        try {
        	ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			InputStream is = classloader.getResourceAsStream("data_country.csv");
            BufferedReader empdtil = new BufferedReader(new InputStreamReader(is));
            while ((detail = empdtil.readLine()) != null) {
                CountryEntity countryEntity = new CountryEntity();
                countryEntity.setName(detail);
                countryRepository.save(countryEntity);
                System.out.println(countryEntity);
            }
            empdtil.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());


        }
    }

    @Test
    public void testData(){
        System.out.println("Size of repository = " + countryRepository.findAll().size());
    }

    @Test
    public void update(){
        Integer index = 6;
        countryEntity = countryRepository.getOne(index);
        System.out.println(countryEntity);
        countryEntity.setName("testtest");
        countryRepository.save(countryEntity);
        System.out.println(countryRepository.getOne(index));
    }

    @Test
    public void remove(){
        Integer index = 4;
        System.out.println("Size of repository = " + countryRepository.findAll().size());
        countryRepository.deleteById(index);
        System.out.println("Size of repository = " + countryRepository.findAll().size());
    }

    @Test
    public void getById(){
        Integer index = 4;
        System.out.println(countryRepository.getOne(index));
    }

    @Test
    public void getByName(){
        System.out.println(countryRepository.getByFullName("Belarus"));
        for (int i = 0; i < dataForFindByNameTest.length; i++){
            testCountry = countryRepository.getByName(dataForFindByNameTest[i]);
            System.out.println(dataForFindByNameTest[i] + " included on " + testCountry.size() + " records ");
            System.out.println(testCountry);
        }
    }
}
