package Application.models.eventrating;

import java.util.HashMap;

import Application.entities.values.EventRatingValue;

public interface IEventRatingModel {
    public EventRatingValue getByEvent(Integer eventId);
    public EventRatingValue updateEventRating(Integer eventId, HashMap<String, String> data);
}
