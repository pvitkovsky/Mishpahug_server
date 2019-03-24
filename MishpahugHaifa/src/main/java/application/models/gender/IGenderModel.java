package application.models.gender;

import application.entities.GenderEntity;

import java.awt.*;

public interface IGenderModel {
    GenderEntity getByName(String name);

    public List<GenderEntity> getAll();
    //TODO
}
