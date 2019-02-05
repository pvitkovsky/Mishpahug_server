package Application.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="freeback")
public class FreeBackItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer eventId;
    private String comment;
    private LocalDateTime dateTime;
    private Integer rating;
    private Integer userId;

    public FreeBackItem() {
    }

    public FreeBackItem(Integer id, Integer eventId, String comment, LocalDateTime dateTime, Integer rating, Integer userId) {
        this.id = id;
        this.eventId = eventId;
        this.comment = comment;
        this.dateTime = dateTime;
        this.rating = rating;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "FreeBackItem{" +
                "id=" + id +
                ", eventId=" + eventId +
                ", comment='" + comment + '\'' +
                ", dateTime=" + dateTime +
                ", rating=" + rating +
                ", userId=" + userId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
