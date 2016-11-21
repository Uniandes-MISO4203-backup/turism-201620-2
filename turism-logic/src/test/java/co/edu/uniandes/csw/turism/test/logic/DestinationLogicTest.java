/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.test.logic;

import co.edu.uniandes.csw.turism.api.IDestinationLogic;
import co.edu.uniandes.csw.turism.ejbs.DestinationLogic;
import co.edu.uniandes.csw.turism.entities.DestinationEntity;
import co.edu.uniandes.csw.turism.entities.TripEntity;
import co.edu.uniandes.csw.turism.persistence.DestinationPersistence;
import java.text.SimpleDateFormat;
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
public class DestinationLogicTest {

    TripEntity fatherEntity;

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private IDestinationLogic destinationLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<DestinationEntity> data = new ArrayList<DestinationEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DestinationEntity.class.getPackage())
                .addPackage(DestinationLogic.class.getPackage())
                .addPackage(IDestinationLogic.class.getPackage())
                .addPackage(DestinationPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
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
     * Prueba para crear un Destination
     */
    @Test
    public void createDestinationTest() {
        DestinationEntity newEntity = factory.manufacturePojo(DestinationEntity.class);
        DestinationEntity result = destinationLogic.createDestination(fatherEntity.getId(), newEntity);
        Assert.assertNotNull(result);
        DestinationEntity entity = em.find(DestinationEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(new SimpleDateFormat("dd/MM/yyyy").format(newEntity.getInitialDate()), new SimpleDateFormat("dd/MM/yyyy").format(entity.getInitialDate()));
        Assert.assertEquals(new SimpleDateFormat("dd/MM/yyyy").format(newEntity.getFinalDate()), new SimpleDateFormat("dd/MM/yyyy").format(entity.getFinalDate()));
        Assert.assertEquals(newEntity.getDuration(), entity.getDuration());
        Assert.assertEquals(newEntity.getActivities(), entity.getActivities());
    }

    /**
     * Prueba para consultar la lista de Destinations
     *
     * @generated
     */
    @Test
    public void getDestinationsTest() {
        List<DestinationEntity> list = destinationLogic.getDestinations(fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (DestinationEntity entity : list) {
            boolean found = false;
            for (DestinationEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Destination
     */
    @Test
    public void getDestinationTest() {
        DestinationEntity entity = data.get(0);
        DestinationEntity resultEntity = destinationLogic.getDestination(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(new SimpleDateFormat("dd/MM/yyyy").format(entity.getInitialDate()), new SimpleDateFormat("dd/MM/yyyy").format(resultEntity.getInitialDate()));
        Assert.assertEquals(new SimpleDateFormat("dd/MM/yyyy").format(entity.getFinalDate()), new SimpleDateFormat("dd/MM/yyyy").format(resultEntity.getFinalDate()));
        Assert.assertEquals(entity.getDuration(), resultEntity.getDuration());
        Assert.assertEquals(entity.getActivities(), resultEntity.getActivities());
    }

    /**
     * Prueba para eliminar un Destination
     */
    @Test
    public void deleteDestinationTest() {
        DestinationEntity entity = data.get(1);
        destinationLogic.deleteDestination(entity.getId());
        DestinationEntity deleted = em.find(DestinationEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Destination
     *
     * @generated
     */
    @Test
    public void updateDestinationTest() {
        DestinationEntity entity = data.get(0);
        DestinationEntity pojoEntity = factory.manufacturePojo(DestinationEntity.class);

        pojoEntity.setId(entity.getId());

        destinationLogic.updateDestination(fatherEntity.getId(), pojoEntity);

        DestinationEntity resp = em.find(DestinationEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(new SimpleDateFormat("dd/MM/yyyy").format(pojoEntity.getInitialDate()), new SimpleDateFormat("dd/MM/yyyy").format(resp.getInitialDate()));
        Assert.assertEquals(new SimpleDateFormat("dd/MM/yyyy").format(pojoEntity.getFinalDate()), new SimpleDateFormat("dd/MM/yyyy").format(resp.getFinalDate()));
        Assert.assertEquals(pojoEntity.getDuration(), resp.getDuration());
        Assert.assertEquals(pojoEntity.getActivities(), resp.getActivities());
    }

}
