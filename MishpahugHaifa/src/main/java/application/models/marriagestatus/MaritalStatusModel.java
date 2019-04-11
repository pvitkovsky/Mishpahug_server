package application.models.marriagestatus;

import application.entities.MaritalStatusEntity;
import application.exceptions.ExceptionMishpaha;
import application.repositories.MaritalStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MaritalStatusModel implements IMaritalStatusModel {

    @Autowired
    MaritalStatusRepository maritalStatusRepository;

    @Override
    public MaritalStatusEntity getByName(String name) {
        return maritalStatusRepository.getByName(name);
    }

    @Override
    public List<MaritalStatusEntity> getAll() {
        return maritalStatusRepository.findAll();

    }

    @Override
    public MaritalStatusEntity getById(Integer id) {
        return maritalStatusRepository.getOne(id);
    }

    @Override
    public MaritalStatusEntity deleteByID(Integer id) throws ExceptionMishpaha {
        try {
            MaritalStatusEntity maritalStatusEntity = maritalStatusRepository.getOne(id);
            maritalStatusRepository.deleteById(id);
            return maritalStatusEntity;
        } catch (Exception e) {
            throw new ExceptionMishpaha(this.getClass().toString(), e);
        }
    }

    @Override
    public void deleteAll() {
        maritalStatusRepository.deleteAll();
    }

    @Override
    public MaritalStatusEntity updateName(Integer id, String name) throws ExceptionMishpaha {
        try {
            MaritalStatusEntity maritalStatusEntity = maritalStatusRepository.getOne(id);
            maritalStatusEntity.setName(name);
            return maritalStatusRepository.saveAndFlush(maritalStatusEntity);
        } catch (Exception e) {
            throw new ExceptionMishpaha(this.getClass().toString(), e);
        }
    }

    @Override
    public MaritalStatusEntity add(MaritalStatusEntity data) throws ExceptionMishpaha {
        try {
            return maritalStatusRepository.saveAndFlush(data);
        } catch (Exception e) {
            throw new ExceptionMishpaha(this.getClass().toString(), e);
        }
    }

}
