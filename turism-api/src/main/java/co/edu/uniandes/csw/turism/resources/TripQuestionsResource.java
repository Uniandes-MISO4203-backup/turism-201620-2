package co.edu.uniandes.csw.turism.resources;

import co.edu.uniandes.csw.turism.api.IQuestionLogic;
import co.edu.uniandes.csw.turism.dtos.detail.QuestionDetailDTO;
import co.edu.uniandes.csw.turism.entities.QuestionEntity;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * URI: /trips/{tripId: \\d+}/questions
 *
 * @generated
 */
@Path("/trips/{tripId: \\d+}/questions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TripQuestionsResource {

    @Inject
    private IQuestionLogic questionLogic;
    @Context
    private HttpServletResponse response;
    @QueryParam("page")
    private Integer page;
    @QueryParam("limit")
    private Integer maxRecords;
    @PathParam("tripId")
    private Long tripId;

    /**
     * Convierte una lista de QuestionEntity a una lista de QuestionDetailDTO
     *
     * @param entityList Lista de QuestionEntity a convertir
     * @return Lista de QuestionDetailDTO convertida
     * @generated
     */
    private List<QuestionDetailDTO> listEntity2DTO(List<QuestionEntity> entityList) {
        List<QuestionDetailDTO> list = new ArrayList<>();
        for (QuestionEntity entity : entityList) {
            list.add(new QuestionDetailDTO(entity));
        }
        return list;
    }

    /**
     * Obtiene la lista de los registros de Question asociados a un Trip
     *
     * @return Colección de objetos de QuestionDetailDTO
     * @generated
     */
    @GET
    public List<QuestionDetailDTO> getQuestions() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", questionLogic.countQuestions());
            return listEntity2DTO(questionLogic.getQuestions(page, maxRecords, tripId));
        }
        return listEntity2DTO(questionLogic.getQuestions(tripId));
    }
}
