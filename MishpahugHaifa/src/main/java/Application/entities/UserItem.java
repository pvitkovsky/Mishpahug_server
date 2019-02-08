package Application.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="user")
public class UserItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nickname; //TODO: unique nickname;
    private String firstName;
    private String lastName;
    private Integer religionId;
    private LocalDate ban;
    private String phoneNumber;
    private String eMail;
    private Integer addressId;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public enum UserRole {
    	ADMIN,
    	USER,
    }
    
    public UserItem() {
    }

    @Override
    public String toString() {
        return "UserItem{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", religionId=" + religionId +
                ", ban=" + ban +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", eMail='" + eMail + '\'' +
                ", addressId=" + addressId +
                ", role=" + role +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getReligionId() {
        return religionId;
    }

    public void setReligionId(Integer religionId) {
        this.religionId = religionId;
    }

    public LocalDate getBan() {
        return ban;
    }

    public void setBan(LocalDate ban) {
        this.ban = ban;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public UserItem(String nickname, String firstName, String lastName, Integer religionId, LocalDate ban, String phoneNumber, String eMail, Integer addressId, UserRole role) {
        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.religionId = religionId;
        this.ban = ban;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
        this.addressId = addressId;
        this.role = role;
    }
}
