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
package co.edu.uniandes.csw.turism.ejbs;

import co.edu.uniandes.csw.turism.api.ITripLogic;
import co.edu.uniandes.csw.turism.entities.TripEntity;
import co.edu.uniandes.csw.turism.persistence.TripPersistence;
import co.edu.uniandes.csw.turism.api.IAgencyLogic;
import co.edu.uniandes.csw.turism.entities.AgencyEntity;
import co.edu.uniandes.csw.turism.entities.CategoryEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 * @generated
 */
@Stateless
public class TripLogic implements ITripLogic {

    @Inject private TripPersistence persistence;

    @Inject
    private IAgencyLogic agencyLogic;

    /**
     * Obtiene el número de registros de Trip.
     *
     * @return Número de registros de Trip.
     * @generated
     */
    @Override
    public int countTrips() {
        return persistence.count();
    }

    /**
     * Obtiene la lista de los registros de Trip que pertenecen a un Agency.
     *
     * @param agencyid id del Agency el cual es padre de los Trips.
     * @return Colección de objetos de TripEntity.
     * @generated
     */
    @Override
    public List<TripEntity> getTrips(Long agencyid) {
        AgencyEntity agency = agencyLogic.getAgency(agencyid);
        return agency.getTrips();
    }

    /**
     * Obtiene la lista de los registros de Trip que pertenecen a un Agency indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @param agencyid id del Agency el cual es padre de los Trips.
     * @return Colección de objetos de TripEntity.
     * @generated
     */
    @Override
    public List<TripEntity> getTrips(Integer page, Integer maxRecords, Long agencyid) {
        if (agencyid!=null){
        return persistence.findAll(page, maxRecords, agencyid);    
        }else{
        return persistence.findAll(page, maxRecords);    
        }
    }
    
    /**
     * Obtiene la lista de los registros de Trip por categoria.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @param categoryid id de la categoria.
     * @return Colección de objetos de TripEntity.
     * @generated
     */
    @Override
    public List<TripEntity> getTripByCategory(Integer page, Integer maxRecords, Long categoryid) {        
        return persistence.getTripByCategory(page, maxRecords,categoryid);  
        
    }

    /**
     * Obtiene los datos de una instancia de Trip a partir de su ID.
     *
     * @pre La existencia del elemento padre Agency se debe garantizar.
     * @param tripid) Identificador del Trip a consultar
     * @return Instancia de TripEntity con los datos del Trip consultado.
     * @generated
     */
    @Override
    public TripEntity getTrip(Long tripid) {
        try {
            return persistence.find(tripid);
        }catch(NoResultException e){
            throw new IllegalArgumentException("El Trip no existe", e);
        }
    }

    /**
     * Se encarga de crear un Trip en la base de datos.
     *
     * @param entity Objeto de TripEntity con los datos nuevos
     * @param agencyid id del Agency el cual sera padre del nuevo Trip.
     * @return Objeto de TripEntity con los datos nuevos y su ID.
     * @generated
     */
    @Override
    public TripEntity createTrip(Long agencyid, TripEntity entity) {
        AgencyEntity agency = agencyLogic.getAgency(agencyid);
        entity.setAgency(agency);
        entity = persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Trip.
     *
     * @param entity Instancia de TripEntity con los nuevos datos.
     * @param agencyid id del Agency el cual sera padre del Trip actualizado.
     * @return Instancia de TripEntity con los datos actualizados.
     * @generated
     */
    @Override
    public TripEntity updateTrip(Long agencyid, TripEntity entity) {
        AgencyEntity agency = agencyLogic.getAgency(agencyid);
        entity.setAgency(agency);
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Trip de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @param agencyid id del Agency el cual es padre del Trip.
     * @generated
     */
    @Override
    public void deleteTrip(Long id) {
        TripEntity old = getTrip(id);
        persistence.delete(old.getId());
    }
  

    /**
     * Obtiene una colección de instancias de CategoryEntity asociadas a una
     * instancia de Trip
     *
     * @param tripId Identificador de la instancia de Trip
     * @return Colección de instancias de CategoryEntity asociadas a la instancia de Trip
     * @generated
     */
    @Override
    public List<CategoryEntity> listCategory(Long tripId) {
        return getTrip(tripId).getCategory();
    }

    /**
     * Obtiene una instancia de CategoryEntity asociada a una instancia de Trip
     *
     * @param tripId Identificador de la instancia de Trip
     * @param categoryId Identificador de la instancia de Category
     * @generated
     */
    @Override
    public CategoryEntity getCategory(Long tripId, Long categoryId) {
        List<CategoryEntity> list = getTrip(tripId).getCategory();
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryId);
        int index = list.indexOf(categoryEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Asocia un Category existente a un Trip
     *
     * @param tripId Identificador de la instancia de Trip
     * @param categoryId Identificador de la instancia de Category
     * @return Instancia de CategoryEntity que fue asociada a Trip
     * @generated
     */
    @Override
    public CategoryEntity addCategory(Long tripId, Long categoryId) {
        TripEntity tripEntity = getTrip(tripId);
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryId);
        tripEntity.getCategory().add(categoryEntity);
        return getCategory(tripId, categoryId);
    }

    /**
     * Remplaza las instancias de Category asociadas a una instancia de Trip
     *
     * @param tripId Identificador de la instancia de Trip
     * @param list Colección de instancias de CategoryEntity a asociar a instancia de Trip
     * @return Nueva colección de CategoryEntity asociada a la instancia de Trip
     * @generated
     */
    @Override
    public List<CategoryEntity> replaceCategory(Long tripId, List<CategoryEntity> list) {
        TripEntity tripEntity = getTrip(tripId);
        tripEntity.setCategory(list);
        return tripEntity.getCategory();
    }

    /**
     * Desasocia un Category existente de un Trip existente
     *
     * @param tripId Identificador de la instancia de Trip
     * @param categoryId Identificador de la instancia de Category
     * @generated
     */
    @Override
    public void removeCategory(Long tripId, Long categoryId) {
        TripEntity entity = getTrip(tripId);
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryId);
        entity.getCategory().remove(categoryEntity);
    }
}
