/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.resources;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.turism.api.IQuestionLogic;
import co.edu.uniandes.csw.turism.dtos.detail.QuestionDetailDTO;
import co.edu.uniandes.csw.turism.entities.QuestionEntity;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

/**
 * URI: trips/{questionsId: \\d+}/questions/
 * @generated
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class QuestionResource {
    
    @Inject private IQuestionLogic questionLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    @PathParam("tripsId") private Long tripsId;
    
    /**
     * Convierte una lista de QuestionEntity a una lista de QuestionDetailDTO
     *
     * @param entityList Lista de QuestionEntity a convertir
     * @return Lista de QuestionDetailDTO convertida
     * @generated
     */
    private List<QuestionDetailDTO> listEntity2DTO(List<QuestionEntity> entityList){
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
            return listEntity2DTO(questionLogic.getQuestions(page, maxRecords, tripsId));
        }
        return listEntity2DTO(questionLogic.getQuestions(tripsId));
    }
    
    /**
     * Obtiene los datos de una instancia de Question a partir de su ID asociado a un Trip
     *
     * @param quiestionId Identificador de la instancia a consultar
     * @return Instancia de QuestionDetailDTO con los datos del Question consultado
     * @generated
     */
    @GET
    @Path("{questionId: \\d+}")
    public QuestionDetailDTO getQuestion(@PathParam("questionId") Long questionId) {
        QuestionEntity entity = questionLogic.getQuestion(questionId);
        if (entity.getTrip() != null && !tripsId.equals(entity.getTrip().getId())) {
            throw new WebApplicationException(404);
        }
        return new QuestionDetailDTO(entity);
    }
    
    /**
     * Asocia un Question existente a un Trip
     *
     * @param dto Objeto de QuestionDetailDTO con los datos nuevos
     * @return Objeto de QuestionDetailDTO con los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public QuestionDetailDTO createQuestion(QuestionDetailDTO dto) {
        return new QuestionDetailDTO(questionLogic.createQuestion(tripsId, dto.toEntity()));
    }
    
    /**
     * Actualiza la información de una instancia de Question.
     *
     * @param questionId Identificador de la instancia de Question a modificar
     * @param dto Instancia de QuestionDetailDTO con los nuevos datos.
     * @return Instancia de QuestionDetailDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{questionId: \\d+}")
    public QuestionDetailDTO updateQuestion(@PathParam("questionId") Long questionId, QuestionDetailDTO dto) {
        QuestionEntity entity = dto.toEntity();
        entity.setId(questionId);
        QuestionEntity oldEntity = questionLogic.getQuestion(questionId);
        return new QuestionDetailDTO(questionLogic.updateQuestion(tripsId, entity));
    }
    
    /**
     * Elimina una instancia de Question de la base de datos.
     *
     * @param questionId Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{questionId: \\d+}")
    public void deleteQuestion(@PathParam("questionId") Long questionId) {
        questionLogic.deleteQuestion(questionId);
    }    
}
