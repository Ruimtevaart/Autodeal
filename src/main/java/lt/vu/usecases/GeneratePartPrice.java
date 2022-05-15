package lt.vu.usecases;

import lt.vu.interceptors.Logger;
import lt.vu.service.PartPriceGenerator;

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
public class GeneratePartPrice implements Serializable {
    @Inject
    PartPriceGenerator partPriceGenerator;

    private CompletableFuture<Integer> priceGenerationTask = null;

    @Logger
    public String generateNewPrice() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        priceGenerationTask = CompletableFuture.supplyAsync(() -> partPriceGenerator.generatePrice());
        return "/carsForPart?faces-redirect=true&partId=" + requestParameters.get("partId");
    }

    public boolean isPriceGenerationRunning() {
        return priceGenerationTask != null && !priceGenerationTask.isDone();
    }

    public String getPriceGenerationStatus() throws ExecutionException, InterruptedException {
        if (priceGenerationTask == null) {
            return null;
        } else if (isPriceGenerationRunning()) {
            return "Price generation in progress...";
        }
        return "Generated price: " + priceGenerationTask.get();
    }
}
