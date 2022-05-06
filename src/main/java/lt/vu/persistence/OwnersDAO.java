package lt.vu.persistence;

import lt.vu.entities.Owner;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class OwnersDAO {

    @Setter
    @Inject
    private EntityManager entityManager;

    public void persist(Owner owner) {
        entityManager.persist(owner);
    }
}
