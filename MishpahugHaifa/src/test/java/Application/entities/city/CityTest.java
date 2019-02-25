package Application.entities.city;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import Application.entities.CityEntity;
import Application.entities.CountryEntity;
import Application.repo.CityRepository;
import Application.repo.CountryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = false) //
@ActiveProfiles("test")
@Transactional
public class CityTest {

	private CountryEntity countryEntity;
	private List<CityEntity> testCity;
	private String[] dataForFindByNameTest = { "Kfar", "Beit", "El", "el" };
	private Integer citiesCount = 0;

	@Autowired
	CityRepository cityRepository;
	@Autowired
	CountryRepository countryRepository;

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
		for(CityEntity ce : cityRepository.findAll()) {
			System.out.println(ce);
		}
		assertTrue(cityRepository.findAll().size() == citiesCount);
	}

//	@Test
//	public void findByName() {
//		for (int i = 0; i < dataForFindByNameTest.length; i++) {
//			testCity = cityRepository.getByName(dataForFindByNameTest[i]);
//			System.out.println(dataForFindByNameTest[i] + " included on " + testCity.size() + " records");
//		}
//	}

	@Test
	public void update() {

		
		final Integer indexToChange = 33;
		final String changedName = "testtest";

		assertTrue(cityRepository.findAll().size() == citiesCount);

		System.out.println("UPDATE");
		for(CityEntity ce : cityRepository.findAll()) {
			System.out.println(ce);
		}
		
		CityEntity cityEntity = cityRepository.getOne(indexToChange);
		cityEntity.setName(changedName);
		cityRepository.save(cityEntity);

		assertTrue(cityRepository.getOne(indexToChange).getName().equals(changedName));
	}

	@Test
	public void remove() {
		System.out.println("REMOVE");
		for(CityEntity ce : cityRepository.findAll()) {
			System.out.println(ce);
		}
		Integer index = 3;
		cityRepository.deleteById(index);
		assertTrue(cityRepository.findAll().size() == citiesCount - 1);
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
			InputStream is = classloader.getResourceAsStream("data.csv");
			// https://stackoverflow.com/q/15749192
			BufferedReader empdtil = new BufferedReader(new InputStreamReader(is));
			while ((detail = empdtil.readLine()) != null) {
				citiesCount++;
				CityEntity cityEntity = new CityEntity();
				cityEntity.setCountryEntity(countryEntity);
				cityEntity.setName(detail);
				cityRepository.save(cityEntity);
			}
			empdtil.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
