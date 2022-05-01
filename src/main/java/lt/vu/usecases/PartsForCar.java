package lt.vu.usecases;

import java.util.Collections;
import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Car;
import lt.vu.entities.Part;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.CarsDAO;
import lt.vu.persistence.PartsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class PartsForCar implements Serializable {
    @Inject
    private PartsDAO partsDAO;

    @Inject
    private CarsDAO carsDAO;

    @Getter
    @Setter
    private Car car;

    @Getter @Setter
    private Part partToCreate = new Part();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer carId = Integer.parseInt(requestParameters.get("carId"));
        this.car = carsDAO.findOne(carId);
    }

    @Transactional
    @LoggedInvocation
    public void createPart() {
        partToCreate.setCompatibleCars(Collections.singletonList(this.car));
        partsDAO.persist(partToCreate);
    }
}
