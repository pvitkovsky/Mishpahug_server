package Application.models.user;

import Application.entities.UserItem;
import Application.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.HashMap;

public class UserModel implements IUserModel {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserItem> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<UserItem> getByFilter(HashMap<String, String> filter) {
        return null;
    }

    @Override
    public UserItem add(UserItem data) {
        return null;
    }

    @Override
    public UserItem updateUser(Integer userId, HashMap<String, String> data) {
        return null;
    }

    @Override
    public UserItem remomeUser(Integer userId) {
        return null;
    }

}
