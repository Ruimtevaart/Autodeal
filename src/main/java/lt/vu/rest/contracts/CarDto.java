package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CarDto {

    private String Make;

    private Integer Year;

    private String OwnerFirstName;

    private String Model;

    private Integer Price;
}
