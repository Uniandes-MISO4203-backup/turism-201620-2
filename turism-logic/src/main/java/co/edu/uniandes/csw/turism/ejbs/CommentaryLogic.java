/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.ejbs;

import co.edu.uniandes.csw.turism.api.ICommentaryLogic;
import co.edu.uniandes.csw.turism.entities.CommentaryEntity;
import co.edu.uniandes.csw.turism.persistence.CommentaryPersistence;
import co.edu.uniandes.csw.turism.api.ITripLogic;
import co.edu.uniandes.csw.turism.entities.TripEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author fe.ruiz
 */
@Stateless
public class CommentaryLogic implements ICommentaryLogic{
    @Inject private CommentaryPersistence persistence;

    @Inject
    private ITripLogic tripLogic;
    /**
     * Obtiene el número de registros de Comments.
     *
     * @return Número de registros de Product.
     * @generated
     */
    @Override
    public int countComments() {
        return persistence.count(); //To change body of generated methods, choose Tools | Templates.
    }

     /**
     * Obtiene la lista de los registros de comments
     * @param tripid
     * @return Colección de objetos de CommentaryEntity.
     * @generated
     */
    @Override
    public List<CommentaryEntity> getComments(Long tripid) {
        TripEntity trip = tripLogic.getTrip(tripid);
        return trip.getComments(); 
        //return persistence.findAll();
    }
    
    
      /**
     * Obtiene la lista de los registros de comments
     *
     * @param page
     * @param maxRecords
     * @param tripid id del Trip el cual es padre de los Comments.
     * @return Colección de objetos de CommentaryEntity.
     * @generated
     */
    @Override
    public List<CommentaryEntity> getComments(Integer page, Integer maxRecords, Long tripid) {
        if (tripid!=null){
            return persistence.findAll(page, maxRecords, tripid);    
        }else{
            return persistence.findAll(page, maxRecords);    
        }
    }
      /**
     * Obtiene la lista de los registros de comments
     *
     * @param commentaryid
     * @return Colección de objetos de CommentaryEntity.
     * @generated
     */

    @Override
    public CommentaryEntity getComment(Long commentaryid) {
       return  persistence.find(commentaryid);
    }
    
     /**
     * Crea un Comments 
     * @param tripid
     * @param entity
     * @return Colección de objetos de CommentaryEntity.
     * @generated
     */
    @Override
    public CommentaryEntity createComment(Long tripid,CommentaryEntity entity) {
        TripEntity trip = tripLogic.getTrip(tripid);
        entity.setTrip(trip);
        entity = persistence.create(entity);
        return entity;
    }

      /**
     * Actualiza  un Comments 
     * @param tripid
     * @param entity
     * @return Instancia 
     * @generated
     */
    @Override
    public CommentaryEntity updateComment(Long tripid,CommentaryEntity entity) {
        TripEntity trip = tripLogic.getTrip(tripid);
        entity.setTrip(trip);
        return persistence.update(entity);
    }

     /**
     * Borra  un Comments 
     * @param id
     * @generated
     */
    @Override
    public void deleteComment(Long id) {
        CommentaryEntity old = getComment(id);
        persistence.delete(old.getId());
    }
    
       
  
}
