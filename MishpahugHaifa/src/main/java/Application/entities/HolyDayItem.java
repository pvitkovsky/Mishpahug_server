package Application.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="holyday")
public class HolyDayItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;
    private String Name;
    private Integer religionId;
    private String description;

    public HolyDayItem() {
    }

    public HolyDayItem(Integer id, LocalDate date, String name, Integer religionId, String description) {
        this.id = id;
        this.date = date;
        Name = name;
        this.religionId = religionId;
        this.description = description;
    }

    @Override
    public String toString() {
        return "HolyDayItem{" +
                "id=" + id +
                ", date=" + date +
                ", Name='" + Name + '\'' +
                ", religionId=" + religionId +
                ", description='" + description + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getReligionId() {
        return religionId;
    }

    public void setReligionId(Integer religionId) {
        this.religionId = religionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
