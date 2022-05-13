package lt.vu.usecases.mybatis;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.CarMapper;
import lt.vu.mybatis.dao.PartCarMapper;
import lt.vu.mybatis.dao.PartMapper;
import lt.vu.mybatis.model.Car;
import lt.vu.mybatis.model.Part;
import lt.vu.mybatis.model.PartCar;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

import static javax.faces.context.FacesContext.getCurrentInstance;

@Model
public class CarsForPartMyBatis implements Serializable{
    @Inject
    private PartMapper partMapper;

    @Inject
    private CarMapper carMapper;

    @Inject
    private PartCarMapper partCarMapper;

    @Getter
    @Setter
    private Part part;

    @PostConstruct
    private void init() {
        Map<String, String> requestParams = getCurrentInstance().getExternalContext().getRequestParameterMap();
        int partId = Integer.parseInt(requestParams.get("partId"));
        this.part = partMapper.selectByPrimaryKey(partId);
    }
}
