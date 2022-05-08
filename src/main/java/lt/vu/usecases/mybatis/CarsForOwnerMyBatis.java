package lt.vu.usecases.mybatis;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.CarMapper;
import lt.vu.mybatis.dao.OwnerMapper;
import lt.vu.mybatis.model.Car;
import lt.vu.mybatis.model.Owner;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

import static javax.faces.context.FacesContext.getCurrentInstance;

@Model
public class CarsForOwnerMyBatis implements Serializable {
    @Inject
    private CarMapper carMapper;

    @Inject
    private OwnerMapper ownerMapper;

    @Getter
    @Setter
    private Owner owner;

    @Getter
    @Setter
    private Car carToAdd = new Car();

    @Transactional
    public String addCarForOwner() {
//        carToAdd.setOwnerId(owner.getId());
        carMapper.insert(carToAdd);
        return "/mybatis/owners?faces-redirect=true";
    }

    @PostConstruct
    private void init() {
        System.out.println("CarsForOwner init triggered");
        Map<String, String> requestParams = getCurrentInstance().getExternalContext().getRequestParameterMap();
        int ownerId = Integer.parseInt(requestParams.get("ownerId"));
        this.owner = ownerMapper.selectByPrimaryKey(ownerId);
    }
}
