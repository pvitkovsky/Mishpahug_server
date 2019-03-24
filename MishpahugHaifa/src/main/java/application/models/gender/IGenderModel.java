package application.models.gender;

import application.entities.GenderEntity;

import java.util.List;

public interface IGenderModel {
    public GenderEntity getByName(String name);
    public List<GenderEntity> getAll();
    //TODO
}
