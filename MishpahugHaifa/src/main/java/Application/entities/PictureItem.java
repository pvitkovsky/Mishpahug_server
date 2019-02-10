package Application.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="picture")
public class PictureItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String data;

    @ManyToOne
    @JsonBackReference
    private UserItem userItemOwner;

}
