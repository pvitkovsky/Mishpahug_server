package Application.models.kichentype;

import Application.entities.KichenTypeEntity;
import Application.repo.KichenTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class KichenType implements IKichenTypeModel {

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
