package lt.vu.usecases;

import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Car;
import lt.vu.entities.Part;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.CarsDAO;
import lt.vu.persistence.PartsDAO;

@Model
public class CarsForPart implements Serializable {

  @Inject
  private PartsDAO partsDAO;

  @Inject
  private CarsDAO carsDAO;

  @Getter
  @Setter
  private Part part;

  @Getter @Setter
  private Car carToCreate = new Car();

  @PostConstruct
  public void init() {
    Map<String, String> requestParameters =
      FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    Integer partId = Integer.parseInt(requestParameters.get("partId"));
    this.part = partsDAO.findOne(partId);
  }

  @Transactional
  @LoggedInvocation
  public void createCar() {
//    carToCreate.setPart(this.part);
    carsDAO.persist(carToCreate);
  }
}
