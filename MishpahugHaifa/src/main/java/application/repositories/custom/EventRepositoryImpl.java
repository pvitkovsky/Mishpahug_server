package application.repositories.custom;

import java.time.LocalDate;
import java.time.LocalTime;
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
	public EventEntity update(EventEntity event, HashMap<String, String> data) {
		StringBuilder query = new StringBuilder();
		Map<String, Integer> parameters = new HashMap<>();
		query.append("select e from EventEntity e where Id = :eventId ");
		parameters.put("eventId", event.getId());
		Query jpaQuery = entityManager.createQuery(query.toString());
		for (Map.Entry<String, Integer> map : parameters.entrySet()) {
			jpaQuery.setParameter(map.getKey(), map.getValue());
		}
		EventEntity tempResult = (EventEntity) jpaQuery.getResultList().get(0);
		if (data.containsKey("date")) {
			tempResult.setDate(LocalDate.parse(data.get("date")));
		}
		if (data.containsKey("time")) {
			tempResult.setTime(LocalTime.parse(data.get("time")));
		}
		if (data.containsKey("nameofevent")) {
			tempResult.setNameOfEvent(data.get("nameofevent"));
		}
		if (data.containsKey("activation")) {
			switch (data.get("activation")) {
			case "activate": {
				tempResult.activate();
				break;
			}
			case "cancel": {
				tempResult.cancel();
				break;
			}
			case "deactivate": {
				tempResult.deactivate();
				break;
			}
			}
		}
		return tempResult;
	}
}