package Application.models.kichentype;

import Application.entities.KichenTypeItem;
import Application.repo.KichenTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class KichenType implements IKichenTypeModel {

    @Autowired
    KichenTypeRepository kichenTypeRepository;

    @Override
    public KichenTypeItem getById(Integer id) {
        return kichenTypeRepository.getOne(id);
    }

    @Override
    public List<KichenTypeItem> getAll() {
        return kichenTypeRepository.findAll();
    }

    @Override
    public KichenTypeItem add(KichenTypeItem data) {
        return null;
    }

    @Override
    public KichenTypeItem update(String name) {
        return null;
    }
}
