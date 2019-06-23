package application.models.properties.marriagestatus;

import application.entities.properties.MaritalStatusEntity;
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
    public void delete(String name){
        maritalStatusRepository.deleteByName(name);
    }

    @Override
    public void delete(Integer id){
        maritalStatusRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        maritalStatusRepository.deleteAll();
    }

    @Override
    public MaritalStatusEntity updateName(Integer id, String name){
        MaritalStatusEntity maritalStatusEntity = maritalStatusRepository.getOne(id);
        maritalStatusEntity.setName(name);
        return maritalStatusRepository.saveAndFlush(maritalStatusEntity);
    }

    @Override
    public MaritalStatusEntity add(MaritalStatusEntity data){
        return maritalStatusRepository.saveAndFlush(data);
    }

}
