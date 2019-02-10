package Application.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


// NEED TO DELETE!!!!!!!!!!!!!!!!!!!! WRONG NAME!!!!!!!!

@Entity
@Table(name="freeback")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FreeBackItem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer eventId;
    private String comment;
    private LocalDateTime dateTime;
    private Integer rating;
    private Integer userId;


    public FreeBackItem(Integer eventId, String comment, LocalDateTime dateTime, Integer rating, Integer userId) {
        this.eventId = eventId;
        this.comment = comment;
        this.dateTime = dateTime;
        this.rating = rating;
        this.userId = userId;
    }


}
