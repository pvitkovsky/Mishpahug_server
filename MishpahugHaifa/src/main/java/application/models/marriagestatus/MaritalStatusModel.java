package application.models.marriagestatus;

import java.util.List;

import javax.transaction.Transactional;

import application.exceptions.EntityExistsException;
import application.exceptions.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entities.MaritalStatusEntity;
import application.repositories.MaritalStatusRepository;

@Service
@Transactional
public class MaritalStatusModel implements IMaritalStatusModel {

    @Autowired
    MaritalStatusRepository maritalStatusRepository;

    @Override
    public MaritalStatusEntity getByName(String name) {
        if (!maritalStatusRepository.existsByName(name)) throw new NotFoundEntityException("");
        return maritalStatusRepository.getByName(name);
    }

    @Override
    public List<MaritalStatusEntity> getAll() {
        return maritalStatusRepository.findAll();

    }

    @Override
    public MaritalStatusEntity getById(Integer id) {
        if (!maritalStatusRepository.existsById(id)) throw new NotFoundEntityException("");
        return maritalStatusRepository.getOne(id);
    }

    @Override
    public void deleteByName(String name){
        if (!maritalStatusRepository.existsByName(name)) throw new NotFoundEntityException("");
        maritalStatusRepository.deleteByName(name);
    }

    @Override
    public void deleteAll() {
        maritalStatusRepository.deleteAll();
    }

    @Override
    public MaritalStatusEntity updateName(Integer id, String name){
        if (maritalStatusRepository.existsByName(name)) throw new EntityExistsException("");
        if (!maritalStatusRepository.existsById(id)) throw new NotFoundEntityException("");
        MaritalStatusEntity maritalStatusEntity = maritalStatusRepository.getOne(id);
        maritalStatusEntity.setName(name);
        return maritalStatusRepository.saveAndFlush(maritalStatusEntity);
    }

    @Override
    public MaritalStatusEntity add(MaritalStatusEntity data){
        if (maritalStatusRepository.existsByName(data.getName())) throw new EntityExistsException("");
        return maritalStatusRepository.saveAndFlush(data);
    }

}
