package application.repositories.custom;


import java.util.Map;

import application.entities.UserEntity;

public interface UserRepositoryCustom {
    public UserEntity update(UserEntity user, Map<String, String> data);
}
