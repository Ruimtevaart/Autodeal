package lt.vu.usecases;

import lt.vu.entities.Car;
import lt.vu.persistence.CarsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class Cars implements Serializable {

    @Inject
    private CarsDAO carsDAO;

    @Getter
    @Setter
    private Car carToCreate = new Car();

    @Getter
    private List<Car> allCars;

    @PostConstruct
    private void init() {
        loadAllCars();
    }

    @Transactional
    public String createCar() {
        carsDAO.persist(carToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllCars() {
        this.allCars = carsDAO.loadAll();
    }
}
