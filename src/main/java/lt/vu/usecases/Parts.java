package lt.vu.usecases;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Part;
import lt.vu.persistence.PartsDAO;

@Model
public class Parts {
  @Inject
  private PartsDAO partsDAO;

  @Getter
  @Setter
  private Part partToCreate = new Part();

  @Getter
  private List<Part> allParts;

  @PostConstruct
  public void init(){
    loadAllParts();
  }

  @Transactional
  public void createPart(){
    this.partsDAO.persist(partToCreate);
  }

  private void loadAllParts(){
    this.allParts = partsDAO.loadAll();
  }
}
