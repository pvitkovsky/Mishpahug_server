package Application.models.eventrating;

import Application.entities.EventRatingValue;

import java.util.HashMap;

public interface IEventRatingModel {
    public EventRatingValue getByEvent(Integer eventId);
    public EventRatingValue updateEventRating(Integer eventId, HashMap<String, String> data);
}
