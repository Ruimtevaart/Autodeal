package lt.vu.usecases.mybatis;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.OwnerMapper;
import lt.vu.mybatis.model.Owner;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class OwnersMyBatis implements Serializable {
    @Inject
    private OwnerMapper ownerMapper;

    @Getter
    private List<Owner> allOwners;

    @Getter
    @Setter
    private Owner ownerToCreate = new Owner();

    @PostConstruct
    private void init() {
        loadAllOwners();
    }

    @Transactional
    public String createOwner() {
        ownerMapper.insert(ownerToCreate);
        return "/myBatis/owners?faces-redirect=true";
    }

    private void loadAllOwners() {
        allOwners = ownerMapper.selectAll();
    }
}
