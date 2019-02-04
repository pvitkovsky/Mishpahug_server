package Application.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="User")
public class UserItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nickname;
    private String firstName;
    private String lastName;
    private Integer religionId;
    private LocalDate ban;
    private String phoneNumber;
    private String eMail;
    private String role;

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
                ", role='" + role + '\'' +
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserItem(Integer id, String nickname, String firstName, String lastName, Integer religionId, LocalDate ban, String phoneNumber, String eMail, String role) {
        this.id = id;
        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.religionId = religionId;
        this.ban = ban;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
        this.role = role;
    }
}
