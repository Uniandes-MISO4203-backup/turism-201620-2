/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.resources;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.turism.api.IPaymentMethodLogic;
import co.edu.uniandes.csw.turism.dtos.detail.PaymentMethodDetailDTO;
import co.edu.uniandes.csw.turism.entities.PaymentMethodEntity;
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
public class PaymentMethodResource {

    @Inject
    private IPaymentMethodLogic paymentMethodLogic;
    @Context
    private HttpServletResponse response;
    @QueryParam("page")
    private Integer page;
    @QueryParam("limit")
    private Integer maxRecords;
    @PathParam("clientId")
    private Long clientId;

    /**
     * Convierte una lista de PaymentMethodEntity a una lista de
     * PaymentMethodDetailDTO
     *
     * @param entityList Lista de PaymentMethodEntity a convertir
     * @return Lista de PaymentMethodDetailDTO convertida
     * @generated
     */
    private List<PaymentMethodDetailDTO> listEntity2DTO(List<PaymentMethodEntity> entityList) {
        List<PaymentMethodDetailDTO> list = new ArrayList<>();
        for (PaymentMethodEntity entity : entityList) {
            list.add(new PaymentMethodDetailDTO(entity));
        }
        return list;
    }

    /**
     * Obtiene la lista de los registros de PaymentMethod asociados a un Author
     *
     * @return Colección de objetos de PaymentMethodDetailDTO
     * @generated
     */
    @GET
    public List<PaymentMethodDetailDTO> getPaymentMethods() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", paymentMethodLogic.countPaymentMethods());
            return listEntity2DTO(paymentMethodLogic.getPaymentMethods(page, maxRecords, clientId));
        }
        return listEntity2DTO(paymentMethodLogic.getPaymentMethods(clientId));
    }

    /**
     * Obtiene los datos de una instancia de PaymentMethod a partir de su ID
     * asociado a un Author
     *
     * @param paymentMethodId Identificador de la instancia a consultar
     * @return Instancia de PaymentMethodDetailDTO con los datos del
     * PaymentMethod consultado
     * @generated
     */
    @GET
    @Path("{paymentMethodId: \\d+}")
    public PaymentMethodDetailDTO getPaymentMethod(@PathParam("paymentMethodId") Long paymentMethodId) {
        PaymentMethodEntity entity = paymentMethodLogic.getPaymentMethod(paymentMethodId);
        if (entity.getClient() != null && !clientId.equals(entity.getClient().getId())) {
            throw new WebApplicationException(404);
        }
        return new PaymentMethodDetailDTO(entity);
    }

    /**
     * Asocia un PaymentMethod existente a un Author
     *
     * @param dto Objeto de PaymentMethodDetailDTO con los datos nuevos
     * @return Objeto de PaymentMethodDetailDTOcon los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public PaymentMethodDetailDTO createPaymentMethod(PaymentMethodDetailDTO dto) {
        return new PaymentMethodDetailDTO(paymentMethodLogic.createPaymentMethod(clientId, dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de PaymentMethod.
     *
     * @param paymentMethodId Identificador de la instancia de PaymentMethod a
     * modificar
     * @param dto Instancia de PaymentMethodDetailDTO con los nuevos datos.
     * @return Instancia de PaymentMethodDetailDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{paymentMethodId: \\d+}")
    public PaymentMethodDetailDTO updatePaymentMethod(@PathParam("paymentMethodId") Long paymentMethodId, PaymentMethodDetailDTO dto) {
        PaymentMethodEntity entity = dto.toEntity();
        entity.setId(paymentMethodId);
        return new PaymentMethodDetailDTO(paymentMethodLogic.updatePaymentMethod(clientId, entity));
    }

    /**
     * Elimina una instancia de PaymentMethod de la base de datos.
     *
     * @param paymentMethodId Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{paymentMethodId: \\d+}")
    public void deletePaymentMethod(@PathParam("paymentMethodId") Long paymentMethodId) {
        paymentMethodLogic.deletePaymentMethod(paymentMethodId);
    }

}
