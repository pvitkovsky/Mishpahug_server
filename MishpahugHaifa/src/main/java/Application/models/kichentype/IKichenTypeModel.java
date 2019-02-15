package Application.models.kichentype;

import Application.entities.KichenTypeEntity;

import java.util.List;

public interface IKichenTypeModel {
    public KichenTypeEntity getById(Integer id);
    public List<KichenTypeEntity> getAll();
    public KichenTypeEntity add(KichenTypeEntity data);
    public KichenTypeEntity update(String name);

}
