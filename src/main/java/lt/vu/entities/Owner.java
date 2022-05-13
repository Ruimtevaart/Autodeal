package lt.vu.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@Table(name = "OWNER")
@NamedQueries({
        @NamedQuery(name = "Owner.findAll", query = "select a from Owner as a")
})
public class Owner {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Integer id;

    @Column(name = "FULL_NAME")
    String fullName;

    @OneToMany
    @JoinColumn(name = "OWNER_ID")
    private List<Car> cars = new ArrayList<Car>();

    public Owner(String fullName) {
        this.fullName = fullName;
    }

    public Owner(String fullName, List<Car> cars) {
        this(fullName);
        this.cars = cars;
    }
}
