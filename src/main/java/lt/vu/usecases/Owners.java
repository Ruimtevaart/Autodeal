package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.OwnersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Console;
import java.io.Serializable;
import java.util.List;

@Model
public class Owners implements Serializable {

    @Inject
    private OwnersDAO ownersDAO;

    @Getter
    @Setter
    private lt.vu.entities.Owner ownerToCreate = new lt.vu.entities.Owner();

    @Getter
    private List<lt.vu.entities.Owner> allOwners;

    @PostConstruct
    private void init() {
        loadAllOwners();
    }

    @Transactional
    public String createOwner() {
        System.out.println("createOwner triggered");
        ownersDAO.persist(ownerToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllOwners() {
        this.allOwners = ownersDAO.loadAll();
    }
}
