package Application.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name="feedback")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FeedBackEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length=255)
    @NotNull
    private String comment;

    private LocalDateTime dateTime;

    @Range(min = 1, max = 5)
    private Integer rating;

    @ManyToOne
    @JsonBackReference
    private UserEntity userItem;

    @ManyToOne
    @JsonBackReference
    private EventEntity eventItem;



}

