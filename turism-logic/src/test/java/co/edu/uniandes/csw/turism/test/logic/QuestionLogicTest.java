/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.test.logic;

import co.edu.uniandes.csw.turism.ejbs.QuestionLogic;
import co.edu.uniandes.csw.turism.api.IQuestionLogic;
import co.edu.uniandes.csw.turism.entities.QuestionEntity;
import co.edu.uniandes.csw.turism.entities.TripEntity;
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
public class QuestionLogicTest {
    
    /**
     * @generated
     */
    TripEntity fatherEntity;

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IQuestionLogic questionLogic;

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
    private List<QuestionEntity> data = new ArrayList<QuestionEntity>();

    /**
     * @generated
     */
    private List<TripEntity> tripData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(QuestionEntity.class.getPackage())
                .addPackage(QuestionLogic.class.getPackage())
                .addPackage(IQuestionLogic.class.getPackage())
                .addPackage(QuestionPersistence.class.getPackage())
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
        em.createQuery("delete from QuestionEntity").executeUpdate();
        em.createQuery("delete from TripEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {

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
     * Prueba para crear un Question
     *
     * @generated
     */
    @Test
    public void createQuestionTest() {
        QuestionEntity newEntity = factory.manufacturePojo(QuestionEntity.class);
        QuestionEntity result = questionLogic.createQuestion(fatherEntity.getId(), newEntity);
        Assert.assertNotNull(result);
        QuestionEntity entity = em.find(QuestionEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getRespuesta(), entity.getRespuesta());
    }

    /**
     * Prueba para consultar la lista de Questions
     *
     * @generated
     */
    @Test
    public void getQuestionsTest() {
        List<QuestionEntity> list = questionLogic.getQuestions(fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (QuestionEntity entity : list) {
            boolean found = false;
            for (QuestionEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un Question
     *
     * @generated
     */
    @Test
    public void getQuestionTest() {
        QuestionEntity entity = data.get(0);
        QuestionEntity resultEntity = questionLogic.getQuestion(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getRespuesta(), resultEntity.getRespuesta());
    }

    /**
     * Prueba para eliminar un Question
     *
     * @generated
     */
    @Test
    public void deleteQuestionTest() {
        QuestionEntity entity = data.get(1);
        questionLogic.deleteQuestion(entity.getId());
        QuestionEntity deleted = em.find(QuestionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Question
     *
     * @generated
     */
    @Test
    public void updateQuestionTest() {
        QuestionEntity entity = data.get(0);
        QuestionEntity pojoEntity = factory.manufacturePojo(QuestionEntity.class);

        pojoEntity.setId(entity.getId());

        questionLogic.updateQuestion(fatherEntity.getId(), pojoEntity);

        QuestionEntity resp = em.find(QuestionEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getRespuesta(), resp.getRespuesta());
    }
}
