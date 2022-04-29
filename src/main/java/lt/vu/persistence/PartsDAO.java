package lt.vu.persistence;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import lt.vu.entities.Part;

@ApplicationScoped
public class PartsDAO {
  @Inject
  private EntityManager em;

  public List<Part> loadAll() {
    return em.createNamedQuery("Part.findAll", Part.class).getResultList();
  }

  public void setEm(EntityManager em) {
    this.em = em;
  }

  public void persist(Part part){
    this.em.persist(part);
  }

  public Part findOne(Integer id) {
    return em.find(Part.class, id);
  }
}
