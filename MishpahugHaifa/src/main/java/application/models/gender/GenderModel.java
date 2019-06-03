package application.models.gender;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entities.GenderEntity;
import application.exceptions.ExceptionMishpaha;
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
    public void deleteByName(String name) throws ExceptionMishpaha {
        try {
            genderRepository.deleteByName(name);
        } catch (Exception e) {
            throw new ExceptionMishpaha(this.getClass().toString(), e);
        }
    }

    @Override
    public void deleteAll() {
        genderRepository.deleteAll();
    }


    @Override
    public GenderEntity add(GenderEntity data) throws ExceptionMishpaha {
        try {
            return genderRepository.saveAndFlush(data);
        } catch (Exception e) {
            throw new ExceptionMishpaha(this.getClass().toString(), e);
        }
    }

    @Override
    public GenderEntity updateName(Integer id, String name) throws ExceptionMishpaha {
        try {
            GenderEntity cityEntity = genderRepository.getOne(id);
            cityEntity.setName(name);
            return genderRepository.saveAndFlush(cityEntity);
        } catch (Exception e) {
            throw new ExceptionMishpaha(this.getClass().toString(), e);
        }
    }

    @Override
    public List<GenderEntity> getAll() {
        return genderRepository.findAll();
    }
}
