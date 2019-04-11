package application.repositories.custom;


import application.entities.UserEntity;

import java.util.Map;

public interface UserRepositoryCustom {
    public UserEntity update(UserEntity user, Map<String, String> data);
}
