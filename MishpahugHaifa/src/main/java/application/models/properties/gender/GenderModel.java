package application.models.properties.gender;

import application.entities.properties.GenderEntity;
import application.repositories.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GenderModel implements IGenderModel {

    @Autowired
    GenderRepository genderRepository;

    @Override
    public GenderEntity getByName(String name) {
        return genderRepository.getByName(name);
    }

    @Override
    public GenderEntity getById(Integer id) {
        return genderRepository.getOne(id);
    }

    @Override
    public void delete(String name) {
        genderRepository.deleteByName(name);
    }

    @Override
    public void delete(Integer id) {
        genderRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        genderRepository.deleteAll();
    }


    @Override
    public GenderEntity add(GenderEntity data) {
        return genderRepository.saveAndFlush(data);
    }

    @Override
    public GenderEntity updateName(Integer id, String name){
         GenderEntity cityEntity = genderRepository.getOne(id);
         cityEntity.setName(name);
         return genderRepository.saveAndFlush(cityEntity);
    }

    @Override
    public List<GenderEntity> getAll() {
        return genderRepository.findAll();
    }
}
