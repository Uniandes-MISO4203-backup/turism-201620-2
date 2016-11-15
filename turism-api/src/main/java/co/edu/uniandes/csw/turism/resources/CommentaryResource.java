/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
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
import co.edu.uniandes.csw.turism.dtos.detail.CommentaryDetailDTO;
import co.edu.uniandes.csw.turism.entities.CommentaryEntity;
import java.util.ArrayList;
import co.edu.uniandes.csw.turism.api.ICommentaryLogic;
import javax.ws.rs.WebApplicationException;

/**
 * URI: trips/{commentsId: \\d+}/commentarys/
 *
 * @generated
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentaryResource {

    @Inject
    private ICommentaryLogic commentaryLogic;
    @Context
    private HttpServletResponse response;
    @QueryParam("page")
    private Integer page;
    @QueryParam("limit")
    private Integer maxRecords;
    @PathParam("tripsId")
    private Long tripsId;

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
     * Obtiene la lista de los registros de Comments
     *
     * @return Colección de objetos de CommentaryDetailDTO
     * @generated
     */
    @GET
    public List<CommentaryDetailDTO> getComments() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", commentaryLogic.countComments());
            return listEntity2DTO(commentaryLogic.getComments(page, maxRecords, tripsId));
        }
        return listEntity2DTO(commentaryLogic.getComments(tripsId));
    }

    /**
     * Obtiene los datos de una instancia de Comments a partir de su ID
     *
     * @param commentId Identificador de la instancia a consultar
     * @return Instancia de CommentaryDetailDTO con los datos del Product
     * consultado
     * @generated
     */
    @GET
    @Path("{commentId: \\d+}")
    public CommentaryDetailDTO getComment(@PathParam("commentId") Long commentId) {
        CommentaryEntity entity = commentaryLogic.getComment(commentId);
        if (entity.getTrip() != null && !tripsId.equals(entity.getTrip().getId())) {
            throw new WebApplicationException(404);
        }
        return new CommentaryDetailDTO(entity);
    }

    /**
     * Se encarga de crear un Comments en la base de datos
     *
     * @param dto Objeto de CommentaryDetailDTO con los datos nuevos
     * @return Objeto de CommentsDetailDTOcon los datos nuevos y su ID
     * @generated
     */
    @POST
    @StatusCreated
    public CommentaryDetailDTO createCommentary(CommentaryDetailDTO dto) {
        return new CommentaryDetailDTO(commentaryLogic.createComment(tripsId, dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Product
     *
     * @param commentId Identificador de la instancia de Product a modificar
     * @param dto Instancia de CommentaryDetailDTO con los nuevos datos
     * @return Instancia de CommentaryDetailDTO con los datos actualizados
     * @generated
     */
    @PUT
    @Path("{commentId: \\d+}")
    public CommentaryDetailDTO updateComment(@PathParam("commentId") Long commentId, CommentaryDetailDTO dto) {
        CommentaryEntity entity = dto.toEntity();
        entity.setId(commentId);
        return new CommentaryDetailDTO(commentaryLogic.updateComment(tripsId, entity));
    }

    /**
     * Elimina una instancia de Product de la base de datos
     *
     * @param commentId Identificador de la instancia a eliminar
     * @generated
     */
    @DELETE
    @Path("{commentId: \\d+}")
    public void deletComment(@PathParam("commentId") Long commentId) {
        commentaryLogic.deleteComment(commentId);
    }

}

