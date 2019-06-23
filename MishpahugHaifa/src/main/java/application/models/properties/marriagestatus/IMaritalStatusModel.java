package application.models.properties.marriagestatus;

import application.entities.properties.MaritalStatusEntity;

import java.util.List;

public interface IMaritalStatusModel {
    public MaritalStatusEntity getByName(String name);

    //TODO
    public List<MaritalStatusEntity> getAll();

    MaritalStatusEntity getById(Integer id);

    void delete(String name);

    void delete(Integer id);

    void deleteAll();

    MaritalStatusEntity updateName(Integer id, String name);

    MaritalStatusEntity add(MaritalStatusEntity data);
}
