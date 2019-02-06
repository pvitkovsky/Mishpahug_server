package Application.entities;

import javax.persistence.*;

@Entity
@Table(name="country")
public class CountryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String name;
}
