package lt.vu.usecases;

import lt.vu.entities.Car;
import lt.vu.entities.Part;
import lt.vu.entities.Owner;
import lt.vu.persistence.CarsDAO;
import lt.vu.persistence.PartsDAO;
import lt.vu.persistence.OwnersDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;
import static javax.faces.context.FacesContext.getCurrentInstance;

@Model
public class CarsForOwner implements Serializable {

    @Inject
    private CarsDAO carsDAO;

    @Inject
    private OwnersDAO ownersDAO;

    @Inject
    private PartsDAO partsDAO;

    @Getter
    @Setter
    private Car car;

    @Getter
    @Setter
    private Owner owner;

    @Getter
    @Setter
    private Owner ownerToCreate = new Owner();

    @Getter
    @Setter
    private Car carToCreate = new Car();

    @Getter
    @Setter
    private Part partToAdd = new Part();

    @Getter
    @Setter
    private List<SelectItem> allParts;

    @Getter
    @Setter
    private List<SelectItem> allCars;

    @Transactional
    public String createCar() {
        carToCreate.setOwner(this.owner);
        carsDAO.persist(carToCreate);
        return "cars?faces-redirect=true&ownerId=" + this.owner.getId();
//        return "/index";
    }

    @PostConstruct
    private void init() {
        Map<String, String > requestParams = getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();


        int ownerId = Integer.parseInt(requestParams.get("ownerId"));
        this.owner = ownersDAO.findOne(ownerId);
        this.allParts = new ArrayList<>();
        List<Part> availableParts = partsDAO.findAll();
        for (Part part : availableParts) {
            this.allParts.add(new SelectItem(part, part.getName()));
        }
    }
}
