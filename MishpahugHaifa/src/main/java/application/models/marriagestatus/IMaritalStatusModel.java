package application.models.marriagestatus;

import application.entities.MaritalStatusEntity;

import java.util.List;

public interface IMaritalStatusModel {
    public MaritalStatusEntity getByName(String name);
    //TODO
    public List<MaritalStatusEntity> getAll();
}
