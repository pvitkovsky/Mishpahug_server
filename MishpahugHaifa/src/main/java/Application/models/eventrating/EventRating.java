package Application.models.eventrating;

import java.util.HashMap;

import Application.entities.EventRatingValue;

public class EventRating implements IEventRatingModel {
    
	@Override
    public EventRatingValue getByEvent(Integer eventId) {
        return null;
    }

    @Override
    public EventRatingValue updateEventRating(Integer eventId, HashMap<String, String> data) {
        return null;
    }
}
