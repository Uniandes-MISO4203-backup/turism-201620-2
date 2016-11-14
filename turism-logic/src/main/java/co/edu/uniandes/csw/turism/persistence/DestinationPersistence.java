/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.persistence;

import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import co.edu.uniandes.csw.turism.entities.DestinationEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jd.cepeda
 */
@Stateless
public class DestinationPersistence extends CrudPersistence<DestinationEntity> {
    @PersistenceContext(unitName="TurismPU")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected Class<DestinationEntity> getEntityClass() {
        return DestinationEntity.class;
    }

    public DestinationEntity find(Long tripid, Long destinationid) {
        TypedQuery<DestinationEntity> q = em.createQuery("select p from DestinationEntity p where (p.trip.id = :tripid) and (p.id = :destinationid)", DestinationEntity.class);
        q.setParameter("tripid", tripid);
        q.setParameter("destinationid", destinationid);
        return q.getSingleResult();
    }

    public List<DestinationEntity> findAll(Integer page, Integer maxRecords, Long tripid) {
        TypedQuery<DestinationEntity> q = em.createQuery("select p from DestinationEntity p where (p.trip.id = :tripid)", DestinationEntity.class);
        q.setParameter("tripid", tripid);
        if (page != null && maxRecords != null) {
            q.setFirstResult((page - 1) * maxRecords);
            q.setMaxResults(maxRecords);
        }
        return q.getResultList();
    }
}
