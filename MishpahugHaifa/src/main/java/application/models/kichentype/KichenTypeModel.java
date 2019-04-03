package application.models.kichentype;

import application.entities.KitchenTypeEntity;
import application.repositories.KichenTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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
    public void deleteByID(Integer id) {
        kichenTypeRepository.deleteById(id);
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
