package Application.models.user;

import Application.entities.UserItem;

import java.util.List;
import java.util.HashMap;

public interface IUserModel {
    public List<UserItem> getAll();
    public List<UserItem> getByFilter(HashMap<String, String> filter);
    public UserItem add(UserItem data);
    public UserItem updateUser(Integer userId, HashMap<String, String> data);
    public UserItem remomeUser(Integer userId);
}
