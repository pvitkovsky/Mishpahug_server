package application.models.marriagestatus;

import java.util.List;

import application.entities.MaritalStatusEntity;
import application.exceptions.ExceptionMishpaha;

public interface IMaritalStatusModel {
    public MaritalStatusEntity getByName(String name);

    //TODO
    public List<MaritalStatusEntity> getAll();

    MaritalStatusEntity getById(Integer id);

    void deleteByName(String name) throws ExceptionMishpaha;

    void deleteAll();

    MaritalStatusEntity updateName(Integer id, String name) throws ExceptionMishpaha;

    MaritalStatusEntity add(MaritalStatusEntity data) throws ExceptionMishpaha;
}
