package Application.models.eventrating;

import Application.entities.EventRatingItem;
import Application.repo.EventRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

public class EventRating implements IEventRatingModel {

    @Autowired
    EventRatingRepository eventRatingRepository;

    @Override
    public EventRatingItem getByEvent(Integer eventId) {
        return null;
    }

    @Override
    public EventRatingItem updateEventRating(Integer eventId, HashMap<String, String> data) {
        return null;
    }
}
