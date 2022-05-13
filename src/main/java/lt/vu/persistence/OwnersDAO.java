package lt.vu.persistence;

import lt.vu.entities.Car;
import lt.vu.entities.Owner;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class OwnersDAO {

    @Setter
    @Inject
    private EntityManager entityManager;

    public void persist(Owner owner) {
        entityManager.persist(owner);
    }

    public List<Owner> loadAll() {
        return entityManager.createNamedQuery("Owner.findAll", Owner.class).getResultList();
    }

    public Owner findOne(int id) {
        return entityManager.find(Owner.class, id);
    }
}
