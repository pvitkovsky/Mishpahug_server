package Application.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="holyday")
@AllArgsConstructor
@NoArgsConstructor
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


    public HoliDayEntity(LocalDate date, String name, Integer religionId, String description) {
        this.date = date;
        this.name = name;
        this.religionId = religionId;
        this.description = description;
    }


}
