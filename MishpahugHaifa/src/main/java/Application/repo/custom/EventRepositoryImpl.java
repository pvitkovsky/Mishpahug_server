package Application.repo.custom;

import Application.entities.EventEntity;
import org.omg.CORBA.Object;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventRepositoryImpl implements EventRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<EventEntity> searchByFilter(HashMap<String, String> filter) {
        StringBuilder query = new StringBuilder();
        Map<String, String> parameters = new HashMap<>();
        query.append("select e from EventEntity e where 1=1 ");

        //zavtra

        Query jpaQuery = entityManager.createQuery(query.toString());
        for (Map.Entry<String,String> map : parameters.entrySet()){
            jpaQuery.setParameter(map.getKey(), map.getValue());
        }

        return jpaQuery.getResultList();
    }
}
