package lt.vu.usecases.mybatis;

import lt.vu.mybatis.dao.CarMapper;
import lt.vu.mybatis.dao.InstructedCarMapper;
import lt.vu.mybatis.dao.PartMapper;
import lt.vu.mybatis.model.Car;
import lt.vu.mybatis.model.InstructedCar;
import lt.vu.mybatis.model.Part;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

import static javax.faces.context.FacesContext.getCurrentInstance;

@Model
public class OwnersForCarMyBatis implements Serializable {

    @Inject
    private CarMapper carMapper;

    @Inject
    private PartMapper partMapper;

    @Inject
    private InstructedCarMapper instructedCarMapper;

    @Getter
    @Setter
    private Car car;

    @Getter
    @Setter
    private Part partToAdd = new Part();

    @Transactional
    public String addPartForCar() {
        partMapper.insert(partToAdd);
        Part createdPart = partMapper.findByName(partToAdd.getName());
        InstructedCar instructedCar = new InstructedCar();
        instructedCar.setCarId(this.car.getId());
        instructedCar.setPartId(createdPart.getId());
        instructedCarMapper.insert(instructedCar);
        return "cars?faces-redirect=true";
    }

    @PostConstruct
    private void init() {
        Map<String, String > requestParams = getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();

        int carId = Integer.parseInt(requestParams.get("carId"));
        this.car = carMapper.selectByPrimaryKey(carId);
    }
}
