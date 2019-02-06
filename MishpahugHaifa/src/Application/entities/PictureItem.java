package Application.entities;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name="picture")
public class PictureItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer user_id;
    private Blob image;

    public PictureItem(Integer id, Integer user_id, Blob image) {
        this.id = id;
        this.user_id = user_id;
        this.image = image;
    }

    @Override
    public String toString() {
        return "PictureItem{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", image=" + image +
                '}';
    }

    public PictureItem() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }
}
