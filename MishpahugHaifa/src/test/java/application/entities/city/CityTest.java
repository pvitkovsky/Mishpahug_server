package application.entities.city;

import application.entities.CityEntity;
import application.entities.CountryEntity;
import application.repositories.CityRepository;
import application.repositories.CountryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
@ActiveProfiles("test")
@Transactional
public class CityTest {

    private static final int MAXCITIES = 100;//to save time on mvn test
    @Autowired
    CityRepository cityRepository;
    @Autowired
    CountryRepository countryRepository;
    private CountryEntity countryEntity;
    private List<CityEntity> testCity;
    private String[] dataForFindByNameTest = {"Kfar", "Beit", "El", "el"};
    private Integer citiesCount = 0;

    @Before
    public void load() {
        addCountry();
        addCity();
    }

    @After
    public void unLoad() {
        cityRepository.deleteAll();
        countryRepository.deleteAll();
    }

    @Test
    public void checkSize() {
        System.out.println("CHECKSIZE");
        for (CityEntity ce : cityRepository.findAll()) {
            System.out.println(ce);
        }
        assertTrue(cityRepository.findAll().size() == citiesCount);
    }


    @Test
    public void update() {
        final String changedName = "testtest";
        assertTrue(cityRepository.findAll().size() == citiesCount);
        cityRepository.findAll().get(0).setName(changedName);
        assertTrue(cityRepository.getByName(changedName).getName().equals(changedName));
    }

    @Test
    public void remove() {
        System.out.println("REMOVE");
        List<CityEntity> cities = cityRepository.findAll();
        for (CityEntity ce : cities) {
            System.out.println(ce);
        } 
        countryRepository.getByName("Israel").removeCity(cityRepository.findById(cities.get(0).getId()).get());
        /* cityRepository.deleteById(cities.get(0).getId()); 
        // city is really managed on the Country side. nice entity-relation design!
        */
        cityRepository.flush();
        assertEquals(cityRepository.count(), citiesCount - 1);
    }

    public void addCountry() {
        countryEntity = new CountryEntity();
        countryEntity.setName("Israel");
        countryRepository.save(countryEntity);
    }

    public void addCity() {
        String detail;
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("data_city.csv");
            // https://stackoverflow.com/q/15749192
            BufferedReader empdtil = new BufferedReader(new InputStreamReader(is));
            int cityLimit = 0; // 1200 cities too much;
            while ((detail = empdtil.readLine()) != null) {
                citiesCount++;
                CityEntity cityEntity = new CityEntity();
                cityEntity.setName(detail);
                countryEntity.addCity(cityEntity);
                countryRepository.save(countryEntity); // city is cascaded;
                cityLimit++;
                if (cityLimit > MAXCITIES) {
                    break;
                } // saving time on mvn test;
            }
            empdtil.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
