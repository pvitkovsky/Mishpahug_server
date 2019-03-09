package Application.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="feedback")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString
@EqualsAndHashCode(of = {"comment", "dateTime", "rating", "userItem", "eventItem"})
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
    @PrimaryKeyJoinColumn(name="USERID", referencedColumnName="id")
    @JsonBackReference
    @ToString.Exclude
    //TODO: safe bidirectional setter
    private UserEntity userItem;

    @ManyToOne
    @PrimaryKeyJoinColumn(name="EVENTID", referencedColumnName="id")
    @JsonBackReference
    @ToString.Exclude
    //TODO: safe bidirectional setter
    private EventEntity eventItem;


    public void setData(UserEntity userEntity, EventEntity eventEntity){
        this.setUserItem(userEntity);
        this.setEventItem(eventEntity);
        eventItem.getFeedbacks().put(this.id, this);
        userEntity.getFeedbacks().put(this.id, this);

    }
}

