package Application.models.religion;

import Application.entities.ReligionEntity;
import Application.repo.ReligionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
        return null;
    }

    @Override
    public ReligionEntity update(String name) {
        return null;
    }

    @Override
    public ReligionEntity getByFullName(String name) {
        return religionRepository.getByFullName(name);
    }

    @Override
    public ReligionEntity remove(Integer id) {
        return null;
    }

    @Override
    public List<ReligionEntity> getByName(String name) {
        return religionRepository.getByName(name);
    }
}
