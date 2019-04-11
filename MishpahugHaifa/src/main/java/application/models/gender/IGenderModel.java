package application.models.gender;

import application.entities.GenderEntity;
import application.exceptions.ExceptionMishpaha;

import java.util.List;

public interface IGenderModel {
    public GenderEntity getByName(String name);

    GenderEntity getById(Integer id);

    GenderEntity deleteByID(Integer id) throws ExceptionMishpaha;

    void deleteAll();

    GenderEntity add(GenderEntity data) throws ExceptionMishpaha;

    GenderEntity updateName(Integer id, String name) throws ExceptionMishpaha;

    public List<GenderEntity> getAll();
    //TODO
}
