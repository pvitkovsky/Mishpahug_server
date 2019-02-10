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
public class FeedBackItem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String comment;
    private LocalDateTime dateTime;
    private Integer rating;

    @ManyToOne
    @JsonBackReference
    private UserItem userItem;

    @ManyToOne
    @JsonBackReference
    private EventItem eventItem;



}
