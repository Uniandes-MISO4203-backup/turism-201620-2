/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.resources;

import co.edu.uniandes.csw.turism.api.IProductLogic;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @product martinalbarracin
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductBuysResource {
  
    @Inject private IProductLogic productLogic;
    @Context private HttpServletResponse response;

    /**
     * Convierte una lista de BuyEntity a una lista de BuyDetailDTO.
     *
     * @param entityList Lista de BuyEntity a convertir.
     * @return Lista de BuyDetailDTO convertida.
     * @generated
     */
    private List<BuyDetailDTO> buysListEntity2DTO(List<BuyEntity> entityList){
        List<BuyDetailDTO> list = new ArrayList<>();
        for (BuyEntity entity : entityList) {
            list.add(new BuyDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de BuyDetailDTO a una lista de BuyEntity.
     *
     * @param dtos Lista de BuyDetailDTO a convertir.
     * @return Lista de BuyEntity convertida.
     * @generated
     */
    private List<BuyEntity> buysListDTO2Entity(List<BuyDetailDTO> dtos){
        List<BuyEntity> list = new ArrayList<>();
        for (BuyDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

    /**
     * Obtiene una colección de instancias de BuyDetailDTO asociadas a una
     * instancia de Product
     *
     * @param productsId Identificador de la instancia de Product
     * @return Colección de instancias de BuyDetailDTO asociadas a la instancia de Product
     * @generated
     */
    @GET
    public List<BuyDetailDTO> listBuys(@PathParam("productsId") Long productsId) {
        return buysListEntity2DTO(productLogic.listBuys(productsId));
    }

    /**
     * Obtiene una instancia de Buy asociada a una instancia de Product
     *
     * @param productsId Identificador de la instancia de Product
     * @param buysId Identificador de la instancia de Buy
     * @generated
     */
    @GET
    @Path("{buysId: \\d+}")
    public BuyDetailDTO getBuys(@PathParam("productsId") Long productsId, @PathParam("buysId") Long buysId) {
        return new BuyDetailDTO(productLogic.getBuys(productsId, buysId));
    }

    /**
     * Asocia un Buy existente a un Product
     *
     * @param productsId Identificador de la instancia de Product
     * @param buysId Identificador de la instancia de Buy
     * @return Instancia de BuyDetailDTO que fue asociada a Product
     * @generated
     */
    @POST
    @Path("{buysId: \\d+}")
    public BuyDetailDTO addBuys(@PathParam("productsId") Long productsId, @PathParam("buysId") Long buysId) {
        return new BuyDetailDTO(productLogic.addBuys(productsId, buysId));
    }

    /**
     * Remplaza las instancias de Buy asociadas a una instancia de Product
     *
     * @param productsId Identificador de la instancia de Product
     * @param buys Colección de instancias de BuyDTO a asociar a instancia de Product
     * @return Nueva colección de BuyDTO asociada a la instancia de Product
     * @generated
     */
    @PUT
    public List<BuyDetailDTO> replaceBuys(@PathParam("productsId") Long productsId, List<BuyDetailDTO> buys) {
        return buysListEntity2DTO(productLogic.replaceBuys(productsId, buysListDTO2Entity(buys)));
    }

    /**
     * Desasocia un Buy existente de un Product existente
     *
     * @param productsId Identificador de la instancia de Product
     * @param buysId Identificador de la instancia de Buy
     * @generated
     */
    @DELETE
    @Path("{buysId: \\d+}")
    public void removeBuys(@PathParam("productsId") Long productsId, @PathParam("buysId") Long buysId) {
        productLogic.removeBuys(productsId, buysId);
    }
}
