package Application.entities;

import javax.persistence.*;

@Entity
@Table(name="address")
public class AddressItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer country;
    private Integer city;
    private Integer build;
    private Integer apartment;
    private String street;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public AddressItem(Integer country, Integer city, Integer build, Integer apartment, String street) {
        this.country = country;
        this.city = city;
        this.build = build;
        this.apartment = apartment;
        this.street = street;
    }

    @Override
    public String toString() {
        return "AddressItem{" +
                "id=" + id +
                ", country=" + country +
                ", city=" + city +
                ", build=" + build +
                ", apartment=" + apartment +
                ", street='" + street + '\'' +
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
}
