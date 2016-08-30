/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.ejbs;

import co.edu.uniandes.csw.turism.api.ICommentsLogic;
import co.edu.uniandes.csw.turism.entities.CommentsEntity;
import co.edu.uniandes.csw.turism.persistence.CommentsPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException; 

/**
 *
 * @author fe.ruiz
 */

/**
 * @generated
 */
@Stateless
public class CommentsLogic implements ICommentsLogic{
    @Inject private CommentsPersistence persistence;

    @Override
    public int countComments() {
        return persistence.count(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CommentsEntity> getComments() {
        return persistence.findAll();
    }

    @Override
    public List<CommentsEntity> getComments(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    @Override
    public CommentsEntity getComment(Long id) {
       return  persistence.find(id);
    }

    @Override
    public CommentsEntity createComment(CommentsEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public CommentsEntity updateComment(CommentsEntity entity) {
         return persistence.update(entity);
    }

    @Override
    public void deleteComment(Long id) {
        persistence.delete(id);
    }
    
       
  
}
