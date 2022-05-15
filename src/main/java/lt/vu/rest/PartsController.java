package lt.vu.rest;

import java.util.HashMap;
import lt.vu.entities.Part;
import lt.vu.persistence.PartsDAO;
import lt.vu.rest.resource.PartDto;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import lt.vu.service.NameChecker;
import lt.vu.service.NameNumberChecker;
import lt.vu.service.NameSymbolChecker;

import static java.util.Objects.isNull;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.CONFLICT;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.status;

@ApplicationScoped
@Path("/parts")
public class PartsController {
  @Inject
  @Getter
  @Setter
  private PartsDAO partsDAO;

  @Inject
  private NameChecker nameChecker;

  @Inject
  private NameSymbolChecker nameSymbolChecker;

  @GET
  @Path("/{id}")
  @Produces(APPLICATION_JSON)
  public Response getById(@PathParam("id") Integer id) {
    Part part = partsDAO.findOne(id);
    if (isNull(part)) {
      return status(NOT_FOUND).build();
    }
    PartDto partDto = new PartDto(part.getName(), part.getPrice());
    return ok(partDto).build();
  }

  @GET
  @Path("/alt/{id}")
  @Produces(APPLICATION_JSON)
  public Response getByIdAlt(@PathParam("id") Integer id) {
    Part part = partsDAO.findOne(id);
    if (isNull(part)) {
      return status(NOT_FOUND).build();
    }
    PartDto partDto = new PartDto(part.getName(), part.getPrice());

    HashMap<String, Boolean> response = new HashMap<>();
    response.put("alternativeNameCheck", nameChecker.containsSpecialChars(partDto.getName()));
    response.put("specializesNameCheck", nameSymbolChecker.containsSpecialChars(partDto.getName()));

    return ok(response).build();
  }

  @PUT
  @Path("/{id}")
  @Consumes(APPLICATION_JSON)
  @Transactional
  public Response update(@PathParam("id") Integer id, PartDto partDto) {
    try {
      Part existingPart = partsDAO.findOne(id);
      if (isNull(existingPart)) {
        return status(NOT_FOUND).build();
      }

      existingPart.setName(partDto.getName());
      existingPart.setPrice(partDto.getPrice());
      partsDAO.update(existingPart);
      return ok().build();
    } catch (OptimisticLockException e) {
      return status(CONFLICT).build();
    }
  }


  @POST
  @Path("/")
  @Consumes(APPLICATION_JSON)
  @Transactional
  public Response create(Part part) {
    partsDAO.persist(part);
    return ok().build();
  }
}
