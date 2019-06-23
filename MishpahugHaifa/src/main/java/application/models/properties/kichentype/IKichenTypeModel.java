package application.models.properties.kichentype;

import application.entities.properties.KitchenTypeEntity;

import java.util.List;

public interface IKichenTypeModel {
    abstract KitchenTypeEntity getById(Integer id);

    KitchenTypeEntity updateName(Integer id, String name);

    void delete(String name);

    void deleteAll();

    public List<KitchenTypeEntity> getAll();

    public KitchenTypeEntity add(KitchenTypeEntity data);

    public KitchenTypeEntity update(Integer id, String name);

    public KitchenTypeEntity getByName(String kichenType);

    void delete(Integer id);
}
