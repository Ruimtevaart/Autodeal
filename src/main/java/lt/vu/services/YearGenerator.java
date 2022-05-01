package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class YearGenerator implements Serializable {

    public Integer generateYear() {
        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
        }
        Integer generatedYear = new Random().nextInt(100);
        return generatedYear;
    }
}