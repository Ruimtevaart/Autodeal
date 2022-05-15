package lt.vu.service;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class PartPriceGenerator implements Serializable {

    public Integer generatePrice() {
        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        Integer generatedCost = new Random().nextInt(1000);
        return generatedCost;
    }
}