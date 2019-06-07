package application.models.gender;

import java.util.List;

import application.entities.GenderEntity;
import application.exceptions.NotFoundGenderWithIDException;

public interface IGenderModel {
    public GenderEntity getByName(String name);

    GenderEntity getById(Integer id);

    void deleteByName(String name) throws NotFoundGenderWithIDException;

    void deleteAll();

    GenderEntity add(GenderEntity data) throws NotFoundGenderWithIDException;

    GenderEntity updateName(Integer id, String name) throws NotFoundGenderWithIDException;

    public List<GenderEntity> getAll();
    //TODO
}
