package Application.entities;

import javax.persistence.*;

@Entity
@Table(name="Address")
public class AddressItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer country;
    private Integer city;
    private Integer build;
    private Integer apartment;
    private Integer userId = -1;
    private Integer eventId = -1;

    public AddressItem(Integer id, Integer country, Integer city, Integer build, Integer apartment, Integer userId, Integer eventId) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.build = build;
        this.apartment = apartment;
        this.userId = userId;
        this.eventId = eventId;
    }

    @Override
    public String toString() {
        return "AddressItem{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", build=" + build +
                ", apartment=" + apartment +
                ", userId=" + userId +
                ", eventId=" + eventId +
                '}';
    }

    public AddressItem() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getBuild() {
        return build;
    }

    public void setBuild(Integer build) {
        this.build = build;
    }

    public Integer getApartment() {
        return apartment;
    }

    public void setApartment(Integer apartment) {
        this.apartment = apartment;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
}
