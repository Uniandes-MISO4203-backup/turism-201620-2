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
import co.edu.uniandes.csw.turism.api.ITripLogic;
import co.edu.uniandes.csw.turism.dtos.detail.CategoryDetailDTO;
import co.edu.uniandes.csw.turism.entities.CategoryEntity;
import java.util.ArrayList;
/**
 * URI: agencys/{agencysId: \\d+}/trips/{tripsId: \\d+}/category
 * @generated
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TripCategoryResource {

    @Inject private ITripLogic tripLogic;
    @Context private HttpServletResponse response;

    /**
     * Convierte una lista de CategoryEntity a una lista de CategoryDetailDTO.
     *
     * @param entityList Lista de CategoryEntity a convertir.
     * @return Lista de CategoryDetailDTO convertida.
     * @generated
     */
    private List<CategoryDetailDTO> categoryListEntity2DTO(List<CategoryEntity> entityList){
        List<CategoryDetailDTO> list = new ArrayList<>();
        for (CategoryEntity entity : entityList) {
            list.add(new CategoryDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de CategoryDetailDTO a una lista de CategoryEntity.
     *
     * @param dtos Lista de CategoryDetailDTO a convertir.
     * @return Lista de CategoryEntity convertida.
     * @generated
     */
    private List<CategoryEntity> categoryListDTO2Entity(List<CategoryDetailDTO> dtos){
        List<CategoryEntity> list = new ArrayList<>();
        for (CategoryDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

    /**
     * Obtiene una colección de instancias de CategoryDetailDTO asociadas a una
     * instancia de Trip
     *
     * @param tripsId Identificador de la instancia de Trip
     * @return Colección de instancias de CategoryDetailDTO asociadas a la instancia de Trip
     * @generated
     */
    @GET
    public List<CategoryDetailDTO> listCategory(@PathParam("tripsId") Long tripsId) {
        return categoryListEntity2DTO(tripLogic.listCategory(tripsId));
    }

    /**
     * Obtiene una instancia de Category asociada a una instancia de Trip
     *
     * @param tripsId Identificador de la instancia de Trip
     * @param categoryId Identificador de la instancia de Category
     * @generated
     */
    @GET
    @Path("{categoryId: \\d+}")
    public CategoryDetailDTO getCategory(@PathParam("tripsId") Long tripsId, @PathParam("categoryId") Long categoryId) {
        return new CategoryDetailDTO(tripLogic.getCategory(tripsId, categoryId));
    }

    /**
     * Asocia un Category existente a un Trip
     *
     * @param tripsId Identificador de la instancia de Trip
     * @param categoryId Identificador de la instancia de Category
     * @return Instancia de CategoryDetailDTO que fue asociada a Trip
     * @generated
     */
    @POST
    @Path("{categoryId: \\d+}")
    public CategoryDetailDTO addCategory(@PathParam("tripsId") Long tripsId, @PathParam("categoryId") Long categoryId) {
        return new CategoryDetailDTO(tripLogic.addCategory(tripsId, categoryId));
    }

    /**
     * Remplaza las instancias de Category asociadas a una instancia de Trip
     *
     * @param tripsId Identificador de la instancia de Trip
     * @param categorys Colección de instancias de CategoryDTO a asociar a instancia de Trip
     * @return Nueva colección de CategoryDTO asociada a la instancia de Trip
     * @generated
     */
    @PUT
    public List<CategoryDetailDTO> replaceCategory(@PathParam("tripsId") Long tripsId, List<CategoryDetailDTO> categorys) {
        return categoryListEntity2DTO(tripLogic.replaceCategory(tripsId, categoryListDTO2Entity(categorys)));
    }

    /**
     * Desasocia un Category existente de un Trip existente
     *
     * @param tripsId Identificador de la instancia de Trip
     * @param categoryId Identificador de la instancia de Category
     * @generated
     */
    @DELETE
    @Path("{categoryId: \\d+}")
    public void removeCategory(@PathParam("tripsId") Long tripsId, @PathParam("categoryId") Long categoryId) {
        tripLogic.removeCategory(tripsId, categoryId);
    }
}
