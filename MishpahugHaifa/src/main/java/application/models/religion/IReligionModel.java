package application.models.religion;

import application.entities.ReligionEntity;
import application.exceptions.ExceptionMishpaha;

import java.util.List;

public interface IReligionModel {
    public ReligionEntity getById(Integer id);

    public List<ReligionEntity> getAll();

    public ReligionEntity add(ReligionEntity data);

    public ReligionEntity updateName(Integer id, String name) throws ExceptionMishpaha;

    public ReligionEntity deleteByID(Integer id) throws ExceptionMishpaha;

    public void deleteAll();

    public ReligionEntity update(String oldName, String newName);

    public ReligionEntity getByName(String name);

    public void remove(Integer id);

    public ReligionEntity update(Integer religionId, String newName);
}
