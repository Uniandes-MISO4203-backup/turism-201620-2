/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.csw.turism.entities.QuestionEntity;
import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author af.osorio10
 */
@Stateless
public class QuestionPersistence extends CrudPersistence<QuestionEntity> {
    
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
    protected Class<QuestionEntity> getEntityClass() {
        return QuestionEntity.class;
    }

    public QuestionEntity find(Long tripid, Long questionid) {
        TypedQuery<QuestionEntity> q = em.createQuery("select p from QuestionEntity p where (p.trip.id = :tripid) and (p.id = :questionid)", QuestionEntity.class);
        q.setParameter("tripid", tripid);
        q.setParameter("questionid", questionid);
        return q.getSingleResult();
    }
    
    public List<QuestionEntity> findAll(Integer page, Integer maxRecords, Long tripid) {
        TypedQuery<QuestionEntity> q = em.createQuery("select p from QuestionEntity p where (p.trip.id = :tripid)", QuestionEntity.class);
        q.setParameter("tripid", tripid);
        if (page != null && maxRecords != null) {
            q.setFirstResult((page - 1) * maxRecords);
            q.setMaxResults(maxRecords);
        }
        return q.getResultList();
    }
    
}
