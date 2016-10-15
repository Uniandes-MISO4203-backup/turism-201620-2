/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.ejbs;

import co.edu.uniandes.csw.turism.entities.CommentaryEntity;
import co.edu.uniandes.csw.turism.persistence.CommentaryPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import co.edu.uniandes.csw.turism.api.ICommentaryLogic;


/**
 *
 * @author fe.ruiz
 */

/**
 * @generated
 */
@Stateless
public class CommentaryLogic implements ICommentaryLogic{
    @Inject private CommentaryPersistence persistence;

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
     *
     * @return Colección de objetos de CommentaryEntity.
     * @generated
     */
    @Override
    public List<CommentaryEntity> getComments() {
        return persistence.findAll();
    }
    
    
      /**
     * Obtiene la lista de los registros de comments
     *
     * @param page
     * @param maxRecords
     * @return Colección de objetos de CommentaryEntity.
     * @generated
     */
    @Override
    public List<CommentaryEntity> getComments(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    
      /**
     * Obtiene la lista de los registros de comments
     *
     * @param id
     * @return Colección de objetos de CommentaryEntity.
     * @generated
     */

    @Override
    public CommentaryEntity getComment(Long id) {
       return  persistence.find(id);
    }
    
     /**
     * Crea un Comments 
     * @param entity
     * @return Colección de objetos de CommentaryEntity.
     * @generated
     */
    @Override
    public CommentaryEntity createComment(CommentaryEntity entity) {
        persistence.create(entity);
        return entity;
    }

      /**
     * Actualiza  un Comments 
     *
     * @param entity
     * @return Instancia 
     * @generated
     */
    @Override
    public CommentaryEntity updateComment(CommentaryEntity entity) {
         return persistence.update(entity);
    }

     /**
     * Borra  un Comments 
     * @param id
     * @generated
     */
    @Override
    public void deleteComment(Long id) {
        persistence.delete(id);
    }
    
       
  
}
