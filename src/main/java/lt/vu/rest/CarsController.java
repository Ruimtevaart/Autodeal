package lt.vu.rest;

import lombok.*;
import lt.vu.rest.contracts.CarDto;
import lt.vu.entities.Car;
import lt.vu.persistence.CarsDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/cars")
public class CarsController {

    @Inject
    @Setter @Getter
    private CarsDAO carsDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Car car = carsDAO.findOne(id);
        if (car == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        CarDto carDto = new CarDto();
        carDto.setMake(car.getMake());
        carDto.setYear(car.getYear());
        carDto.setOwnerName(car.getOwner().getName());

        return Response.ok(carDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer carId,
            CarDto carData) {
        try {
            Car existingCar = carsDAO.findOne(carId);
            if (existingCar == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingCar.setMake(carData.getMake());
            existingCar.setYear(carData.getYear());
            carsDAO.update(existingCar);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
