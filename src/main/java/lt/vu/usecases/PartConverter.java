package lt.vu.usecases;

import lt.vu.entities.Part;
import lt.vu.persistence.PartsDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

@ManagedBean
@RequestScoped
public class PartConverter implements Converter {

    @Inject
    private PartsDAO partsDAO;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }
        return partsDAO.findByName(submittedValue);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null) {
            return "";
        }
        if (o instanceof Part) {
            return ((Part) o).getName();
        }
        return "";
    }
}
