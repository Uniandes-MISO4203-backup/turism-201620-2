/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.test.persistence;

import co.edu.uniandes.csw.turism.entities.TripEntity;
import co.edu.uniandes.csw.turism.entities.QuestionEntity;
import co.edu.uniandes.csw.turism.persistence.QuestionPersistence;
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
 * @author af.osorio10
 */
@RunWith(Arquillian.class)
public class QuestionPersistenceTest {
    
    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(QuestionEntity.class.getPackage())
                .addPackage(QuestionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    TripEntity fatherEntity;

    /**
     * @generated
     */
    @Inject
    private QuestionPersistence questionPersistence;

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
        em.createQuery("delete from QuestionEntity").executeUpdate();
        em.createQuery("delete from TripEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<QuestionEntity> data = new ArrayList<QuestionEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
            fatherEntity = factory.manufacturePojo(TripEntity.class);
            fatherEntity.setId(1L);
            em.persist(fatherEntity);
        for (int i = 0; i < 3; i++) {
            QuestionEntity entity = factory.manufacturePojo(QuestionEntity.class);
            
            entity.setTrip(fatherEntity);
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un Question.
     *
     * @generated
     */
    @Test
    public void createQuestionTest() {
	PodamFactory factory = new PodamFactoryImpl();
        QuestionEntity newEntity = factory.manufacturePojo(QuestionEntity.class);
        newEntity.setTrip(fatherEntity);
        QuestionEntity result = questionPersistence.create(newEntity);

        Assert.assertNotNull(result);

        QuestionEntity entity = em.find(QuestionEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getRespuesta(), entity.getRespuesta());
    }

    /**
     * Prueba para consultar la lista de Questions.
     *
     * @generated
     */
    @Test
    public void getQuestionsTest() {
        List<QuestionEntity> list = questionPersistence.findAll(null, null, fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (QuestionEntity ent : list) {
            boolean found = false;
            for (QuestionEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Question.
     *
     * @generated
     */
    @Test
    public void getQuestionTest() {
        QuestionEntity entity = data.get(0);
        QuestionEntity newEntity = questionPersistence.find(entity.getTrip().getId(), entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getRespuesta(), newEntity.getRespuesta());
    }

    /**
     * Prueba para eliminar un Question.
     *
     * @generated
     */
    @Test
    public void deleteQuestionTest() {
        QuestionEntity entity = data.get(0);
        questionPersistence.delete(entity.getId());
        QuestionEntity deleted = em.find(QuestionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Question.
     *
     * @generated
     */
    @Test
    public void updateQuestionTest() {
        QuestionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        QuestionEntity newEntity = factory.manufacturePojo(QuestionEntity.class);

        newEntity.setId(entity.getId());

        questionPersistence.update(newEntity);

        QuestionEntity resp = em.find(QuestionEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getRespuesta(), resp.getRespuesta());
    }
    
}
