/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.resources;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.turism.api.IBuyLogic;
import co.edu.uniandes.csw.turism.dtos.detail.BuyDetailDTO;
import co.edu.uniandes.csw.turism.entities.BuyEntity;
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
 * @product martinalbarracin
 */
@Path("/buys")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)


public class BuyResource {
    
    
    
    @Inject private IBuyLogic buyLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;


    /**
     * Convierte una lista de BuyEntity a una lista de BuyDetailDTO.
     *
     * @param entityList Lista de BuyEntity a convertir.
     * @return Lista de BuyDetailDTO convertida.
     * @generated
     */
    private List<BuyDetailDTO> listEntity2DTO(List<BuyEntity> entityList){
        List<BuyDetailDTO> list = new ArrayList<>();
        for (BuyEntity entity : entityList) {
            list.add(new BuyDetailDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de Buy
     *
     * @return Colección de objetos de BuyDetailDTO
     * @generated
     */
    @GET
    public List<BuyDetailDTO> getBuys() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", buyLogic.countBuys());
            return listEntity2DTO(buyLogic.getBuys(page, maxRecords));
        }
        return listEntity2DTO(buyLogic.getBuys());
    }

    /**
     * Obtiene los datos de una instancia de Buy a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de BuyDetailDTO con los datos del Buy consultado
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public BuyDetailDTO getBuy(@PathParam("id") Long id) {
        return new BuyDetailDTO(buyLogic.getBuy(id));
    }

    /**
     * Se encarga de crear un Buy en la base de datos
     *
     * @param dto Objeto de BuyDetailDTO con los datos nuevos
     * @return Objeto de BuyDetailDTOcon los datos nuevos y su ID
     * @generated
     */
    @POST
    @StatusCreated
    public BuyDetailDTO createBuy(BuyDetailDTO dto) {
        return new BuyDetailDTO(buyLogic.createBuy(dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Buy
     *
     * @param id Identificador de la instancia de Buy a modificar
     * @param dto Instancia de BuyDetailDTO con los nuevos datos
     * @return Instancia de BuyDetailDTO con los datos actualizados
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public BuyDetailDTO updateBuy(@PathParam("id") Long id, BuyDetailDTO dto) {
        BuyEntity entity = dto.toEntity();
        entity.setId(id);
        BuyEntity oldEntity = buyLogic.getBuy(id);
        entity.setProduct(oldEntity.getProduct());
        return new BuyDetailDTO(buyLogic.updateBuy(entity));
    }

    /**
     * Elimina una instancia de Buy de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteBuy(@PathParam("id") Long id) {
        buyLogic.deleteBuy(id);
    }
    
}
