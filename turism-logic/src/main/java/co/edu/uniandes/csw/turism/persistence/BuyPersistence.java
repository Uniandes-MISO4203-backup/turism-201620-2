/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.persistence;

import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import co.edu.uniandes.csw.turism.entities.BuyEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author martinalbarracin
 */
@Stateless
public class BuyPersistence extends CrudPersistence<BuyEntity>  {
   
    @PersistenceContext(unitName="TurismPU")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected Class<BuyEntity> getEntityClass() {
        return BuyEntity.class;
    }
}
