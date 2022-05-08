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
public class CarsForPart implements Serializable {
    @Inject
    private CarsDAO carsDAO;

    @Inject
    private PartsDAO partsDAO;

    @Getter
    @Setter
    private Part part;

    @PostConstruct
    private void init() {
        Map<String, String > requestParams = getCurrentInstance().getExternalContext().getRequestParameterMap();
        int partId = Integer.parseInt(requestParams.get("partId"));
        this.part = partsDAO.findOne(partId);
    }
}
