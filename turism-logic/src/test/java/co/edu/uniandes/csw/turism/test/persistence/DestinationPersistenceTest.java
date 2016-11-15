/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.test.persistence;

import co.edu.uniandes.csw.turism.entities.DestinationEntity;
import co.edu.uniandes.csw.turism.entities.TripEntity;
import co.edu.uniandes.csw.turism.persistence.DestinationPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jd.cepeda
 */
@RunWith(Arquillian.class)
public class DestinationPersistenceTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DestinationEntity.class.getPackage())
                .addPackage(DestinationPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    TripEntity fatherEntity;

    @Inject
    private DestinationPersistence destinationPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<DestinationEntity> data = new ArrayList<DestinationEntity>();

    /**
     * Configuración inicial de la prueba.
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
     */
    private void clearData() {
        em.createQuery("delete from DestinationEntity").executeUpdate();
        em.createQuery("delete from TripEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        fatherEntity = factory.manufacturePojo(TripEntity.class);
        fatherEntity.setId(1L);
        em.persist(fatherEntity);
        for (int i = 0; i < 3; i++) {
            DestinationEntity entity = factory.manufacturePojo(DestinationEntity.class);

            entity.setTrip(fatherEntity);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Destination.
     */
    @Test
    public void createDestinationTest() {
        PodamFactory factory = new PodamFactoryImpl();
        DestinationEntity newEntity = factory.manufacturePojo(DestinationEntity.class);
        newEntity.setTrip(fatherEntity);
        DestinationEntity result = destinationPersistence.create(newEntity);

        Assert.assertNotNull(result);

        DestinationEntity entity = em.find(DestinationEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de Destinations.
     */
    @Test
    public void getDestinationsTest() {
        List<DestinationEntity> list = destinationPersistence.findAll(null, null, fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (DestinationEntity ent : list) {
            boolean found = false;
            for (DestinationEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Destination.
     */
    @Test
    public void getDestinationTest() {
        DestinationEntity entity = data.get(0);
        DestinationEntity newEntity = destinationPersistence.find(entity.getTrip().getId(), entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Prueba para eliminar un Destination.
     */
    @Test
    public void deleteDestinationTest() {
        DestinationEntity entity = data.get(0);
        destinationPersistence.delete(entity.getId());
        DestinationEntity deleted = em.find(DestinationEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Destination.
     */
    @Test
    public void updateDestinationTest() {
        DestinationEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        DestinationEntity newEntity = factory.manufacturePojo(DestinationEntity.class);

        newEntity.setId(entity.getId());

        destinationPersistence.update(newEntity);

        DestinationEntity resp = em.find(DestinationEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

}
