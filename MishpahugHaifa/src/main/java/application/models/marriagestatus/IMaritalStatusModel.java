package application.models.marriagestatus;

import application.entities.MaritalStatusEntity;
import application.exceptions.ExceptionMishpaha;

import java.util.List;

public interface IMaritalStatusModel {
    public MaritalStatusEntity getByName(String name);
    //TODO
    public List<MaritalStatusEntity> getAll();

    MaritalStatusEntity getById(Integer id);

    MaritalStatusEntity deleteByID(Integer id)  throws ExceptionMishpaha;

    void deleteAll();

    MaritalStatusEntity updateName(Integer id, String name) throws ExceptionMishpaha;

    MaritalStatusEntity add(MaritalStatusEntity data)  throws ExceptionMishpaha;
}
