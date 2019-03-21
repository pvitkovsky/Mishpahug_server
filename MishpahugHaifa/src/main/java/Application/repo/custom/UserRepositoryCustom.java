package Application.repo.custom;

import Application.entities.EventGuestRelation;
import Application.entities.UserEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface UserRepositoryCustom {
    public List<UserEntity> searchByFilter(HashMap<String, String> filter);
}
