package application.repo.custom;

import application.entities.UserEntity;

import java.util.HashMap;
import java.util.List;

public interface UserRepositoryCustom {
    public List<UserEntity> searchByFilter(HashMap<String, String> filter);
}
