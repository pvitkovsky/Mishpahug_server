package Application.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eventrating")
public class EventRatingItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eventId;
	@ElementCollection
	private List<Integer> rating;

	public EventRatingItem() {
		super();
	}

	public EventRatingItem(Integer eventId) {
		super();
		this.eventId = eventId;
		this.rating = new ArrayList<Integer>();
	}

	public EventRatingItem(Integer eventId, List<Integer> rating) {
		super();
		this.eventId = eventId;
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "EventRatingItem [eventId=" + eventId + ", rating=" + rating + "]";
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public List<Integer> getRating() {
		return Collections.synchronizedList(rating);
	}

	public void setRating(List<Integer> rating) {
		this.rating = rating;
	}

}
