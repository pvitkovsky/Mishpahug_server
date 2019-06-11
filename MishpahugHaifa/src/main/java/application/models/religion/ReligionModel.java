package application.models.religion;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entities.ReligionEntity;
import application.repositories.ReligionRepository;

@Service
@Transactional
public class ReligionModel implements IReligionModel {

    @Autowired
    ReligionRepository religionRepository;

    @Override
    public ReligionEntity getById(Integer id) {
        return religionRepository.findById(id).orElse(null);
    }

    @Override
    public List<ReligionEntity> getAll() {
        return religionRepository.findAll();
    }

    @Override
    public ReligionEntity add(ReligionEntity data) {
        return religionRepository.save(data);
    }

    @Override
    public ReligionEntity updateName(Integer id, String name){
        ReligionEntity cityEntity = religionRepository.getOne(id);
        cityEntity.setName(name);
        return religionRepository.saveAndFlush(cityEntity);
    }

    @Override
    public ReligionEntity deleteByID(Integer id){
        ReligionEntity cityEntity = religionRepository.getOne(id);
        religionRepository.deleteById(id);
        return cityEntity;
    }

    @Override
    public void deleteAll() {
        religionRepository.deleteAll();
    }


    @Override
    public ReligionEntity update(String oldName, String newName) {
        ReligionEntity religionEntity = religionRepository.getByName(oldName);
        religionEntity.setName(newName);
        return religionRepository.save(religionEntity);
    }

    @Override
    public ReligionEntity update(Integer religionId, String newName) {
        ReligionEntity religionEntity = religionRepository.getOne(religionId);
        religionEntity.setName(newName);
        return religionRepository.save(religionEntity);
    }

    @Override
    public void deleteByName(String name) {
        religionRepository.deleteByName(name);
    }

    @Override
    public ReligionEntity getByName(String name) {
        return religionRepository.getByName(name);
    }
}
