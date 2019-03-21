package application.models.kichentype;

import application.entities.KichenTypeEntity;

import java.util.List;

public interface IKichenTypeModel {
    public KichenTypeEntity getById(Integer id);
    public List<KichenTypeEntity> getAll();
    public KichenTypeEntity add(KichenTypeEntity data);
    public KichenTypeEntity update(Integer id, String name);

}
