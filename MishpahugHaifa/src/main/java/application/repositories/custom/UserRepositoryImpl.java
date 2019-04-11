package application.repositories.custom;

import application.entities.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;

/*
поля для фильтрации
-никнайм
-имя
-фамилия
-почта
-номер телефона
использовать like '%:parameter%'
 */
public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserEntity update(UserEntity user, Map<String, String> data) {
        StringBuilder query = new StringBuilder();
        Map<String, Integer> parameters = new HashMap<>();
        query.append("select e from UserEntity e where user_Id = :user_Id ");
        parameters.put("user_Id", user.getId());
        Query jpaQuery = entityManager.createQuery(query.toString());
        for (Map.Entry<String, Integer> map : parameters.entrySet()) {
            jpaQuery.setParameter(map.getKey(), map.getValue());
        }
        UserEntity tempResult = (UserEntity) jpaQuery.getResultList().get(0);
        if (data.containsKey("username")) {
            tempResult.setUserName(data.get("username"));
        }
        if (data.containsKey("firstname")) {
            tempResult.setFirstName(data.get("firstname"));
        }
        if (data.containsKey("lastname")) {
            tempResult.setLastName(data.get("lastname"));
        }
        if (data.containsKey("eMail")) {
            tempResult.setLastName(data.get("eMail"));
        }
        if (data.containsKey("status")) {
            tempResult.changeStatus(data.get("status"));
        }
        return tempResult;
    }
}
