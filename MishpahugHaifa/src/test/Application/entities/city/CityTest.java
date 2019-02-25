package Application.entities.city;

import Application.repo.CityRepository;
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
public class CityTest {
    @Autowired
    CityRepository cityRepository;
    @Before
    public void clear() {

    }

    @Before
    public void buildCity() {

    }

    @Test
    public void addCity() {

    }

}
