package application.entities.country;

import application.entities.properties.CountryEntity;
import application.repositories.CountryRepository;
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
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class CountryTest {
	@Autowired
	CountryRepository countryRepository;
	long counter = 0;
	private CountryEntity countryEntity;
	private static Random gen = new Random();

	@Before
	public void load() {
		String detail;
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			InputStream is = classloader.getResourceAsStream("data_country.csv");
			BufferedReader empdtil = new BufferedReader(new InputStreamReader(is));
			while ((detail = empdtil.readLine()) != null) {
				CountryEntity countryEntity = new CountryEntity();
				countryEntity.setName(detail);
				countryRepository.save(countryEntity);
				counter++;
			}
			empdtil.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());

		}
	}

	/**
	 * The Id range changes on each test; need to calculate it for all test where we
	 * do something by ID;
	 */
	private Integer getRandomIndex() {
		int minId = Integer.MAX_VALUE;
		int maxId = Integer.MIN_VALUE;
		Iterable<CountryEntity> countries = countryRepository.findAll();
		for (CountryEntity country : countries) {
			int thisID = country.getId();
			if (thisID < minId) {
				minId = thisID;
			}
			if (thisID > maxId) {
				maxId = thisID;
			}

		}
		Integer index = minId + gen.nextInt(maxId - minId);
		return index;
	}

	@Test
	public void testData() {
		assertEquals(countryRepository.count(), counter);
	}

	@Test
	public void update() {
		Integer index = getRandomIndex();
		countryEntity = countryRepository.getOne(index);
		countryEntity.setName("testtest");
		countryRepository.save(countryEntity);
		assertEquals(countryRepository.getOne(index).getName(), "testtest");
	}

	@Test
	public void remove() {
		long size = countryRepository.count();
		Integer index = getRandomIndex();
		countryRepository.deleteById(index);
		assertEquals(countryRepository.count(), size - 1);

	}

	@Test
	public void getById() {
		Integer index = getRandomIndex();
		assertTrue(countryRepository.getOne(index) != null);
	}

	@Test
	public void getByName() {
		assertTrue(countryRepository.getByName("Belarus") != null);
	}
}
