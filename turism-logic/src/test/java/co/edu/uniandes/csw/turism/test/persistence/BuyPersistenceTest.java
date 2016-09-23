/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.test.persistence;


import co.edu.uniandes.csw.turism.entities.BuyEntity;
import co.edu.uniandes.csw.turism.persistence.BuyPersistence;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


/**
 *
 * @author me.albarracin
 */

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class BuyPersistenceTest {
    
    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BuyEntity.class.getPackage())
                .addPackage(BuyPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */

    /**
     * @generated
     */
    @Inject
    private BuyPersistence buyPersistence;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    UserTransaction utx;
    
    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     * @generated
     */
    private void clearData() {
        em.createQuery("delete from BuyEntity").executeUpdate();
    }
    
    /**
     * @generated
     */
    private List<BuyEntity> data = new ArrayList<BuyEntity>();
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            BuyEntity entity = factory.manufacturePojo(BuyEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un Agency.
     *
     * @generated
     */
    @Test
    public void createBuyTest() {
        PodamFactory factory = new PodamFactoryImpl();
        BuyEntity newEntity = factory.manufacturePojo(BuyEntity.class);
        BuyEntity result =    buyPersistence.create(newEntity);
        Assert.assertNotNull(result);
        BuyEntity  entity = em.find(BuyEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
    /**
     * Prueba para consultar la lista de Buys .
     *
     * @generated
     */
    @Test
    public void getBuyTest() {
        List<BuyEntity> list =  buyPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (BuyEntity ent : list) {
            boolean found = false;
            for (BuyEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un Buy.
     *
     * @generated
     */
    @Test
    public void getAgencyTest() {
        BuyEntity entity = data.get(0);
        BuyEntity newEntity = buyPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getDescription(), newEntity.getDescription());
    }
    
    /**
     * Prueba para eliminar un Buy.
     *
     * @generated
     */
    @Test
    public void deleteBuyTest() {
        BuyEntity entity = data.get(0);
        buyPersistence.delete(entity.getId());
        BuyEntity deleted = em.find(BuyEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
     /**
     * Prueba para actualizar un Buy.
     *
     * @generated
     */
    @Test
    public void updateBuyTest() {
        BuyEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        BuyEntity newEntity = factory.manufacturePojo(BuyEntity.class);

        newEntity.setId(entity.getId());
        buyPersistence.update(newEntity);
        BuyEntity resp = em.find(BuyEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}
