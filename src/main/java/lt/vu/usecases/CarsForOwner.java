package lt.vu.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.CarsDAO;
import lt.vu.persistence.OwnersDAO;
import lt.vu.entities.Car;
import lt.vu.entities.Owner;

@Model
public class CarsForOwner implements Serializable {

    @Inject
    private OwnersDAO ownersDAO;

    @Inject
    private CarsDAO carsDAO;

    @Getter @Setter
    private Owner owner;

    @Getter @Setter
    private Car carToCreate = new Car();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer ownerId = Integer.parseInt(requestParameters.get("ownerId"));
        this.owner = ownersDAO.findOne(ownerId);
    }

    @Transactional
    @LoggedInvocation
    public void createCar() {
        carToCreate.setOwner(this.owner);
        carsDAO.persist(carToCreate);
    }
}
