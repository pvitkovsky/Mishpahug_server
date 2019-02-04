package Application.entities;

import javax.persistence.*;

@Entity
@Table(name="EventRating")
public class EventRatingItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventId;
    private Integer rating0;
    private Integer rating1;
    private Integer rating2;
    private Integer rating3;
    private Integer rating4;
    private Integer rating5;
    private Integer rating6;
    private Integer rating7;
    private Integer rating8;
    private Integer rating9;
    private Integer rating10;

    public EventRatingItem(Integer eventId) {
        this.eventId = eventId;
    }

    @Override
    public String toString() {
        return "EventRatingItem{" +
                "eventId=" + eventId +
                ", rating0=" + rating0 +
                ", rating1=" + rating1 +
                ", rating2=" + rating2 +
                ", rating3=" + rating3 +
                ", rating4=" + rating4 +
                ", rating5=" + rating5 +
                ", rating6=" + rating6 +
                ", rating7=" + rating7 +
                ", rating8=" + rating8 +
                ", rating9=" + rating9 +
                ", rating10=" + rating10 +
                '}';
    }

    public EventRatingItem() {
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getRating0() {
        return rating0;
    }

    public void setRating0(Integer rating0) {
        this.rating0 = rating0;
    }

    public Integer getRating1() {
        return rating1;
    }

    public void setRating1(Integer rating1) {
        this.rating1 = rating1;
    }

    public Integer getRating2() {
        return rating2;
    }

    public void setRating2(Integer rating2) {
        this.rating2 = rating2;
    }

    public Integer getRating3() {
        return rating3;
    }

    public void setRating3(Integer rating3) {
        this.rating3 = rating3;
    }

    public Integer getRating4() {
        return rating4;
    }

    public void setRating4(Integer rating4) {
        this.rating4 = rating4;
    }

    public Integer getRating5() {
        return rating5;
    }

    public void setRating5(Integer rating5) {
        this.rating5 = rating5;
    }

    public Integer getRating6() {
        return rating6;
    }

    public void setRating6(Integer rating6) {
        this.rating6 = rating6;
    }

    public Integer getRating7() {
        return rating7;
    }

    public void setRating7(Integer rating7) {
        this.rating7 = rating7;
    }

    public Integer getRating8() {
        return rating8;
    }

    public void setRating8(Integer rating8) {
        this.rating8 = rating8;
    }

    public Integer getRating9() {
        return rating9;
    }

    public void setRating9(Integer rating9) {
        this.rating9 = rating9;
    }

    public Integer getRating10() {
        return rating10;
    }

    public void setRating10(Integer rating10) {
        this.rating10 = rating10;
    }
}
