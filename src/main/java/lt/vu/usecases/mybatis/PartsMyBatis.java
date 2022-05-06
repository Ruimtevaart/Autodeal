package lt.vu.usecases.mybatis;

import lt.vu.mybatis.dao.PartMapper;
import lt.vu.mybatis.model.Part;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Model
public class PartsMyBatis implements Serializable {

    @Inject
    private PartMapper mapper;

    @Getter
    private List<Part> allParts;

    @PostConstruct
    private void init() {
        loadAllParts();
    }

    private void loadAllParts() {
        allParts = mapper.selectAll();
    }
}
