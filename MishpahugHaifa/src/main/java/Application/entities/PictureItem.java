package Application.entities;

import javax.persistence.*;

@Entity
@Table(name="picture")
public class PictureItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer user_id;
    private String data;
}
