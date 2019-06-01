package application.entities.kichentype;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import application.entities.CountryEntity;
import application.entities.KitchenTypeEntity;
import application.repositories.KichenTypeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class KichenTypeTest {
    KitchenTypeEntity kichenTypeEntity;
    @Autowired
    KichenTypeRepository kichenTypeRepository;
    private String[] data = {"kosher milk", "kosher meat", "kosher fur", "non - kosher milk", "non - kosher meat", "non - kosher fur", "n/a"};
    private String[] dataForFindByNameTest = {"non", "iii", "kosher", "fur"};
    private KitchenTypeEntity testKichenType;
    private static Random gen = new Random();
    
    @Before
    public void load() {
        for (int i = 0; i < data.length; i++) {
            kichenTypeEntity = new KitchenTypeEntity();
            kichenTypeEntity.setName(data[i]);
            kichenTypeRepository.save(kichenTypeEntity);
        }
    }
    
    /**
	 * The Id range changes on each test; need to calculate it for all test where we
	 * do something by ID;
	 */
	private Integer getRandomIndex() {
		int minId = Integer.MAX_VALUE;
		int maxId = Integer.MIN_VALUE;
		Iterable<KitchenTypeEntity> entries = kichenTypeRepository.findAll();
		for (KitchenTypeEntity entry : entries) {
			int thisID = entry.getId();
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
    public void loadTest() {
    	assertEquals(kichenTypeRepository.count(), data.length);
    }

    @Test
    public void getById() {
        Integer index = getRandomIndex();
        assertTrue(kichenTypeRepository.getOne(index) != null);
    }

    @Test 
    public void getByName() { //TODO: fix me please
        for (int i = 0; i < dataForFindByNameTest.length; i++) {
            testKichenType = kichenTypeRepository.getByName(dataForFindByNameTest[i]);
            assertTrue(testKichenType != null);
        }
    }

    @Test
    public void update() {
        Integer index = getRandomIndex();
        kichenTypeEntity = kichenTypeRepository.getOne(index);
        kichenTypeEntity.setName("testtest");
        kichenTypeRepository.save(kichenTypeEntity);
        assertEquals(kichenTypeRepository.getOne(index).getName(), "testtest");
    }

    @Test
    public void remove() {
        Integer index = getRandomIndex();
        kichenTypeRepository.deleteById(index);
        assertEquals(kichenTypeRepository.count(), data.length - 1);
    }
}
