package Application.models.user;

import Application.entities.UserEntity;

import java.util.List;
import java.util.HashMap;

public interface IUserModel {
    public List<UserEntity> getAll();
    public UserEntity getById(Integer userId);
    public List<UserEntity> getByFilter(HashMap<String, String> filter);
    public UserEntity add(UserEntity data);
    public UserEntity updateUser(Integer userId, HashMap<String, String> data);
    public UserEntity remomeUser(Integer userId);
}
