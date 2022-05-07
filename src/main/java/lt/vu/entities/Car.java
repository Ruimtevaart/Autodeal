package lt.vu.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@Table(name = "CAR")
@NamedQueries({
        @NamedQuery(name = "Car.findAll", query = "select a from Car as a")
})
public class Car {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Integer id;

    @Size(max = 50)
    @Column(name = "TITLE")
    String title;

    @Column(name = "PRICE")
    int price;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    Owner owner;

    @ManyToMany(mappedBy = "cars")
    List<Part> parts;

    public Car(String title) {
        this.title = title;
    }

    public Car(String title, Owner owner) {
        this(title);
        this.owner = owner;
    }

}
