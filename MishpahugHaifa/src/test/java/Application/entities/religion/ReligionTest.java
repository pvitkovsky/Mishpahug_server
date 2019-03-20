package application.entities.religion;


import application.entities.ReligionEntity;
import application.repo.ReligionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class ReligionTest {
    private ReligionEntity religionEntity;
    private String[] data = {"Christianity", "Judaism", "Atheism", "Buddhism", "Hare Krishnas", "Sikhism", "Confucianism", "Sikhism", "Zoroastrianism"};
    private String[] dataForFindByNameTest = {"hi", "ni", "fi", "ei"};
    private List<ReligionEntity> testReligion;
    @Autowired
    ReligionRepository religionRepository;
    @Before
    public void load(){
        for (int i = 0; i < data.length; i++){
            religionEntity = new ReligionEntity();
            religionEntity.setName(data[i]);
            religionRepository.save(religionEntity);
        }
    }
    @Test
    public void loadTest(){
        System.out.println(religionRepository.findAll());
    }
    @Test
    public void getById(){
        Random r = new Random();
        Integer index = r.nextInt(data.length-1);
        System.out.println(religionRepository.findById(index));
    }
    @Test
    public void getByName(){
        System.out.println(religionRepository.getByFullName("test"));
        System.out.println(religionRepository.getByFullName("Buddhism"));
        for (int i = 0; i < dataForFindByNameTest.length; i++){
            testReligion = religionRepository.getByName(dataForFindByNameTest[i]);
            System.out.println(dataForFindByNameTest[i] + " included on " + testReligion.size() + " records");
        }
    }
    @Test
    public void update(){
        Integer index = 5;
        religionEntity = religionRepository.getOne(index);
        religionEntity.setName("testtest");
        religionRepository.save(religionEntity);
        System.out.println(religionRepository.findById(index));
    }
    @Test
    public void remove(){
        Integer index = 4;
        System.out.println("Size of repository = " + religionRepository.findAll().size());
        religionRepository.deleteById(index);
        System.out.println("Size of repository = " + religionRepository.findAll().size());
    }
}
