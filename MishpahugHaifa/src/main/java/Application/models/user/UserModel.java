package Application.models.user;

import Application.entities.UserEntity;
import Application.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.HashMap;

public class UserModel implements IUserModel {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getById(Integer userId) {
        return userRepository.getOne(userId);
    }

    @Override
    public List<UserEntity> getByFilter(HashMap<String, String> filter) {
        return userRepository.searchByFilter(filter);
    }

    @Override
    public UserEntity add(UserEntity data) {
        return userRepository.save(data);
    }

    @Override
    public UserEntity updateUser(Integer userId, HashMap<String, String> data) {
        return null;
    }

    @Override
    public UserEntity remomeUser(Integer userId) {
        UserEntity usr = userRepository.getOne(userId);
        userRepository.deleteById(userId);
        return usr;
    }

}
