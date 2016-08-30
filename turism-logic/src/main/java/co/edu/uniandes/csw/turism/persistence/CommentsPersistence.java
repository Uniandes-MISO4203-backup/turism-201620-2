/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.csw.turism.entities.CommentsEntity;
import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
/**
 *
 * @author fe.ruiz
 */
 
/**
 * @generated
 */
@Stateless
public class CommentsPersistence extends CrudPersistence<CommentsEntity>{
   
    @PersistenceContext(unitName="TurismPU")
    protected EntityManager em;

    /**
     * @generated
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * @generated
     */
    @Override
    protected Class<CommentsEntity> getEntityClass() {
        return CommentsEntity.class;
    }  
}
