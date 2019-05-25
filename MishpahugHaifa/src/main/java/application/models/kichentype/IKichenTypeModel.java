package application.models.kichentype;

import java.util.List;

import application.entities.KitchenTypeEntity;

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
