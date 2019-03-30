package application.models.kichentype;

import application.entities.KitchenTypeEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

public interface IKichenTypeModel {
    public KitchenTypeEntity getById(Integer id);

    KitchenTypeEntity updateName(Integer id, String name);

    void deleteByID(Integer id);

    void deleteAll();

    public List<KitchenTypeEntity> getAll();
    public KitchenTypeEntity add(KitchenTypeEntity data);
    public KitchenTypeEntity update(Integer id, String name);
    public KitchenTypeEntity getByName(String kichenType);
}
