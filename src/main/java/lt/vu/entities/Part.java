package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
  @NamedQuery(name = "Part.findAll", query = "select p from Part as p")
})
@Table(name = "PART")
@Getter @Setter
public class Part {

    public Part(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "PRICE")
    private Integer price;

    @ManyToMany(mappedBy = "compatibleParts")
    private List<Car> compatibleCars = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return Objects.equals(id, part.id) && Objects.equals(title, part.title)
          && Objects.equals(price, part.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price);
    }
}
