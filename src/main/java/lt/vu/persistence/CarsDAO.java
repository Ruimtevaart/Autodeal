package lt.vu.persistence;

import lt.vu.entities.Car;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CarsDAO {

    @Setter
    @Inject
    private EntityManager entityManager;

    public List<Car> loadAll() {
        return entityManager.createNamedQuery("Car.findAll", Car.class).getResultList();
    }

    public void persist(Car car) {
        entityManager.persist(car);
    }

    public Car findOne(int id) {
        return entityManager.find(Car.class, id);
    }

    public void update(Car car) {
        entityManager.merge(car);
    }
}
