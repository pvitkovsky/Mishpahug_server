package application.models.gender;

import java.util.List;

import application.entities.GenderEntity;
import application.exceptions.ExceptionMishpaha;

public interface IGenderModel {
    public GenderEntity getByName(String name);

    GenderEntity getById(Integer id);

    void deleteByName(String name) throws ExceptionMishpaha;

    void deleteAll();

    GenderEntity add(GenderEntity data) throws ExceptionMishpaha;

    GenderEntity updateName(Integer id, String name) throws ExceptionMishpaha;

    public List<GenderEntity> getAll();
    //TODO
}
