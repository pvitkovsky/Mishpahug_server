package application.repositories.custom;


import application.entities.UserEntity;

import java.util.HashMap;

public interface UserRepositoryCustom {
    public UserEntity update(UserEntity user, HashMap<String, String> data);
}
