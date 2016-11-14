/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.ejbs;

import co.edu.uniandes.csw.turism.api.IDestinationLogic;
import co.edu.uniandes.csw.turism.api.ITripLogic;
import co.edu.uniandes.csw.turism.entities.DestinationEntity;
import co.edu.uniandes.csw.turism.entities.TripEntity;
import co.edu.uniandes.csw.turism.persistence.DestinationPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 *
 * @author jd.cepeda
 */
@Stateless
public class DestinationLogic implements IDestinationLogic {

    @Inject
    private DestinationPersistence persistence;

    @Inject
    private ITripLogic tripLogic;

    /**
     * Obtiene el número de registros de Destination.
     *
     * @return Número de registros de Destination.
     * @generated
     */
    @Override
    public int countDestinations() {
        return persistence.count();
    }

    /**
     * Obtiene la lista de los registros de Destination que pertenecen a un
     * Trip.
     *
     * @param tripid id del Trip el cual es padre de los Destinations.
     * @return Colección de objetos de DestinationEntity.
     * @generated
     */
    @Override
    public List<DestinationEntity> getDestinations(Long tripid) {
        TripEntity trip = tripLogic.getTrip(tripid);
        return trip.getDestinations();
    }

    /**
     * Obtiene la lista de los registros de Destination que pertenecen a un Trip
     * indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @param tripid id del Trip el cual es padre de los Destinations.
     * @return Colección de objetos de DestinationEntity.
     * @generated
     */
    @Override
    public List<DestinationEntity> getDestinations(Integer page, Integer maxRecords, Long tripid) {
        return persistence.findAll(page, maxRecords, tripid);
    }

    /**
     * Obtiene los datos de una instancia de Destination a partir de su ID.
     *
     * @pre La existencia del elemento padre Trip se debe garantizar.
     * @param destinationid) Identificador del Destination a consultar
     * @return Instancia de DestinationEntity con los datos del Destination
     * consultado.
     * @generated
     */
    @Override
    public DestinationEntity getDestination(Long destinationid) {
        try {
            return persistence.find(destinationid);
        } catch (NoResultException e) {
            throw new IllegalArgumentException("El Destination no existe", e);
        }
    }

    /**
     * Se encarga de crear un Destination en la base de datos.
     *
     * @param entity Objeto de DestinationEntity con los datos nuevos
     * @param tripid id del Trip el cual sera padre del nuevo Destination.
     * @return Objeto de DestinationEntity con los datos nuevos y su ID.
     * @generated
     */
    @Override
    public DestinationEntity createDestination(Long tripid, DestinationEntity entity) {
        TripEntity trip = tripLogic.getTrip(tripid);
        entity.setTrip(trip);
        entity = persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Destination.
     *
     * @param entity Instancia de DestinationEntity con los nuevos datos.
     * @param tripid id del Trip el cual sera padre del Destination actualizado.
     * @return Instancia de DestinationEntity con los datos actualizados.
     * @generated
     */
    @Override
    public DestinationEntity updateDestination(Long tripid, DestinationEntity entity) {
        TripEntity trip = tripLogic.getTrip(tripid);
        entity.setTrip(trip);
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Destination de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @Override
    public void deleteDestination(Long id) {
        DestinationEntity old = getDestination(id);
        persistence.delete(old.getId());
    }

}
