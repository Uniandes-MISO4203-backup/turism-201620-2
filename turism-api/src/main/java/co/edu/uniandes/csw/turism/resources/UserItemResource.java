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
import co.edu.uniandes.csw.auth.stormpath.Utils;
import co.edu.uniandes.csw.turism.api.IItemLogic;
import co.edu.uniandes.csw.turism.dtos.detail.ClientDetailDTO;
import co.edu.uniandes.csw.turism.dtos.detail.ItemDetailDTO;
import co.edu.uniandes.csw.turism.entities.ItemEntity;
import com.stormpath.sdk.account.Account;
import org.apache.shiro.authz.UnauthorizedException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * URI: client/wishList/
 *
 * @generated
 */
@Path("/client/wishList")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserItemResource {

    @Inject
    private IItemLogic itemLogic;
    @Context
    private HttpServletResponse response;
    @Context
    private HttpServletRequest req;
    @QueryParam("page")
    private Integer page;
    @QueryParam("limit")
    private Integer maxRecords;


    /**
     * Convierte una lista de ItemEntity a una lista de ItemDetailDTO
     *
     * @param entityList Lista de ItemEntity a convertir
     * @return Lista de ItemDetailDTO convertida
     * @generated
     */
    private List<ItemDetailDTO> listEntity2DTO(List<ItemEntity> entityList) {
        List<ItemDetailDTO> list = new ArrayList<>();
        for (ItemEntity entity : entityList) {
            list.add(new ItemDetailDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de Item asociados a un Client
     *
     * @return Colección de objetos de ItemDetailDTO
     * @generated
     */
    @GET
    public List<ItemDetailDTO> getItems() {
        String accountHref = req.getRemoteUser();
        if (accountHref != null) {
            Account account = Utils.getClient().getResource(accountHref, Account.class);
            Integer clientsId = (Integer) account.getCustomData().get("client_id");
            if (page != null && maxRecords != null) {
                this.response.setIntHeader("X-Total-Count", itemLogic.countItems());
                return listEntity2DTO(itemLogic.getItems(page, maxRecords, clientsId.longValue()));
            }
            return listEntity2DTO(itemLogic.getItems(clientsId.longValue()));
        }
        throw new WebApplicationException("User not authorized to do this", 403);
    }

    /**
     * Asocia un Item existente a un Client
     *
     * @param dto Objeto de ItemDetailDTO con los datos nuevos
     * @return Objeto de ItemDetailDTOcon los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public ClientDetailDTO createItem(ItemDetailDTO dto) {
        String accountHref = req.getRemoteUser();
        if (accountHref != null) {
            Account account = Utils.getClient().getResource(accountHref, Account.class);
            Integer clientsId = (Integer) account.getCustomData().get("client_id");
            itemLogic.createItem(clientsId.longValue(), dto.toEntity());
            ClientDetailDTO clientDetailDTO = new ClientDetailDTO();
            clientDetailDTO.setId(clientsId.longValue());
            return clientDetailDTO;
        }
        throw new WebApplicationException("User not authorized to do this", 403);
    }

}
