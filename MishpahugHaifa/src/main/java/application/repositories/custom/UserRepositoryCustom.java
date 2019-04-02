package application.repositories.custom;


import application.entities.UserEntity;

import java.util.HashMap;
import java.util.List;

public interface UserRepositoryCustom {
    public UserEntity update(UserEntity user, HashMap<String, String> data);
}
