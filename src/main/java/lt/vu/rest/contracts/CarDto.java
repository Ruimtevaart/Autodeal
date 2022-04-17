package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CarDto {

    private String Make;

    private Integer Year;

    private String OwnerName;
}
