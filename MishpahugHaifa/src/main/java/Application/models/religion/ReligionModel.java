package Application.models.religion;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Application.entities.ReligionEntity;
import Application.repo.ReligionRepository;
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
    public ReligionEntity update(String oldName, String newName) {
        ReligionEntity religionEntity = religionRepository.getByFullName(oldName);
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
    public ReligionEntity getByFullName(String name) {
        return religionRepository.getByFullName(name);
    }

    @Override
    public void remove(Integer id) {
        religionRepository.deleteById(id);
    }

    @Override
    public List<ReligionEntity> getByName(String name) {
        return religionRepository.getByName(name);
    }
}
