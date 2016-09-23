/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.test.logic;

/**
 *
 * @author martinalbarracin
 */

import co.edu.uniandes.csw.turism.ejbs.BuyLogic;
import co.edu.uniandes.csw.turism.api.IBuyLogic;
import co.edu.uniandes.csw.turism.entities.BuyEntity;
import co.edu.uniandes.csw.turism.persistence.BuyPersistence;
import co.edu.uniandes.csw.turism.entities.TripEntity;
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
 * @generated
 */
@RunWith(Arquillian.class)

public class BuyLogicTest {
    
    
    /**
     * @generated
     */

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IBuyLogic buyLogic;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    private UserTransaction utx;

    /**
     * @generated
     */
    private List<BuyEntity> data = new ArrayList<BuyEntity>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BuyEntity.class.getPackage())
                .addPackage(BuyLogic.class.getPackage())
                .addPackage(IBuyLogic.class.getPackage())
                .addPackage(BuyPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
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
        em.createQuery("delete from TripEntity").executeUpdate();
        em.createQuery("delete from BuyEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            BuyEntity entity = factory.manufacturePojo(BuyEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un Buy
     *
     * @generated
     */
    @Test
    public void createBuyTest() {
        BuyEntity newEntity = factory.manufacturePojo(BuyEntity.class);
        BuyEntity result = buyLogic.createBuy(newEntity);
        Assert.assertNotNull(result);
        BuyEntity entity = em.find(BuyEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de Buys
     *
     * @generated
     */
    @Test
    public void getBuysTest() {
        List<BuyEntity> list = buyLogic.getBuys();
        Assert.assertEquals(data.size(), list.size());
        for (BuyEntity entity : list) {
            boolean found = false;
            for (BuyEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un Buy
     *
     * @generated
     */
    @Test
    public void getBuyTest() {
        BuyEntity entity = data.get(0);
        BuyEntity resultEntity = buyLogic.getBuy(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * Prueba para eliminar un Buy
     *
     * @generated
     */
    @Test
    public void deleteBuyTest() {
        BuyEntity entity = data.get(1);
        buyLogic.deleteBuy(entity.getId());
        BuyEntity deleted = em.find(BuyEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Buy
     *
     * @generated
     */
    @Test
    public void updateBuyTest() {
        BuyEntity entity = data.get(0);
        BuyEntity pojoEntity = factory.manufacturePojo(BuyEntity.class);

        pojoEntity.setId(entity.getId());

        buyLogic.updateBuy(pojoEntity);

        BuyEntity resp = em.find(BuyEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }
    
}
