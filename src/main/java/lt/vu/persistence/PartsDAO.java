package lt.vu.persistence;

import lt.vu.entities.Part;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class PartsDAO {

    @Setter
    @Inject
    private EntityManager entityManager;

    public void persist(Part part) {
        entityManager.persist(part);
    }

    public List<Part> findAll() {
        return entityManager.createNamedQuery("Part.findAll", Part.class).getResultList();
    }

    public Part findByName(String name) {
        try {
            Query query = entityManager.createNamedQuery("Part.findByName");
            query.setParameter("name", name);
            return (Part) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Part findOne(int id) {
        return entityManager.find(Part.class, id);
    }

    public Part update(Part part) {
        return entityManager.merge(part);
    }
}
