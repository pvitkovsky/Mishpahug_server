package application.models.user;

import application.entities.UserEntity;
import application.exceptions.ExceptionMishpaha;
import com.querydsl.core.types.Predicate;

import java.util.List;
import java.util.HashMap;

public interface IUserModel {
    public List<UserEntity> getAll() throws ExceptionMishpaha;

    public Iterable<UserEntity> getAll(Predicate predicate);

    public UserEntity getById(Integer userId)  throws ExceptionMishpaha;
    public List<UserEntity> getByFilter(HashMap<String, String> filter) throws ExceptionMishpaha;
    public UserEntity add(UserEntity data) throws ExceptionMishpaha;
    public UserEntity update(Integer userId, HashMap<String, String> data) throws ExceptionMishpaha;
    public UserEntity deleteByID(Integer userId) throws ExceptionMishpaha;
    public UserEntity getByName(String name) throws ExceptionMishpaha;
    public List<UserEntity> deleteAll() throws ExceptionMishpaha;
    public List<UserEntity> getByGender(String gender) throws ExceptionMishpaha;
    public List<UserEntity> getByReligion(String religion) throws ExceptionMishpaha;
	public List<UserEntity> getByKitchenType(String kitchenType) throws ExceptionMishpaha;
	public List<UserEntity> getByMaritalStatus(String maritalStatus) throws ExceptionMishpaha;
}
