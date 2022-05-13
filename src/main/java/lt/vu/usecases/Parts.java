package lt.vu.usecases;

import lt.vu.entities.Part;
import lt.vu.persistence.PartsDAO;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Model
public class Parts implements Serializable {

    @Inject
    private PartsDAO dao;

    @Getter
    private List<Part> allParts;

    @PostConstruct
    private void init() {
        loadAllParts();
    }

    private void loadAllParts() {
        allParts = dao.findAll();
    }


}
