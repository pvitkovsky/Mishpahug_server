package application.models.gender;

import java.util.List;

import javax.transaction.Transactional;

import application.exceptions.EntityExistsDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entities.GenderEntity;
import application.repositories.GenderRepository;

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
    public void deleteByName(String name) {
        genderRepository.deleteByName(name);
    }

    @Override
    public void deleteAll() {
        genderRepository.deleteAll();
    }


    @Override
    public GenderEntity add(GenderEntity data) throws EntityExistsDException {
        return genderRepository.saveAndFlush(data);
    }

    @Override
    public GenderEntity updateName(Integer id, String name) throws EntityExistsDException {
         GenderEntity cityEntity = genderRepository.getOne(id);
         cityEntity.setName(name);
         return genderRepository.saveAndFlush(cityEntity);
    }

    @Override
    public List<GenderEntity> getAll() {
        return genderRepository.findAll();
    }
}
