package application.repo.custom;


import application.entities.EventGuestRelation;
import application.entities.UserEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface UserRepositoryCustom {
    public List<UserEntity> searchByFilter(HashMap<String, String> filter);
    public UserEntity update(Integer userId, HashMap<String, String> data);
}
