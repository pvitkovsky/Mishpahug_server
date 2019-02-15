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
    private LocalDate date;
    private String Name;
    private Integer religionId;
    private String description;


    public HoliDayEntity(LocalDate date, String name, Integer religionId, String description) {
        this.date = date;
        Name = name;
        this.religionId = religionId;
        this.description = description;
    }


}
