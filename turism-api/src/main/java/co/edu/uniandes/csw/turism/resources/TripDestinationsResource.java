package co.edu.uniandes.csw.turism.resources;

import co.edu.uniandes.csw.turism.api.IDestinationLogic;
import co.edu.uniandes.csw.turism.dtos.detail.DestinationDetailDTO;
import co.edu.uniandes.csw.turism.entities.DestinationEntity;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * URI: /trips/{tripId: \\d+}/destinations
 *
 * @generated
 */
@Path("/trips/{tripId: \\d+}/destinations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TripDestinationsResource {

    @Inject
    private IDestinationLogic destinationLogic;
    @Context
    private HttpServletResponse response;
    @QueryParam("page")
    private Integer page;
    @QueryParam("limit")
    private Integer maxRecords;
    @PathParam("tripId")
    private Long tripId;

    /**
     * Convierte una lista de DestinationEntity a una lista de DestinationDetailDTO
     *
     * @param entityList Lista de DestinationEntity a convertir
     * @return Lista de DestinationDetailDTO convertida
     * @generated
     */
    private List<DestinationDetailDTO> listEntity2DTO(List<DestinationEntity> entityList) {
        List<DestinationDetailDTO> list = new ArrayList<>();
        for (DestinationEntity entity : entityList) {
            list.add(new DestinationDetailDTO(entity));
        }
        return list;
    }

    /**
     * Obtiene la lista de los registros de Destination asociados a un Trip
     *
     * @return Colección de objetos de DestinationDetailDTO
     * @generated
     */
    @GET
    public List<DestinationDetailDTO> getDestinations() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", destinationLogic.countDestinations());
            return listEntity2DTO(destinationLogic.getDestinations(page, maxRecords, tripId));
        }
        return listEntity2DTO(destinationLogic.getDestinations(tripId));
    }
}
