package lt.vu.usecases.mybatis;

import lt.vu.mybatis.dao.CarMapper;
import lt.vu.mybatis.model.Car;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class CarsMyBatis implements Serializable {

    @Inject
    private CarMapper carsMapper;

    @Getter
    private List<Car> allCars;

    @Getter
    @Setter
    private Car carToCreate = new Car();

    @PostConstruct
    private void init() {
        loadAllCars();
    }

    @Transactional
    public String createCar() {
        carsMapper.insert(carToCreate);
        return "/myBatis/cars?faces-redirect=true";
    }

    private void loadAllCars() {
        allCars = carsMapper.selectAll();
    }
}
