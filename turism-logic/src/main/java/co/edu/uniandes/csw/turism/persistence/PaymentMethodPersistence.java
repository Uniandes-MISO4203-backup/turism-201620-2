/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.persistence;

import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import co.edu.uniandes.csw.turism.entities.PaymentMethodEntity;
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
public class PaymentMethodPersistence extends CrudPersistence<PaymentMethodEntity>{
    @PersistenceContext(unitName="TurismPU")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected Class<PaymentMethodEntity> getEntityClass() {
        return PaymentMethodEntity.class;
    }

    public PaymentMethodEntity find(Long clientId, Long paymentMethodId) {
        TypedQuery<PaymentMethodEntity> q = em.createQuery("select p from PaymentMethodEntity p where (p.client.id = :clientId) and (p.id = :paymentMethodId)", PaymentMethodEntity.class);
        q.setParameter("clientId", clientId);
        q.setParameter("paymentMethodId", paymentMethodId);
        return q.getSingleResult();
    }

    public List<PaymentMethodEntity> findAll(Integer page, Integer maxRecords, Long clientId) {
        TypedQuery<PaymentMethodEntity> q = em.createQuery("select p from PaymentMethodEntity p where (p.client.id = :clientId)", PaymentMethodEntity.class);
        q.setParameter("clientId", clientId);
        if (page != null && maxRecords != null) {
            q.setFirstResult((page - 1) * maxRecords);
            q.setMaxResults(maxRecords);
        }
        return q.getResultList();
    }
}
