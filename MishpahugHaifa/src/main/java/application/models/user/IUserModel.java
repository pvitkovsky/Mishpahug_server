package application.models.user;

import java.util.List;

import com.querydsl.core.types.Predicate;

import application.dto.UserDTO;
import application.entities.UserEntity;
import application.exceptions.ExceptionMishpaha;

public interface IUserModel {
    UserEntity getByUsernameAndPassword(String username, String password);

    public List<UserEntity> getAll() throws ExceptionMishpaha;

    public Iterable<UserEntity> getAll(Predicate predicate);

    public UserEntity getById(Integer userId) throws ExceptionMishpaha;

    public UserEntity getByUserName(String name) throws ExceptionMishpaha;

    public UserEntity add(UserEntity data) throws ExceptionMishpaha;

    public UserEntity update(UserDTO data) throws ExceptionMishpaha;

    public UserEntity deleteByID(Integer userId) throws ExceptionMishpaha;

    public List<UserEntity> deleteAll() throws ExceptionMishpaha;

    public UserEntity activateByID(Integer userId) throws ExceptionMishpaha;

    public UserEntity deactivateByID(Integer userId) throws ExceptionMishpaha;

    public UserEntity prepareForDeletionByID(Integer userId) throws ExceptionMishpaha;


}
