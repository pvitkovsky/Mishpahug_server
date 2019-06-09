package application.models.kichentype;

import java.util.List;

import javax.transaction.Transactional;

import application.exceptions.EntityExistsException;
import application.exceptions.NotFoundEntityException;
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
        if (!kichenTypeRepository.existsById(id)) throw new NotFoundEntityException("");
        return kichenTypeRepository.getOne(id);
    }

    @Override
    public KitchenTypeEntity updateName(Integer id, String name) {
        if (!kichenTypeRepository.existsById(id)) throw new NotFoundEntityException("");
        if (kichenTypeRepository.existsByName(name)) throw new EntityExistsException("");
        KitchenTypeEntity countryEntity = kichenTypeRepository.getOne(id);
        countryEntity.setName(name);
        return kichenTypeRepository.save(countryEntity);
    }

    @Override
    public void deleteByName(String name) {
        if (!kichenTypeRepository.existsByName(name)) throw new NotFoundEntityException("");
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
        if (kichenTypeRepository.existsByName(data.getName())) throw new EntityExistsException("");
        return kichenTypeRepository.save(data);
    }

    @Override
    public KitchenTypeEntity update(Integer id, String name) {
        if (!kichenTypeRepository.existsById(id)) throw new NotFoundEntityException("");
        if (kichenTypeRepository.existsByName(name)) throw new EntityExistsException("");
        KitchenTypeEntity temp = kichenTypeRepository.getOne(id);
        temp.setName(name);
        return kichenTypeRepository.save(temp);
    }

    @Override
    public KitchenTypeEntity getByName(String kichenType) {
        if (!kichenTypeRepository.existsByName(kichenType)) throw new NotFoundEntityException("");
        return kichenTypeRepository.getByName(kichenType);
    }
}
