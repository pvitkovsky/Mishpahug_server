package Application.models.eventrating;

import Application.entities.EventRatingItem;

import java.util.HashMap;

public interface IEventRatingModel {
    public EventRatingItem getByEvent(Integer eventId);
    public EventRatingItem updateEventRating(Integer eventId, HashMap<String, String> data);
}
