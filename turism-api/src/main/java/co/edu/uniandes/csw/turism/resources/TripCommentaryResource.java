package co.edu.uniandes.csw.turism.resources;

import co.edu.uniandes.csw.turism.api.ICommentaryLogic;
import co.edu.uniandes.csw.turism.api.IQuestionLogic;
import co.edu.uniandes.csw.turism.dtos.detail.CommentaryDetailDTO;
import co.edu.uniandes.csw.turism.entities.CommentaryEntity;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * URI: /trips/{tripId: \\d+}/commentaries
 *
 * @generated
 */
@Path("/trips/{tripId: \\d+}/commentaries")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TripCommentaryResource {

    @Inject
    private ICommentaryLogic commentaryLogic;
    @Context
    private HttpServletResponse response;
    @QueryParam("page")
    private Integer page;
    @QueryParam("limit")
    private Integer maxRecords;
    @PathParam("tripId")
    private Long tripId;

    /**
     * Convierte una lista de CommentaryEntity a una lista de
     * CommentaryDetailDTO.
     *
     * @param entityList Lista de CommentaryEntity a convertir.
     * @return Lista de CommentaryDetailDTO convertida.
     * @generated
     */
    private List<CommentaryDetailDTO> listEntity2DTO(List<CommentaryEntity> entityList) {
        List<CommentaryDetailDTO> list = new ArrayList<>();
        for (CommentaryEntity entity : entityList) {
            list.add(new CommentaryDetailDTO(entity));
        }
        return list;
    }

    /**
     * Obtiene la lista de los registros de Commentaries
     *
     * @return Colección de objetos de CommentaryDetailDTO
     * @generated
     */
    @GET
    public List<CommentaryDetailDTO> getComments() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", commentaryLogic.countComments());
            return listEntity2DTO(commentaryLogic.getComments(page, maxRecords, tripId));
        }
        return listEntity2DTO(commentaryLogic.getComments(tripId));
    }
}
