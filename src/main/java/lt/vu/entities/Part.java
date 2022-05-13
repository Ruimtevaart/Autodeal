package lt.vu.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@Table(name = "PART")
@NamedQueries({
        @NamedQuery(name = "Part.findAll", query = "select a from Part as a"),
        @NamedQuery(name = "Part.findByName", query = "select a from Part as a where lower(a.name) = lower(:name)")
})
@EqualsAndHashCode
public class Part {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Integer id;

    @Column(name = "NAME")
    String name;

    @Column(name = "PRICE")
    int price;

    @Column
    @ManyToMany
    @JoinTable(
            name = "PART_CAR",
            joinColumns = @JoinColumn(name = "PART_ID"),
            inverseJoinColumns = @JoinColumn(name = "CAR_ID")
    )
    List<Car> cars;


    public Part(String name) {
        this.name = name;
    }
}
