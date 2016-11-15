/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.api;

import co.edu.uniandes.csw.turism.entities.DestinationEntity;
import java.util.List;

/**
 *
 * @author jd.cepeda
 */
public interface IDestinationLogic {
    public int countDestinations();
    public List<DestinationEntity> getDestinations(Long tripid);
    public List<DestinationEntity> getDestinations(Integer page, Integer maxRecords, Long tripid);
    public DestinationEntity getDestination(Long destinationid);
    public DestinationEntity createDestination(Long tripid, DestinationEntity entity);
    public DestinationEntity updateDestination(Long tripid, DestinationEntity entity);
    public void deleteDestination(Long id);
}