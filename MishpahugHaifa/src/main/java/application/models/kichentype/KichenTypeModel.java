package application.models.kichentype;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entities.KitchenTypeEntity;
import application.repositories.KichenTypeRepository;

@Service
@Transactional
public class KichenTypeModel implements IKichenTypeModel {

    @Autowired
    KichenTypeRepository kichenTypeRepository;

    @Override
    public KitchenTypeEntity getById(Integer id) {
        return kichenTypeRepository.getOne(id);
    }

    @Override
    public KitchenTypeEntity updateName(Integer id, String name) {
        KitchenTypeEntity countryEntity = kichenTypeRepository.getOne(id);
        countryEntity.setName(name);
        return kichenTypeRepository.save(countryEntity);
    }

    @Override
    public void deleteByName(String name) {
        kichenTypeRepository.deleteByName(name);
    }

    @Override
    public void deleteAll() {
        kichenTypeRepository.deleteAll();
    }

    @Override
    public List<KitchenTypeEntity> getAll() {
        return kichenTypeRepository.findAll();
    }

    @Override
    public KitchenTypeEntity add(KitchenTypeEntity data) {
        return kichenTypeRepository.save(data);
    }

    @Override
    public KitchenTypeEntity update(Integer id, String name) {
        KitchenTypeEntity temp = kichenTypeRepository.getOne(id);
        temp.setName(name);
        return kichenTypeRepository.save(temp);
    }

    @Override
    public KitchenTypeEntity getByName(String kichenType) {
        return kichenTypeRepository.getByName(kichenType);
    }
}
