package application.models.user;

import application.entities.UserEntity;
import application.exceptions.ExceptionMishpaha;
import com.querydsl.core.types.Predicate;

import java.util.HashMap;
import java.util.List;

public interface IUserModel {
    UserEntity getByUsernameAndPassword(String username, String password);

    public List<UserEntity> getAll() throws ExceptionMishpaha;

    public Iterable<UserEntity> getAll(Predicate predicate);

    public UserEntity getById(Integer userId) throws ExceptionMishpaha;

    public UserEntity getByName(String name) throws ExceptionMishpaha;

    public UserEntity add(UserEntity data) throws ExceptionMishpaha;

    public UserEntity update(Integer userId, HashMap<String, String> data) throws ExceptionMishpaha;

    public UserEntity deleteByID(Integer userId) throws ExceptionMishpaha;

    public List<UserEntity> deleteAll() throws ExceptionMishpaha;

    public UserEntity activateByID(Integer userId) throws ExceptionMishpaha;

    public UserEntity deactivateByID(Integer userId) throws ExceptionMishpaha;

    public UserEntity prepareForDeletionByID(Integer userId) throws ExceptionMishpaha;

}
