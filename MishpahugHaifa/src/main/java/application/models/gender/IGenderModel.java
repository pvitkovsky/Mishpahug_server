package application.models.gender;

import java.util.List;

import application.entities.GenderEntity;
import application.exceptions.EntityExistsDException;

public interface IGenderModel {
    public GenderEntity getByName(String name);

    GenderEntity getById(Integer id);

    void deleteByName(String name) throws EntityExistsDException;

    void deleteAll();

    GenderEntity add(GenderEntity data) throws EntityExistsDException;

    GenderEntity updateName(Integer id, String name) throws EntityExistsDException;

    public List<GenderEntity> getAll();
    //TODO
}
