package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.OwnersDAO;
import lt.vu.entities.Owner;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Owners {

    @Inject
    private OwnersDAO ownersDAO;

    @Getter @Setter
    private Owner ownerToCreate = new Owner();

    @Getter
    private List<Owner> allOwners;

    @PostConstruct
    public void init(){
        loadAllOwners();
    }

    @Transactional
    public void createOwner(){
        this.ownersDAO.persist(ownerToCreate);
    }

    private void loadAllOwners(){
        this.allOwners = ownersDAO.loadAll();
    }
}
