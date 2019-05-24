package application.repositories.custom;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import application.entities.EventEntity;

public class EventRepositoryImpl implements EventRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

	@Override 
	public EventEntity update(EventEntity event, Map<String, String> data) {
		//TODO: make this work / replace with another working solution; as of now this just takes a result from the database and changes some fields in the result;  
		StringBuilder query = new StringBuilder();
		Map<String, Integer> parameters = new HashMap<>();
		query.append("select e from EventEntity e where Id = :eventId ");
		parameters.put("eventId", event.getId());
		Query jpaQuery = entityManager.createQuery(query.toString());
		for (Map.Entry<String, Integer> map : parameters.entrySet()) {
			jpaQuery.setParameter(map.getKey(), map.getValue());
		}
		EventEntity tempResult = (EventEntity) jpaQuery.getResultList().get(0); //this is not an update method
		if (data.containsKey("nameofevent")) {
			tempResult.setNameOfEvent(data.get("nameofevent"));
		}
		if (data.containsKey("status")) {
			tempResult.changeStatus(data.get("status"));
		}
		
		return tempResult;
	}
}
