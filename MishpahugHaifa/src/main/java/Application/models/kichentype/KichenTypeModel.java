package application.models.kichentype;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entities.KichenTypeEntity;
import application.repo.KichenTypeRepository;
@Service
@Transactional
public class KichenTypeModel implements IKichenTypeModel {

    @Autowired
    KichenTypeRepository kichenTypeRepository;

    @Override
    public KichenTypeEntity getById(Integer id) {
        return kichenTypeRepository.getOne(id);
    }

    @Override
    public List<KichenTypeEntity> getAll() {
        return kichenTypeRepository.findAll();
    }

    @Override
    public KichenTypeEntity add(KichenTypeEntity data) {
        return kichenTypeRepository.save(data);
    }

    @Override
    public KichenTypeEntity update(Integer id, String name) {
        KichenTypeEntity temp = kichenTypeRepository.getOne(id);
        temp.setName(name);
        return kichenTypeRepository.save(temp);
    }
}
