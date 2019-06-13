package application.models.properties.religion;

import application.entities.properties.ReligionEntity;

import java.util.List;

public interface IReligionModel {
    public ReligionEntity getById(Integer id);

    public List<ReligionEntity> getAll();

    public ReligionEntity add(ReligionEntity data);

    public ReligionEntity updateName(Integer id, String name);

    public ReligionEntity deleteByID(Integer id);

    public void deleteAll();

    public ReligionEntity update(String oldName, String newName);

    public ReligionEntity getByName(String name);

    public void deleteByName(String name);

    public ReligionEntity update(Integer religionId, String newName);
}
