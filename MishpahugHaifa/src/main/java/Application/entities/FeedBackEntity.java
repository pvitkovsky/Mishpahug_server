package Application.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="feedback")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FeedBackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String comment;


    private LocalDateTime dateTime;


    private Integer rating;

    @ManyToOne
    @JsonBackReference
    //@JoinColumn(name="user", nullable=false)
    private UserEntity userEntity;

    @ManyToOne
    @JsonBackReference
    //@JoinColumn(name="event", nullable=false)
    private EventEntity eventEntity;



}
