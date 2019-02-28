package Application.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="holyday")
@AllArgsConstructor @NoArgsConstructor
@ToString
@Getter @Setter
public class HoliDayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "name")
    private String name;

    @Column(name = "religion")
    private Integer religionId;

    @Column(name = "description")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HoliDayEntity that = (HoliDayEntity) o;
        return id.equals(that.id) &&
                date.equals(that.date) &&
                name.equals(that.name) &&
                religionId.equals(that.religionId) &&
                description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, name, religionId, description);
    }
}
