package lt.vu.usecases;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Part;
import lt.vu.persistence.CarsDAO;
import lt.vu.persistence.PartsDAO;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

import static javax.faces.context.FacesContext.getCurrentInstance;

@Named
@Getter
@Setter
@ViewScoped
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

    @Transactional
    public String updatePartPrice() {
        try {
            partsDAO.update(this.part);
        }
        catch (OptimisticLockException e) {
            return "/carsForPart?faces-redirect=true&partId=" + this.part.getId() + "&error=optimistic-lock-exception";
        }
        return "/carsForPart?faces-redirect=true&partId=" + this.part.getId();
    }
}
