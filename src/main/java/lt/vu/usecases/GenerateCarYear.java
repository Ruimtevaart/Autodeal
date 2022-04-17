package lt.vu.usecases;

import lt.vu.interceptors.LoggedInvocation;
import lt.vu.services.YearGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GenerateCarYear implements Serializable {
    @Inject
    YearGenerator yearGenerator;

    private CompletableFuture<Integer> yearGenerationTask = null;

    @LoggedInvocation
    public String generateNewYear() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        yearGenerationTask = CompletableFuture.supplyAsync(() -> yearGenerator.generateYear());

        return  "/carDetails.xhtml?faces-redirect=true&carId=" + requestParameters.get("carId");
    }

    public boolean isYearGenerationRunning() {
        return yearGenerationTask != null && !yearGenerationTask.isDone();
    }

    public String getYearGenerationStatus() throws ExecutionException, InterruptedException {
        if (yearGenerationTask == null) {
            return null;
        } else if (isYearGenerationRunning()) {
            return "Year generation in progress";
        }
        return "Suggested year number: " + yearGenerationTask.get();
    }
}
