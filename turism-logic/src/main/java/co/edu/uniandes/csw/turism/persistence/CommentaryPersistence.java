/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.csw.turism.entities.CommentaryEntity;
import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import java.util.List;
import javax.persistence.TypedQuery;
/**
 *
 * @author fe.ruiz
 */
 
/**
 * @generated
 */
@Stateless
public class CommentaryPersistence extends CrudPersistence<CommentaryEntity>{
   
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
    protected Class<CommentaryEntity> getEntityClass() {
        return CommentaryEntity.class;
    } 
    
     public CommentaryEntity find(Long tripid, Long commentaryid) {
        TypedQuery<CommentaryEntity> q = em.createQuery("select p from CommentaryEntity p where (p.trip.id = :tripid) and (p.id = :commentaryid)", CommentaryEntity.class);
        q.setParameter("tripid", tripid);
        q.setParameter("commentaryid", commentaryid);
        return q.getSingleResult();
    }
    
    public List <CommentaryEntity> findAll(Integer page, Integer maxRecords, Long tripid) {
        TypedQuery<CommentaryEntity> q = em.createQuery("select p from CommentaryEntity  p where (p.trip.id = :tripid)", CommentaryEntity.class);
        q.setParameter("tripid", tripid);
        if (page != null && maxRecords != null) {
            q.setFirstResult((page - 1) * maxRecords);
            q.setMaxResults(maxRecords);
        }
        return q.getResultList();
    }
}
