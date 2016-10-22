/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.resources;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.turism.api.IDestinationLogic;
import co.edu.uniandes.csw.turism.dtos.detail.DestinationDetailDTO;
import co.edu.uniandes.csw.turism.entities.DestinationEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jd.cepeda
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DestinationResource {

    @Inject
    private IDestinationLogic destinationLogic;
    @Context
    private HttpServletResponse response;
    @QueryParam("page")
    private Integer page;
    @QueryParam("limit")
    private Integer maxRecords;
    @PathParam("tripsId")
    private Long tripsId;

    /**
     * Convierte una lista de DestinationEntity a una lista de
     * DestinationDetailDTO
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
            return listEntity2DTO(destinationLogic.getDestinations(page, maxRecords, tripsId));
        }
        return listEntity2DTO(destinationLogic.getDestinations(tripsId));
    }

    /**
     * Obtiene los datos de una instancia de Destination a partir de su ID
     * asociado a un Trip
     *
     * @param destinationId Identificador de la instancia a consultar
     * @return Instancia de DestinationDetailDTO con los datos del Destination
     * consultado
     * @generated
     */
    @GET
    @Path("{destinationId: \\d+}")
    public DestinationDetailDTO getDestination(@PathParam("destinationId") Long destinationId) {
        DestinationEntity entity = destinationLogic.getDestination(destinationId);
        if (entity.getTrip() != null && !tripsId.equals(entity.getTrip().getId())) {
            throw new WebApplicationException(404);
        }
        return new DestinationDetailDTO(entity);
    }

    /**
     * Asocia un Destination existente a un Trip
     *
     * @param dto Objeto de DestinationDetailDTO con los datos nuevos
     * @return Objeto de DestinationDetailDTOcon los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public DestinationDetailDTO createDestination(DestinationDetailDTO dto) {
        return new DestinationDetailDTO(destinationLogic.createDestination(tripsId, dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Destination.
     *
     * @param destinationId Identificador de la instancia de Destination a
     * modificar
     * @param dto Instancia de DestinationDetailDTO con los nuevos datos.
     * @return Instancia de DestinationDetailDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{destinationId: \\d+}")
    public DestinationDetailDTO updateDestination(@PathParam("destinationId") Long destinationId, DestinationDetailDTO dto) {
        DestinationEntity entity = dto.toEntity();
        entity.setId(destinationId);
        return new DestinationDetailDTO(destinationLogic.updateDestination(tripsId, entity));
    }

    /**
     * Elimina una instancia de Destination de la base de datos.
     *
     * @param destinationId Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{destinationId: \\d+}")
    public void deleteDestination(@PathParam("destinationId") Long destinationId) {
        destinationLogic.deleteDestination(destinationId);
    }

}
