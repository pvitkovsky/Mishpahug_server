package application.models.properties.gender;

import application.entities.properties.GenderEntity;

import java.util.List;

public interface IGenderModel {
    public GenderEntity getByName(String name);

    GenderEntity getById(Integer id);

    void delete(String name);

    void delete(Integer id);

    void deleteAll();

    GenderEntity add(GenderEntity data);

    GenderEntity updateName(Integer id, String name);

    public List<GenderEntity> getAll();
    //TODO
}
