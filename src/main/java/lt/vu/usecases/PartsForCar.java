package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Car;
import lt.vu.entities.Part;
import lt.vu.persistence.CarsDAO;
import lt.vu.persistence.PartsDAO;

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
public class PartsForCar implements Serializable {

    @Inject
    private CarsDAO carsDAO;

    @Inject
    private PartsDAO partsDAO;

    @Getter
    @Setter
    private Car car;

    @Getter
    @Setter
    private Part partToAdd = new Part();

    @Getter
    @Setter
    private List<SelectItem> allParts;

    @Transactional
    public String createPartFromDropdown() {
        List<Car> allPartCars = partToAdd.getCars();
        allPartCars.add(this.car);
        partToAdd.setCars(allPartCars);
        partsDAO.update(partToAdd);
//        return "index?faces-redirect=true";
        return "parts?faces-redirect=true&carId=" + this.car.getId();
    }

    @Transactional
    public String createPart() {
        Part foundPart = partsDAO.findByName(partToAdd.getName());
        if (isNull(foundPart)) {
            handleNewPart();
        } else {
            handleExistingPart(foundPart);
        }

//        return "index?faces-redirect=true";
        return "parts?faces-redirect=true&carId=" + this.car.getId();
    }

    private void handleExistingPart(Part foundPart) {
        partToAdd.setId(foundPart.getId());
        List<Car> allPartCars = foundPart.getCars();
        allPartCars.add(this.car);
        partToAdd.setCars(allPartCars);
        partsDAO.update(partToAdd);
    }

    private void handleNewPart() {
        List<Car> allPartCars = new ArrayList<>();
        allPartCars.add(this.car);
        partToAdd.setCars(allPartCars);
        partsDAO.persist(partToAdd);
    }

    @PostConstruct
    private void init() {
        Map<String, String > requestParams = getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();


        int carId = Integer.parseInt(requestParams.get("carId"));
        this.car = carsDAO.findOne(carId);
        this.allParts = new ArrayList<>();
        List<Part> availableParts = partsDAO.findAll();
        for (Part part : availableParts) {
            this.allParts.add(new SelectItem(part, part.getName()));
        }
    }
}
