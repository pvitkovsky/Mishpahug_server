package application.models.user;

import application.entities.UserEntity;
import application.exceptions.ExceptionMishpaha;

import java.util.List;
import java.util.HashMap;

public interface IUserModel {
    public List<UserEntity> getAll();
    public UserEntity getById(Integer userId);
    public List<UserEntity> getByFilter(HashMap<String, String> filter);
    public UserEntity add(UserEntity data);
    public UserEntity update(Integer userId, HashMap<String, String> data) throws ExceptionMishpaha;
    public UserEntity deleteByID(Integer userId);
    public UserEntity getByName(String name);
    public void deleteAll();
    public List<UserEntity> getByGender(String gender);
    public List<UserEntity> getByReligion(String religion);
	public List<UserEntity> getByKitchenType(String kitchenType);
	public List<UserEntity> getByMaritalStatus(String maritalStatus);
}
