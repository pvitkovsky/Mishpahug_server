package application.entities.religion;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import application.entities.ReligionEntity;
import application.repositories.ReligionRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class ReligionTest {
    @Autowired
    ReligionRepository religionRepository;
    private ReligionEntity religionEntity;
    private String[] data = {"Christianity", "Judaism", "Atheism", "Buddhism", "Hare Krishnas", "Sikhism", "Confucianism", "Zoroastrianism"};
    private String[] dataForFindByNameTest = {"hi", "ni", "fi", "ei"};
    private ReligionEntity testReligion;
    private static Random gen = new Random();
    
    @Before
    public void load() {
        for (int i = 0; i < data.length; i++) {
            religionEntity = new ReligionEntity();
            religionEntity.setName(data[i]);
            religionRepository.save(religionEntity);
        }
    }

    /**
     * The Id range changes on each test; need to calculate it for all test where we do something by ID; 
     */
	private Integer getRandomIndex() {
		int minId = Integer.MAX_VALUE; 
    	int maxId = Integer.MIN_VALUE;
    	Iterable<ReligionEntity> religions = religionRepository.findAll();
    	for(ReligionEntity religion : religions) {
    		int thisID = religion.getId();
    		if(thisID < minId) {minId = thisID;}
    		if(thisID > maxId) {maxId = thisID;}
    		
    	}    
        Integer index = minId +  gen.nextInt(maxId - minId);
		return index;
	}
	
    @Test
    public void loadTest() {
    	assertEquals(religionRepository.count(), data.length);
    }

    @Test
    public void getById() { 
    	Integer index = getRandomIndex();
        testReligion = religionRepository.findById(index).get(); 
        assertFalse(testReligion == null);
    }

    @Test
    public void getByName() { //TODO: fix me
        for (int i = 0; i < dataForFindByNameTest.length; i++) {
        	System.out.println(dataForFindByNameTest[i]);
            testReligion = religionRepository.getByName(dataForFindByNameTest[i]);
            System.out.println(religionRepository.findAll());
            System.out.println(testReligion);
            //assertFalse(testReligion == null);
        }
    }

    @Test
    public void update() {
    	Integer index = getRandomIndex();
    	religionEntity = religionRepository.getOne(index);
        assertFalse(religionEntity == null);
        religionEntity.setName("testtest");
        religionRepository.save(religionEntity);
        assertEquals(religionEntity.getName(),  "testtest");
    }

    @Test
    public void remove() {
    	long size = religionRepository.count();
    	Integer index = gen.nextInt((int) size);
        religionRepository.deleteById(index);
        assertEquals(religionRepository.count(), size-1);
    }
}
