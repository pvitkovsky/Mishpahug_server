package application.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

//TODO: hashcode, unique, toString
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "userActor")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class LogsDataEntity {

    @Id
    @SequenceGenerator(name = "buiness_logs_seq", sequenceName = "logs_data_entity_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "buiness_logs_seq")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_actor")
    //TODO: unidirectional safety check;
    private UserEntity userActor;

    @Column(name = "date", nullable = false)
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate date;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    @Column(name = "text_description")
    private String description;


}
