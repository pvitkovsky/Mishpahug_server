package application.repo.custom;

import application.entities.EventEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventRepositoryImpl implements EventRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EventEntity update(Integer eventId, HashMap<String, String> data){
        StringBuilder query = new StringBuilder();
        Map<String, Integer> parameters = new HashMap<>();
        query.append("select e from EventEntity e where Id = :eventId ");
        parameters.put("eventId", eventId);
        Query jpaQuery = entityManager.createQuery(query.toString());
        for (Map.Entry<String,Integer> map : parameters.entrySet()){
            jpaQuery.setParameter(map.getKey(), map.getValue());
        }
        EventEntity tempResult = (EventEntity) jpaQuery.getResultList().get(0);
        if (data.containsKey("date")){
            tempResult.setDate(LocalDate.parse(data.get("date")));
        }
        if (data.containsKey("time")){
            tempResult.setTime(LocalTime.parse(data.get("time")));
        }
        if (data.containsKey("nameofevent")){
            tempResult.setNameOfEvent(data.get("nameofevent"));
        }
        return tempResult;
    }

    @Override
    public List<EventEntity> searchByFilter(HashMap<String, String> filter) {
        StringBuilder query = new StringBuilder();
        Map<String, String> parameters = new HashMap<>();
        query.append("select e from EventEntity e where 1=1 ");

        if (filter.containsKey("date")){
            query.append(" and e.date = :date");
            parameters.put("date", filter.get("date"));
        }
        if (filter.containsKey("nameOfEvent")){
            query.append(" and e.nameOfEvent LIKE :nameOfEvent");
            parameters.put("nameOfEvent",  "%" + filter.get("nameOfEvent") + "%");
        }
        if (filter.containsKey("status")){
            query.append(" and e.status = :status");
            parameters.put("status", filter.get("status"));
        }

        Query jpaQuery = entityManager.createQuery(query.toString());
        for (Map.Entry<String,String> map : parameters.entrySet()){
            jpaQuery.setParameter(map.getKey(), map.getValue());
        }

        return jpaQuery.getResultList();
    }
}
