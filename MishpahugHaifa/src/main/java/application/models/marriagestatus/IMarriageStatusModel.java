package application.models.marriagestatus;

import application.entities.MarriageStatusEntity;

import java.util.List;

public interface IMarriageStatusModel {
    public MarriageStatusEntity getByName(String name);
    //TODO
    public List<MarriageStatusEntity> getAll();
}
