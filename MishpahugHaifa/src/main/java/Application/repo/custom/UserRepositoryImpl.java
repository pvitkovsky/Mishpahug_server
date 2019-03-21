package application.repo.custom;

import application.entities.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
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
    public UserEntity update(Integer userId, HashMap<String, String> data){
        StringBuilder query = new StringBuilder();
        Map<String, Integer> parameters = new HashMap<>();
        query.append("select e from UserEntity e where user_Id = :user_Id ");
        parameters.put("user_Id", userId);
        Query jpaQuery = entityManager.createQuery(query.toString());
        for (Map.Entry<String,Integer> map : parameters.entrySet()){
            jpaQuery.setParameter(map.getKey(), map.getValue());
        }
        UserEntity tempResult = (UserEntity) jpaQuery.getResultList().get(0);
        if (data.containsKey("username")){
            tempResult.setUserName(data.get("username"));
        }
        if (data.containsKey("firstname")){
            tempResult.setFirstName(data.get("firstname"));
        }
        if (data.containsKey("lastname")){
            tempResult.setLastName(data.get("lastname"));
        }
        if (data.containsKey("eMail")){
            tempResult.setLastName(data.get("eMail"));
        }
        return tempResult;
    }
    @Override
    public List<UserEntity> searchByFilter(HashMap<String, String> filter) {
        StringBuilder query = new StringBuilder();
        Map<String, String> parameters = new HashMap<>();
        query.append("select e from UserEntity e where 1=1 ");
        if (filter.containsKey("username")){
            query.append(" and e.userName LIKE :username");
            parameters.put("username", "%" + filter.get("username") + "%");
        }
        if (filter.containsKey("firstname")){
            query.append(" and e.firstname LIKE :firstname");
            parameters.put("firstname", "%" + filter.get("firstname") + "%");
        }
        if (filter.containsKey("lastname")){
            query.append(" and e.lastname LIKE :lastname");
            parameters.put("lastname", "%" + filter.get("lastname") + "%");
        }
        if (filter.containsKey("eMail")){
            query.append(" and e.eMail LIKE :eMail");
            parameters.put("eMail", "%" + filter.get("eMail") + "%");
        }
        Query jpaQuery = entityManager.createQuery(query.toString());
        for (Map.Entry<String,String> map : parameters.entrySet()){
            jpaQuery.setParameter(map.getKey(), map.getValue());
        }

        return jpaQuery.getResultList();
    }
}
