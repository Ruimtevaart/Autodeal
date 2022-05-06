package lt.vu.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "CAR_ID")
    private Car car;

    public Owner(String fullName) {
        this.fullName = fullName;
    }

    public Owner(String fullName, Car car) {
        this(fullName);
        this.car = car;
    }
}
