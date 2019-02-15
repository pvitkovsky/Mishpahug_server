package Application.models.kichentype;

import Application.entities.KichenTypeEntity;
import Application.repo.KichenTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
        return null;
    }

    @Override
    public KichenTypeEntity update(String name) {
        return null;
    }
}
