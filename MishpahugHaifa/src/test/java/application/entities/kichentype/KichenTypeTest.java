package application.entities.kichentype;

import application.entities.KitchenTypeEntity;
import application.repositories.KichenTypeRepository;
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
public class KichenTypeTest {
    private String[] data = {"kosher milk", "kosher meat", "kosher fur", "non - kosher milk", "non - kosher meat", "non - kosher fur", "n/a"};
    private String[] dataForFindByNameTest = {"non", "iii", "kosher", "fur"};
    KitchenTypeEntity kichenTypeEntity;
    @Autowired
    KichenTypeRepository kichenTypeRepository;
    private KitchenTypeEntity testKichenType;

    @Before
    public void load(){
        for (int i = 0; i < data.length; i++){
            kichenTypeEntity = new KitchenTypeEntity();
            kichenTypeEntity.setName(data[i]);
            kichenTypeRepository.save(kichenTypeEntity);
        }
    }
    @Test
    public void loadTest(){
        System.out.println(kichenTypeRepository.findAll());
    }
    @Test
    public void getById(){
        Integer index = 3;
        System.out.println(kichenTypeRepository.findById(index));
    }
    @Test
    public void getByName(){
        System.out.println(kichenTypeRepository.getByName("test"));
        System.out.println(kichenTypeRepository.getByName("Buddhism"));
        for (int i = 0; i < dataForFindByNameTest.length; i++){
            testKichenType = kichenTypeRepository.getByName(dataForFindByNameTest[i]);
            System.out.println(dataForFindByNameTest[i] + " included on " + testKichenType);
        }
    }
    @Test
    public void update(){
        Integer index = 5;
        kichenTypeEntity = kichenTypeRepository.getOne(index);
        kichenTypeEntity.setName("testtest");
        kichenTypeRepository.save(kichenTypeEntity);
        System.out.println(kichenTypeRepository.findById(index));
    }
    @Test
    public void remove(){
        Integer index = 4;
        System.out.println("Size of repository = " + kichenTypeRepository.findAll().size());
        kichenTypeRepository.deleteById(index);
        System.out.println("Size of repository = " + kichenTypeRepository.findAll().size());
    }
}
